package Inventario.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Inventario.model.Categoria;
import Inventario.repository.CategoriaRepository;
import Inventario.service.CategoriaService;

@Service
public class CategoriaServiceImp implements CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override

    public List<Categoria> getAllCategorieByIdUsuario(long userId){
  
        return categoriaRepository.findAllByUsuarioId(userId);
    }


    @Override
    public Optional<Categoria> getCategoriesById(long id,long userId) {
        return categoriaRepository.findByIdAndUsuarioId(id,userId);
    }

    @Override
    public Categoria saveCategorie(Categoria categoria) {
       return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria updateCategorie(long id, Categoria categoria) {
        Optional<Categoria> categoriaId = categoriaRepository.findById(id);
        categoriaId.get().setNombre(categoria.getNombre());

        return categoriaRepository.save(categoriaId.get());
    }

    @Override
    public String deleteCategorie(long id,long userId) {
        Optional<Categoria> categoriaId = categoriaRepository.findById(id);
        if (categoriaId.get() == null) {
            return "No se pudo encontrar la categoria";
        }

        if (categoriaId.get().getUsuario().getId() != userId) {
            return "No tienes permiso para eliminar este producto";
        }
        categoriaRepository.deleteByProductoIdAndUsuarioId(id,userId);
        return "Categoria eliminada";

    }
    
}
