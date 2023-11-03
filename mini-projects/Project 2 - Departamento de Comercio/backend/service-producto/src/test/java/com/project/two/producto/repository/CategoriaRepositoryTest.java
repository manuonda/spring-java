package com.project.two.producto.repository;


import com.project.two.producto.domain.Categoria;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaRepositoryTest {

    Categoria categoria;

     @Autowired
    CategoriaRepository categoriaRepository;

    @BeforeEach
    public void init() {
       categoria = Categoria.builder()
               .name("Categoria 1")
               .description("Description de Categoria numero one")
               .build();
    }

    @Test
    @DisplayName("Save Categoria")
    void shouldSaveCategoria_returnObjectCategoria(){
        //given
        //when
        Categoria categoriaSave = this.categoriaRepository.save(categoria);

        //then
        Assertions.assertThat(categoriaSave).isNotNull();
        Assertions.assertThat(categoriaSave.getId()).isGreaterThan(0);

    }

    @Test
    @DisplayName("List all")
    void shouldListCategoria_returnListAllCategoria(){
        //given
        Categoria categoria1 = Categoria.builder().name("Categoria 1").description("Categoria description numero 1").build();
        Categoria categoria2 = Categoria.builder().name("Categoria 2").description("Categoria description numero 2 ").build();
        this.categoriaRepository.saveAll(List.of(categoria1,categoria2));
        //when
        List<Categoria> listado = this.categoriaRepository.findAll();

        Assertions.assertThat(listado.size()).isGreaterThan(0);
        Assertions.assertThat(listado).isNotEmpty()
                .extracting(Categoria::getName)
                .contains("Categoria 1");
    }


    @Test
    @DisplayName("Delete categoria")
    void shouldDeleteCategoria_returnEmptyObject(){
        //given
        Categoria categoriaSave = this.categoriaRepository.save(categoria);
        //when
        this.categoriaRepository.delete(categoriaSave);
        Optional<Categoria> optional = this.categoriaRepository.findById(categoriaSave.getId());

        //then
        Assertions.assertThat(optional.get()).isNotNull();
        assertThrows(NoSuchElementException.class, ()-> {
            optional.get();
        });


    }

}
