package Inventario.service;

import java.util.List;
import java.util.Optional;


import Inventario.model.Categoria;

public interface CategoriaService {

    public List<Categoria> getAllCategorieByIdUsuario(long userId);

    public Optional<Categoria> getCategoriesById(long id,long userId);

    public Categoria saveCategorie(Categoria categoria);
    
    public Categoria updateCategorie(long id,Categoria categoria);

    public String deleteCategorie(long id, long userId);

}


