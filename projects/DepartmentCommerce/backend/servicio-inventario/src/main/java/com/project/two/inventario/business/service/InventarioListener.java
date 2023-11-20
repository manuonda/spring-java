package com.project.two.inventario.business.service;


import com.project.two.commons.constants.CommonConstants;
import com.project.two.commons.dto.EventoInventarioDTO;
import com.project.two.commons.dto.EventoProductoDTO;
import com.project.two.commons.dto.ProductoDTO;
import com.project.two.inventario.domain.entity.Inventario;
import com.project.two.inventario.domain.dto.InventarioDTO;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class InventarioListener {

  private static final Logger logger = LoggerFactory.getLogger(InventarioListener.class);

  private final InventarioService inventarioService;

    @Autowired
    private ProductoFeignClient productoFeignClient;

    public InventarioListener(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
  }


  @KafkaListener(id="" ,
          groupId = "group_inventario",
          topics = {
          "${spring.kafka.topic.reposicion_producto}" ,
          "${spring.kafka.topic.venta_producto}"
          })
  public void actualizarInventarioProducto(EventoProductoDTO strEventoProductoDTO) {
     logger.info("Actualizar Inventario producto" + strEventoProductoDTO);
     logger.info("Tipo de Evento : " + strEventoProductoDTO.getTypeEvent());

     switch (strEventoProductoDTO.getTypeEvent()) {
         case CommonConstants.EVENT_REPOSICION_INVENTARIO ->
             this.inventarioService.actualizarInventarioReposicion(strEventoProductoDTO.getId(), strEventoProductoDTO.getQty());
         case CommonConstants.EVENT_VENTA_PRODUCTO ->
             this.inventarioService.actualizarInventarioVenta(strEventoProductoDTO.getId(), strEventoProductoDTO.getQty());
     }
  }


  @KafkaListener(id="",
    groupId = "group_agregar_producto",
    topics = "${spring.kafka.topic.agregar_producto}")
   public void agregarInventarioProducto(EventoInventarioDTO eventoInventarioProducto){
      logger.info("AgregarInventarioProducto : " + eventoInventarioProducto.toString());
      Optional<InventarioDTO> optionalInventario = this.inventarioService.findById(eventoInventarioProducto.idProducto());
      if ( optionalInventario.isEmpty()) {

          // add new inventario
          try {
              Inventario inventario = new Inventario();
              InventarioDTO inventarioDTO = new InventarioDTO();
              ProductoDTO productoDTO = this.productoFeignClient.findById(eventoInventarioProducto.idProducto());
              if ( productoDTO != null ) {
                  logger.info("Event inventario : " + eventoInventarioProducto.event());
                  logger.info(" Cantidad : "+ eventoInventarioProducto.cantidad() +
                          " idProducto : "+ eventoInventarioProducto.idProducto());
                  inventarioDTO.setIdProducto(eventoInventarioProducto.idProducto());
                  inventarioDTO.setQty(eventoInventarioProducto.cantidad());
                  this.inventarioService.save(inventarioDTO);
              }

          }catch(FeignException ex){
                //Not Found Producto
              logger.info("Not Found Producto " + eventoInventarioProducto.idProducto());
          }

      } else {
          // actualizo el inventario del producto
          InventarioDTO inventario = optionalInventario.get();
          inventario.setQty(eventoInventarioProducto.cantidad());
          this.inventarioService.save(inventario);
      }

  }

}
