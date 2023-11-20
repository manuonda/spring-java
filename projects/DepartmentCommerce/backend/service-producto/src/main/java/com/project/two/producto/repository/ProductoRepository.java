package com.project.two.producto.repository;

import com.project.two.producto.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByName(String name);

}
