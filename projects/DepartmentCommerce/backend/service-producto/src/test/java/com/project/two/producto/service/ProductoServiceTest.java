package com.project.two.producto.service;


import com.project.two.producto.TestContainerConfiguration;
import com.project.two.producto.business.mapper.ProductoMapperImpl;
import com.project.two.producto.domain.entity.Categoria;
import com.project.two.producto.domain.entity.Producto;
import com.project.two.producto.domain.dto.CategoriaDTO;
import com.project.two.producto.domain.dto.ProductoDTO;
import com.project.two.producto.repository.CategoriaRepository;
import com.project.two.producto.repository.ProductoRepository;
import com.project.two.producto.business.service.impl.CategoriaServiceImpl;
import com.project.two.producto.business.service.impl.ProductoServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest extends TestContainerConfiguration {

   @Mock
   ProductoRepository productoRepository;

   @Mock
   CategoriaRepository categoriaRepository;

   @InjectMocks
   ProductoServiceImpl productoService;

   @InjectMocks
   CategoriaServiceImpl categoriaService;

   @Spy
   ProductoMapperImpl productoMapper;

   ProductoDTO productoDTO;

   CategoriaDTO categoriaDTO;

    @BeforeEach
    void init(){
      productoDTO = ProductoDTO.builder()
              .name("Arroz")
              .description("Arroz Integral para consumo")
              .qty(1)
              .price(23.55)
              .build();


      categoriaDTO = CategoriaDTO.builder()
              .name("Categoria 1")
              .description("Description de Categoria numero 1")
              .build();
    }


    @Test
    @DisplayName("Save Producto")
    void saveProducto_returnObjectProducto(){
       //given
        ProductoDTO  productoDTO = ProductoDTO.builder()
                .name("Arroz")
                .description("ARROZ INTEGRAL")
                .price(23.5)
                .qty(23)
                .build();
        categoriaDTO = CategoriaDTO.builder()
                .name("Categoria 1")
                .description("Description de Categoria numero 1")
                .build();

        Categoria categoria = Categoria
                .builder()
                .name("Categoria 1")
                .build();

        when(this.categoriaRepository.save(categoria)).thenReturn(categoria);
        CategoriaDTO categoriaDTO1 = this.categoriaService.save(categoriaDTO);

        productoDTO.setIdCategoria(categoria.getId());
        Producto producto1 = this.productoMapper.toEntity(productoDTO);
        when(this.productoRepository.save(producto1)).thenReturn(producto1);

        //when
        ProductoDTO dto = this.productoService.save(productoDTO);
        //then
        Assertions.assertThat(dto)
                .isNotNull()
                .extracting("name");

    }

    @Test
    @DisplayName("Test all Products")
    void shouldListProduct_returnListProducto() {

        //given
        Producto producto1 = Producto.builder()
                .name("Algodon")
                .description("Algodon information")
                .build();

        Producto producto2 = Producto.builder()
                .name("Producto 2")
                .description("Producto 2")
                .build();

        producto1 = this.productoRepository.save(producto1);
        producto2 = this.productoRepository.save(producto2);

        when(this.productoRepository.findAll()).thenReturn(List.of(producto1,producto2));

        //when
        List<ProductoDTO> productos = this.productoService.findAll();
        //then
        Assertions.assertThat(productos.size()).isEqualTo(2);
        Assertions.assertThat(productos)
                .filteredOn(producto->producto.getName().contains("Producoto"))
                .isNotEmpty()
                .hasSize(2);
    }


}
