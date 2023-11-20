package com.project.two.producto.controller;


import com.project.two.producto.TestConfiguration;
import com.project.two.producto.domain.entity.Categoria;
import com.project.two.producto.domain.dto.CategoriaDTO;
import com.project.two.producto.domain.dto.ValidationErrorResponseDTO;
import com.project.two.producto.presentation.controller.CategoriaController;
import com.project.two.producto.business.service.CategoriaService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoriaController.class)
@AutoConfigureMockMvc
public class CategoriaControllerTest extends TestConfiguration {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    Categoria categoria;

    CategoriaDTO categoriaDTO ;

    ObjectMapper objectMapper;


    @BeforeEach
    public void initSetup(){

        MockitoAnnotations.openMocks(this);

        this.categoria = Categoria.builder()
                .id(1L)
                .name("Categoria numero 1")
                .description("Description de categora numero1")
                .build();
        objectMapper =new ObjectMapper();
    }


    @Test
    @DisplayName("Should Save Categoria")
    public void shouldSaveCagetoria_whenSave_returnObjectCategoria() throws Exception {
        //given
        this.categoriaDTO = CategoriaDTO.builder()
                        .id(1L).name("david").build();

        when(this.categoriaService.save(any())).thenReturn(this.categoriaDTO);
        //when
        ResultActions result = mockMvc.perform(
                 post("/api/v1/categorias/create")
                         .content(objectMapper.writeValueAsString(this.categoriaDTO))
                         .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated());

        //then
        result.andExpect(jsonPath("$.id").isNotEmpty());

    }

    @Test
    @DisplayName("Error Saving Categoria")
    void shouldEmptyObject_whenSaveCategoria_returnErroCategoria() throws Exception {
        //given
        CategoriaDTO categoriaDTO1 = CategoriaDTO.builder()
                .description("description numero 1").build();

        when(this.categoriaService.findById(any())).thenReturn(null);
        //when
        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/categorias/create")
                        .content(objectMapper.writeValueAsString(categoriaDTO1))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        //then
        resultActions.andExpect(status().isBadRequest())
                .andExpect(result -> {
                    ValidationErrorResponseDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), ValidationErrorResponseDTO.class);

                     Map<String,String> error = response.getErrors();
                     String errorFieldName = error.get("name");

                    // Assertions
                    Assertions.assertThat(response.getErrors().size()).isEqualTo(1);
                    Assertions.assertThat(errorFieldName).isNotEmpty();
                    Assertions.assertThat(errorFieldName).isEqualTo("El nombre de categoria no puede estar vacio");
                });


        // Se verifica que el servicio no se llamó
        verify(this.categoriaService, Mockito.never()).save(any(CategoriaDTO.class));

    }

    @Test
    @DisplayName("List Categorias")
    public void shouldListCategoria_whenGetList_returnListCategorias() throws Exception {
        // given
        CategoriaDTO categoriaDTO1 = CategoriaDTO.builder().id(1L).name("Categoria numero1").description("numero 1").build();
        CategoriaDTO categoriaDTO2 = CategoriaDTO.builder().id(2L).name("Categoria numero 2").description("numero 2").build();

        List<CategoriaDTO> listDTOS = List.of(categoriaDTO1, categoriaDTO2);
        when(this.categoriaService.findAll()).thenReturn(listDTOS);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/categorias/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(listDTOS.size()))
                .andExpect(jsonPath("$[*].id", Matchers.contains(1,2)))
                .andExpect(jsonPath("$[0].name").value("Categoria numero1"))
                .andExpect(jsonPath("$[1].name").value("Categoria numero 2"))
                .andExpect(jsonPath("$[*].name", Matchers.everyItem(Matchers.startsWith("Categoria"))));

    }


    @Test
    @DisplayName("Test get by id")
    public void shouldGetCategoria_whenFindById_returnObjectCategoria() throws Exception {
        var nombreCatgoria = "Categoria numer1";
        //given
        CategoriaDTO categoriaDTO1 = CategoriaDTO.builder()
                   .id(1L)
                   .name(nombreCatgoria)
                   .description("Categoria description numero 1")
                   .build();

        when(this.categoriaService.findById(any())).thenReturn(categoriaDTO1);
        //when
        ResultActions resultActions = this.mockMvc.perform(get("/api/v1/categorias/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath(".name").value(nombreCatgoria))
                .andExpect(content().json(this.objectMapper.writeValueAsString(categoriaDTO1)));


        resultActions.andExpect( result -> {
             CategoriaDTO categoriaDTO =  objectMapper.readValue(result.getResponse().getContentAsString(), CategoriaDTO.class);
             Assertions.assertThat(categoriaDTO.getName()).isEqualTo(nombreCatgoria)
                     .contains("");
             Assertions.assertThat(categoriaDTO.getId()).isNotNull();
             Assertions.assertThat(categoriaDTO.getId()).isGreaterThan(0);
        });
    }


    @Test
    @DisplayName("Not Found By Id")
    void shouldNotFound_whenFindById_returnEmptyObject() throws Exception {
        var nombreCatgoria = "Categoria numer1";
        //given
        CategoriaDTO categoriaDTO1 = CategoriaDTO.builder()
                .id(1L)
                .name(nombreCatgoria)
                .description("Categoria description numero 1")
                .build();

        when(this.categoriaService.findById(1L)).thenReturn(null);
        //when
        ResultActions resultActions = this.mockMvc.perform(get("/api/v1/categorias/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andExpect(content().string(""));
                //.andExpect(jsonPath(".name").value(nombreCatgoria))
                //.andExpect(content().json(this.objectMapper.writeValueAsString(categoriaDTO1)));


        resultActions.andExpect( result -> {
            CategoriaDTO categoriaDTO =  objectMapper.readValue(result.getResponse().getContentAsString(), CategoriaDTO.class);
            Assertions.assertThat(categoriaDTO.getName()).isEqualTo(nombreCatgoria)
                    .contains("");
            Assertions.assertThat(categoriaDTO.getId()).isNotNull();
            Assertions.assertThat(categoriaDTO.getId()).isGreaterThan(0);
        });
    }


    @Test
    @DisplayName("Find by Name")
    void shouldGetCategoria_whenFindByname_returnObject() throws Exception {
        //given
        var nombreCatgoria = "Categoria numer1";
        //given
        CategoriaDTO categoriaDTO1 = CategoriaDTO.builder()
                .id(1L)
                .name(nombreCatgoria)
                .description("Categoria description numero 1")
                .build();

        when(this.categoriaService.findByName(nombreCatgoria)).thenReturn(categoriaDTO1);
        //when
        ResultActions resultActions = this.mockMvc.perform(get("/api/v1/categorias/findByName/{name}",nombreCatgoria))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(nombreCatgoria))
                .andExpect(jsonPath("$.id").isNumber());
        //then
        resultActions.andExpect(result -> {
           CategoriaDTO categoriaDTO2 =    objectMapper.readValue(result.getResponse().getContentAsString(),CategoriaDTO.class);
            Assertions.assertThat(categoriaDTO2).isNotNull();
            Assertions.assertThat(categoriaDTO2.getId()).isNotNull();
        });
    }


}
