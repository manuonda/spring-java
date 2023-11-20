package com.project.two.producto.service;

import com.project.two.producto.TestContainerConfiguration;
import com.project.two.producto.business.mapper.CategoriaMapper;
import com.project.two.producto.business.mapper.CategoriaMapperImpl;
import com.project.two.producto.domain.entity.Categoria;
import com.project.two.producto.domain.dto.CategoriaDTO;
import com.project.two.producto.repository.CategoriaRepository;
import com.project.two.producto.business.service.impl.CategoriaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest extends TestContainerConfiguration {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @Spy
    private CategoriaMapperImpl categoriaMapper;


    @Test
    void name() {
    }

    @Test
    @DisplayName("Save Categoria")
    void shouldSaveCategoria_returnSaveCategoria(){
        //given

        CategoriaDTO categoriaDTO = CategoriaDTO.builder()
                        .id(23L)
                        .name("Categoria 1")
                        .description("Description de categoria uno").build();
        Categoria categoria = this.categoriaMapper.toEntityCategoria(categoriaDTO);

        when(this.categoriaRepository.save(categoria)).thenReturn(categoria);
        CategoriaDTO categoriaDTO1 = this.categoriaMapper.toDTOCategoria(categoria);
        //then
        CategoriaDTO categoriaSave = this.categoriaService.save(categoriaDTO);

        //then
        assertThat(categoriaSave.getName())
                .isEqualTo("Categoria 1");
        assertThat(categoriaSave).isNotNull();
        assertThat(categoriaSave.getName())
                .contains("Categoria 1");

    }

    @Test
    @DisplayName("Find Categoria by Id")
    void shouldFindCategoria_returnCategoriaById(){
        Categoria categoria = Categoria.builder()
                .id(11L)
                .name("Categoria Uno").description("Categoria Uno").build();

        CategoriaDTO categoriaDTO = this.categoriaMapper.toDTOCategoria(categoria);
        when(this.categoriaRepository.findById(11L)).thenReturn(Optional.ofNullable(categoria));
        //when
        CategoriaDTO categoriaDTO1 = this.categoriaService.findById(11L);

        //then
        assertThat(categoriaDTO1).isNotNull();
        assertThat(categoriaDTO1.getName())
                .contains("Categoria")
                .isNotEmpty()
                .isNotBlank();
    }

    @Test
    @DisplayName("Find by Name")
    void shouldFindByName_returnObjectCategoria(){
        //given
        Categoria categoria = Categoria.builder()
                .id(11L)
                .name("Categoria One")
                .build();
        when(this.categoriaRepository.findByName("Categoria One")).thenReturn(Optional.ofNullable(categoria));

        //when
        CategoriaDTO categoriaDTO = this.categoriaService.findByName("Categoria One");

        //then
        assertThat(categoriaDTO).isNotNull();
        assertThat(categoriaDTO.getName()).contains("Categoria")
                .isNotEmpty().isNotBlank();
        assertThat(categoriaDTO.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Delete Categoria")
    void shouldDeleteCategoria_fidnById_returnEmptyObject(){
      Categoria categoria = Categoria.builder()
              .id(23l)
              .name("Categoria Hierros")
              .build();
      CategoriaDTO categoriaDTO = this.categoriaMapper.toDTOCategoria(categoria);
      when(this.categoriaRepository.findById(any())).thenReturn(Optional.of(categoria));

      //when
        CategoriaDTO categoriaDTO1 = this.categoriaService.findById(23L);

        //then
        assertThat(categoriaDTO1).isNotNull()
                .hasFieldOrPropertyWithValue("name","Categoria Hierros");
        assertThat(categoriaDTO1.getName())
                .isNotEmpty()
                .isNotBlank()
                .contains("Categoria")
                .startsWith("Categoria");

    }


    @Test
    @DisplayName("List all Categorias")
    void shouldListCategorias_wherFind_returnListCategoria(){
        Categoria categoria = Categoria.builder().name("Categoria 1").build();
        Categoria categoria1 = Categoria.builder().name("Categoria 2").build();
        List<Categoria> list = List.of(categoria1, categoria);
        when(this.categoriaRepository.findAll()).thenReturn(list);

        //when
        List<CategoriaDTO> categorias = this.categoriaService.findAll();
        assertThat(categorias).hasSize(2)
                .isNotEmpty()
                .extracting(categoriaDTO -> categoriaDTO.getName())
                .contains("Categoria 1", "Categoria 2");
    }



    
    

}
