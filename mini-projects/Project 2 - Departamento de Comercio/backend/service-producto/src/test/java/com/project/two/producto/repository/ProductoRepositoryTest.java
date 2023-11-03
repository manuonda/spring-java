package com.project.two.producto.repository;


import com.project.two.producto.domain.Categoria;
import com.project.two.producto.domain.Producto;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Levantando un docker-compose
 * para correr este Repositorio
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoRepositoryTest {
    Producto producto;

    Categoria categoria;


    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    public void setUp(){
        producto = Producto.builder()
                .id(1L)
                .name("Producto 1")
                .description("Descriptino de producto 1")
                .price(12)
                .qty(12)
                .build();

        categoria = Categoria.builder()
                .id(1L)
                .name("Categoria 1")
                .description("Description uno")
                .build();

        producto.setCategoria(categoria);
    }

    @Test
    @DisplayName("Save Producto")
    public void shouldSaveProducto_whenSaveProducto_returnObject(){
        Producto producto1 = Producto.builder()
                .id(1L)
                .name("Producto numero 1")
                .price(23)
                .qty(23).build();
        //when
        Categoria categoriaSave= this.categoriaRepository.save(categoria);
        producto.setCategoria(categoria);
        Producto productoSave = this.productoRepository.save(producto);


        System.out.println(productoSave.getId());
        //Then
        Assertions.assertThat(productoSave)
                .isNotNull();
        Assertions.assertThat(productoSave.getQty()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Update Producto")
    public void shouldUpdateProducto_whenUpdateProducto_returnObject(){
       // given
        Producto producto1 = Producto.builder().id(2L).name("Producto 33")
                .description("Information 23")
                .qty(23)
                .price(23).build();
        Categoria categoriaSave= this.categoriaRepository.save(this.categoria);
        producto1.setCategoria(categoria);
        Producto productoSave = this.productoRepository.save(producto1);

        //when
        Optional<Producto> optionalProducto= this.productoRepository.findById(productoSave.getId());
        Producto productoEncontrado =  optionalProducto.get();
        productoEncontrado.setName("Producto 33");
        productoEncontrado.setPrice(254);
        productoEncontrado.setDescription("Description numero 23");
        Producto productoUpdate = this.productoRepository.save(productoEncontrado);

        //then
        Assertions.assertThat(productoUpdate).isNotNull();
        Assertions.assertThat(productoUpdate.getId()).isGreaterThan(0);
        Assertions.assertThat(productoUpdate.getName()).isEqualTo("Producto 33");

    }


    @Test
    @DisplayName("Find all producto")
    void shouldFindAll_whenFindAll_returnListAll(){
        // given
        Producto producto1 = Producto.builder().id(3L).name("Producto 1").build();
       Producto producto2 = Producto.builder().id(4L).name("Producto 2").build();
       this.productoRepository.saveAll(List.of(producto1,producto2));

       //when
        List<Producto> listProducto = this.productoRepository.findAll();

        //then
        Assert.assertEquals(listProducto.size(),2);
        Assert.assertNotNull(listProducto);

    }

    @Test
    @DisplayName("Delete producto")
    void shouldDeleteProducto_whenDelete_returnObjectEmpty(){
        //given
        Producto producto1 = Producto.builder()
                .id(1L)
                .name("Producto Information")
                .description("Information de database")
                .qty(23)
                .price(23)
                .build();
        //when
        Producto productoSave = this.productoRepository.save(producto1);
        this.productoRepository.delete(productoSave);
        Optional<Producto> optionalProducto = this.productoRepository.findById(productoSave.getId());


        //then
        assertFalse(optionalProducto.isPresent());
        assertThrows(NoSuchElementException.class, () -> {
           optionalProducto.get();
        });
    }

    @Test
    @DisplayName("Find by Name")
    void shouldFindByName_returnObjectFindByName(){
        //given
        Producto producto = Producto.builder()
                .id(4L)
                .name("Producto Maquina")
                .description("Producto numero 23")
                .price(23.4)
                .qty(11)
                .build();
        this.productoRepository.save(producto);
        //when
        Optional<Producto> optionalFind = this.productoRepository.findByName("Producto Maquina");
        Producto productoEncontrado = optionalFind.get();

        //then
        Assertions.assertThat(productoEncontrado).isNotNull();
        Assertions.assertThat(productoEncontrado.getName()).contains("Maquina");
    }


}
