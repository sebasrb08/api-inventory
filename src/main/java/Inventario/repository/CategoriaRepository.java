package Inventario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Inventario.model.Categoria;
import jakarta.transaction.Transactional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    List<Categoria> findAllByUsuarioId(long userId);

    Optional<Categoria> findByIdAndUsuarioId(long categoriaId,long userId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Categoria c WHERE c.id=:categoriaId AND c.usuario.id = :usuarioId")
    void deleteByProductoIdAndUsuarioId(@Param("categoriaId")long categoriaId, @Param("usuarioId") long usuarioId);

}
