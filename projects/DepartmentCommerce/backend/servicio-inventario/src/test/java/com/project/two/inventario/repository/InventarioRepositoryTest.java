package com.project.two.inventario.repository;


import com.project.two.inventario.domain.entity.Categoria;
import com.project.two.inventario.domain.entity.Inventario;
import com.project.two.inventario.domain.entity.Producto;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InventarioRepositoryTest  {

    Inventario inventario;
     @Autowired
    InventarioRepository inventarioRepository;

     @ServiceConnection

     @BeforeEach
     public void init(){
         this.inventario = Inventario.builder().build();
     }

     @Test
     @DisplayName("Save Inventario")
     void shouldSaveInventario_whenSave_returnObjectInventario(){
         Producto producto = Producto.builder()
                 .name("Producto numero1")
                 .description("Description numero1")
                 .build();
         Categoria categoria = Categoria.builder().id(1L).build();
         this.inventario = Inventario.builder().producto(producto)
                 .qty(13).build();

         Inventario inventarioSave = this.inventarioRepository.save(inventario);

         Assertions.assertThat(inventarioSave).isNotNull();
         Assertions.assertThat(inventarioSave.getId()).isGreaterThan(0);
     }

}
