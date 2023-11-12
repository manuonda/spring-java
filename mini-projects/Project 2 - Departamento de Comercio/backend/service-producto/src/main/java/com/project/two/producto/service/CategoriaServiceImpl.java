package com.project.two.producto.service;

import com.project.two.producto.domain.Categoria;
import com.project.two.producto.dto.CategoriaDTO;
import com.project.two.producto.dto.ProductoDTO;
import com.project.two.producto.exception.EntityExists;
import com.project.two.producto.exception.EntityNotFound;
import com.project.two.producto.mapper.CategoriaMapper;
import com.project.two.producto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {


    private final CategoriaMapper categoriaMapper;

    private final CategoriaRepository categoriaRepository;

    @Autowired
    RedisTemplate<Long, Categoria> redisTemplate;

    public CategoriaServiceImpl(CategoriaMapper categoriaMapper, CategoriaRepository categoriaRepository) {
        this.categoriaMapper = categoriaMapper;
        this.categoriaRepository = categoriaRepository;
    }


    @Override
    public CategoriaDTO save(CategoriaDTO dto) {
        Optional<Categoria> findCategoria = this.categoriaRepository.findByName(dto.getName());
        if(findCategoria.isPresent()) {
            throw new EntityExists("Categoria name exists");
        }

        Categoria categoria = this.categoriaMapper.toEntityCategoria(dto);
        categoria = this.categoriaRepository.save(categoria);

        // save categoria
        //this.redisTemplate.opsForValue().set(categoria.getId(),categoria);
        return this.categoriaMapper.toDTOCategoria(categoria);
    }

    @Override
    public CategoriaDTO update(CategoriaDTO dto, Long aLong) {
        Categoria categoria = this.categoriaRepository.findById(aLong)
                .orElseThrow(() ->{
                    throw new EntityNotFound("No exist Categoria");
                });
        categoriaMapper.updateCategoriaFromDTO(dto,categoria);
        Categoria categoriaSave = this.categoriaRepository.save(categoria);
        return this.categoriaMapper.toDTOCategoria(categoriaSave);
    }

    @Override
    public void delete(Long id) {
      Categoria categoria = this.categoriaRepository.findById(id)
              .orElseThrow(()-> { throw  new  EntityNotFound("Not found categoria");});
      this.categoriaRepository.delete(categoria);
    }

    @Override
    public CategoriaDTO findByName(String name) {
        Categoria categoria = this.categoriaRepository.findByName(name).orElseThrow(() -> {
            throw new EntityNotFound("Categoria not existe by name");
        });
        return this.categoriaMapper.toDTOCategoria(categoria);
    }

    @Override
    public CategoriaDTO findById(Long aLong) {
        Categoria categoria = this.categoriaRepository.findById(aLong)
                .orElseThrow(() -> {
                    throw new EntityNotFound("No existe la categoria by id {}"  +aLong);
                });
        return this.categoriaMapper.toDTOCategoria(categoria);
    }

    @Override
    public List<CategoriaDTO> findAll() {
        try {
            Thread.sleep(5000);
            
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.categoriaMapper.toListCategoriaDTO(this.categoriaRepository.findAll());
    }
}
