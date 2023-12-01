package com.project.two.producto.business.service.impl;

import com.project.two.commons.constants.CommonConstants;
import com.project.two.commons.dto.EventoInventarioDTO;
import com.project.two.commons.dto.RequestEmailDTO;
import com.project.two.producto.domain.entity.Categoria;
import com.project.two.producto.domain.entity.Producto;
import com.project.two.producto.domain.dto.ProductoDTO;
import com.project.two.producto.common.exception.EntityExists;
import com.project.two.producto.common.exception.EntityNotFound;
import com.project.two.producto.business.mapper.ProductoMapper;
import com.project.two.producto.repository.CategoriaRepository;
import com.project.two.producto.repository.ProductoRepository;
import com.project.two.producto.business.service.ProductoService;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoMapper productoMapper;

    private final ProductoRepository productoRepository;

    private final CategoriaRepository categoriaRepository;

    @Autowired
    private  KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.reposicion_producto}")
    private String kafkaTopicReposicionProducto;

    @Value("${spring.kafka.topic.agregar_producto}")
    private String kafkaTopicAgregarProducto;

    @Value("${spring.kafka.topic.envio_email}")
    private String kafkaTopicSendEmail;



    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(ProductoServiceImpl.class);

    private NewTopic productoTopic;
    public ProductoServiceImpl(ProductoMapper productoMapper, ProductoRepository productoRepository, CategoriaRepository categoriaRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.productoMapper = productoMapper;
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<ProductoDTO> findAll() {
        Logger.info("List all productos");
        return this.productoMapper.toListProductoDTO(this.productoRepository.findAll());
    }



    @Override
    public ProductoDTO save(ProductoDTO dto) {
        Logger.info("Save producto");
        Optional<Producto> optionalProducto = this.productoRepository.findByName(dto.getName());
        if(optionalProducto.isPresent()) {
            throw new EntityExists("Producto existe");
        }

        // TODO : add validation exception
        Producto producto = this.productoRepository.save(this.productoMapper.toEntity(dto));



        // send kafka to topic
        EventoInventarioDTO eventoInventarioDTO = new EventoInventarioDTO(
                CommonConstants.EVENT_AGREGAR_PRODUCTO,
                producto.getId(),
                producto.getQty()
        );



        // send topic inventario
        //kafkaTemplate.send(kafkaTopicAgregarProducto,eventoInventarioDTO);
        // send topic email
        RequestEmailDTO requestEmailDTO = new RequestEmailDTO(
                "manuonda@gmail.com",
                "Creacion de Nuevo Producto  : " + producto.getName() ,
                "",
                null
                );
        kafkaTemplate.send(kafkaTopicSendEmail, requestEmailDTO);

        return  this.productoMapper.toProductoDTO(producto);
    }

    @Override
    public ProductoDTO update(ProductoDTO dto, Long id) {
        Logger.info("Update producto : ");
        Producto productoUpdate = this.productoRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFound("Producto Not Found");
                });
        Optional<Producto> findByName = this.productoRepository.findByName(dto.getName());
        if ( findByName.isPresent() &&
                findByName.get().getName().equals(dto.getName()) &&
              !findByName.get().getId().equals(dto.getId())) {
            throw new EntityNotFound("El nombre del Producto Existe");

        }


        Categoria categoria  = this.categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> {
                    throw new EntityNotFound("Categoria Not Exist");
                });



        productoUpdate.setName(dto.getName());
        productoUpdate.setPrice(dto.getPrice());
        productoUpdate.setDescription(dto.getDescription());
        productoUpdate.setCategoria(categoria);

        Producto productoSave = this.productoRepository.save(productoUpdate);
        return this.productoMapper.toProductoDTO(productoSave);
    }

    @Override
    public void delete(Long id) {
        Logger.info("Delete by id: " + id);
       Producto producto  = this.productoRepository.findById(id)
               .orElseThrow(() ->{
                   throw new EntityNotFound("Producto not exists");
               });
       this.productoRepository.delete(producto);
    }

    @Override
    public ProductoDTO findByName(String name) {
        Logger.info("Find by Name : " + name);
        Producto producto = this.productoRepository.findByName(name)
                .orElseThrow(() -> {
                    throw new EntityNotFound("Producto not exists por nobmre");
                });
        return this.productoMapper.toProductoDTO(producto);
    }

    @Override
    public ProductoDTO findById(Long aLong) {
        Logger.info("find by id producto : "+ aLong);
        Producto producto = this.productoRepository.findById(aLong)
                .orElseThrow(() -> new EntityNotFound("Producto no existe"));
        return this.productoMapper.toProductoDTO(producto);
    }

    @Override
    public boolean realizarReposicionProducto(Long idProducto, int cantidadAgregar) {
        Producto productoFind = this.productoRepository.findById(idProducto)
                .orElseThrow(() -> new  EntityNotFound("Producto not existe"));

        EventoInventarioDTO eventoInventarioDTO =new EventoInventarioDTO(
                CommonConstants.EVENT_REPOSICION_INVENTARIO,
                idProducto,
                cantidadAgregar
        );
        kafkaTemplate.send(kafkaTopicReposicionProducto, eventoInventarioDTO );
        return true;
    }


}
