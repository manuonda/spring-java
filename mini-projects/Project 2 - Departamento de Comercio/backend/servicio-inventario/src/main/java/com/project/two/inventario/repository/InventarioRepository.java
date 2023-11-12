package com.project.two.inventario.repository;

import com.project.two.inventario.domain.Inventario;
import com.project.two.inventario.domain.Producto;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    /**
     * Funcion que permite obtener
     * inventario por producto
     * @param producto
     * @return
     */
    Inventario findByProducto(Producto producto);

    // Actualizar inventario para reposicion de stock
    @Modifying  // indica que la consulta modificar el estado de la base de datos
    @Transactional // asegura que la operación sea atomica
    @Query("update Inventario i  set i.qty = i.qty + : cantidadAgregar WHERE i.producto.id = :idProducto")
    void reposicionarInventario(@Param("idProducto") Long idProducto,
                                @Param("cantidaAgregar") int cantidadAgregar);


   // Actualizar inventario para venta de productos
   @Modifying
   @Transactional
   @Query("UPDATE tbl_inventario i SET i.qty =  i.qty - :cantVendida WHERE i.producto.id = :idProducto")
    void venderProducto(@Param("idProducto") Long idProducto,@Param("cantVendida") int cantVendida);


   @Query("SELECT i FROM tbl_inventario i  WHERE i.id_producto=:idProducto")
    public Optional<Inventario> findByProductoId(@Param("idProducto") Long idProducto);
}
