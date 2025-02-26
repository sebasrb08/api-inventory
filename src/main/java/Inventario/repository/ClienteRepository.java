package Inventario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Inventario.model.Cliente;
import jakarta.transaction.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
     List<Cliente> findAllByUsuarioId(long userId);

    Optional<Cliente> findByIdAndUsuarioId(long clienteId, long usuarioId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Cliente c WHERE c.id=:clienteId  AND c.usuario.id = :usuarioId")
    void deleteByClienteIdAndUsuarioId(@Param("clienteId") long clienteId, @Param("usuarioId") long usuarioId);
}
