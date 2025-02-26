package Inventario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Inventario.model.DetalleProducto;
import jakarta.transaction.Transactional;

@Repository
public interface DetalleProductoRepository extends JpaRepository<DetalleProducto,Long> {
     List<DetalleProducto> findAllByUsuarioId(long userId);

    Optional<DetalleProducto> findByIdAndUsuarioId(long DetalleProductoId, long usuarioId);
    @Modifying
    @Transactional
    @Query("DELETE FROM DetalleProducto d WHERE d.id=:detalleProductoId  AND d.usuario.id = :usuarioId")
    void deleteByDetalleProductoIdAndUsuarioId(@Param("detalleProductoId") long detalleProductoId, @Param("usuarioId") long usuarioId);
}
