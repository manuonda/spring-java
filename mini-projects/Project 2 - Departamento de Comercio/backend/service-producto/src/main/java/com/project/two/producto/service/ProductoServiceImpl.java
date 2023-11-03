package com.project.two.producto.service;

import com.project.two.producto.domain.Categoria;
import com.project.two.producto.domain.Producto;
import com.project.two.producto.dto.ProductoDTO;
import com.project.two.producto.exception.EntityExists;
import com.project.two.producto.exception.EntityNotFound;
import com.project.two.producto.mapper.ProductoMapper;
import com.project.two.producto.repository.CategoriaRepository;
import com.project.two.producto.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoMapper productoMapper;

    private final ProductoRepository productoRepository;

    private final CategoriaRepository categoriaRepository;


    public ProductoServiceImpl(ProductoMapper productoMapper, ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoMapper = productoMapper;
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<ProductoDTO> findAll() {
       return this.productoMapper.toListProductoDTO(this.productoRepository.findAll());
    }

    @Override
    public ProductoDTO save(ProductoDTO dto) {
        Optional<Producto> optionalProducto = this.productoRepository.findByName(dto.getName());
        if(optionalProducto.isPresent()) {
            throw new EntityExists("Producto existe");
        }
        Producto producto = this.productoMapper.toEntity(dto);
        return  this.productoMapper.toProductoDTO(this.productoRepository.save(producto));
    }

    @Override
    public ProductoDTO update(ProductoDTO dto, Long id) {
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
       Producto producto  = this.productoRepository.findById(id)
               .orElseThrow(() ->{
                   throw new EntityNotFound("Producto not exists");
               });
       this.productoRepository.delete(producto);
    }

    @Override
    public ProductoDTO findByName(String name) {
        Producto producto = this.productoRepository.findByName(name)
                .orElseThrow(() -> {
                    throw new EntityNotFound("Producto not exists por nobmre");
                });
        return this.productoMapper.toProductoDTO(producto);
    }

    @Override
    public ProductoDTO findById(Long aLong) {
        return null;
    }

}
