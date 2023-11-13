package com.project.two.inventario.service;

import com.project.two.inventario.domain.Inventario;
import com.project.two.inventario.dto.InventarioDTO;
import com.project.two.inventario.exception.EntityNotFound;
import com.project.two.inventario.mapper.InventarioMapper;
import com.project.two.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioServiceImpl implements InventarioService{

    private final InventarioRepository inventarioRepository;

    @Autowired
    private  InventarioMapper inventarioMapper;

    public InventarioServiceImpl(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }


    /**
     * Funcion que permite agregar cantidades del producto
     * al inventario
     * @param idProducto
     * @param cantidadAgregar
     * @return
     */
    @Override
    public Inventario actualizarInventarioReposicion(Long idProducto, int cantidadAgregar) {
      Inventario inventario = this.inventarioRepository.findByProductoId(idProducto).orElseThrow(
              () ->new EntityNotFound("Not Found Inventario"));

      this.inventarioRepository.reposicionarInventario(idProducto, cantidadAgregar);
      return inventario;
    }

    @Override
    public Inventario actualizarInventarioVenta(Long idProducto, int cantVendida) {
     Inventario inventario = this.inventarioRepository.findByProductoId(idProducto)
             .orElseThrow(()-> new EntityNotFound("Not Found Inventario"));
     this.inventarioRepository.venderProducto(idProducto, cantVendida);
     return inventario;
    }

    @Override
    public List<InventarioDTO> listAll() {
       List<Inventario> listado = this.inventarioRepository.findAll();
       return this.inventarioMapper.listToDTO(listado);
    }

    @Override
    public Optional<Inventario> findById(Long id) {
        return this.inventarioRepository.findById(id);
    }
}
