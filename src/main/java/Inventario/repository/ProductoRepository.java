package Inventario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Inventario.model.Producto;
import jakarta.transaction.Transactional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findAllByUsuarioId(long userId);

    Optional<Producto> findByIdAndUsuarioId(long productoId, long usuarioId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Producto p WHERE p.id=:productoId  AND p.usuario.id = :usuarioId")
    void deleteByProductoIdAndUsuarioId(@Param("productoId") long productoId, @Param("usuarioId") long usuarioId);
}
