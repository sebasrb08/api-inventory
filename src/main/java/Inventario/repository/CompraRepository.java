package Inventario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Inventario.model.Compra;
import jakarta.transaction.Transactional;
@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {
    List<Compra> findByUsuarioId(long id);

    Optional<Compra> findByIdAndUsuarioId(long compraId, long usuarioId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Compra p WHERE p.id=:compraId  AND p.usuario.id = :usuarioId")
    void deleteByCompraIdAndUsuarioId(@Param("compraId") long compraId, @Param("usuarioId") long usuarioId);
}
