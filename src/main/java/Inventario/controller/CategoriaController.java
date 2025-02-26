package Inventario.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Inventario.model.Categoria;
import Inventario.model.Usuario;
import Inventario.service.CategoriaService;
import Inventario.service.UsuarioService;
import Inventario.util.CurrentUserUtil;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping("/categoria")
@RestController
@CrossOrigin(origins = {"http://localhost:8081","exp://192.168.80.10:8081"})

public class CategoriaController {


    @Autowired
    CategoriaService categoriaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CurrentUserUtil currentUserUtil;
   

    @GetMapping()
    public ResponseEntity<List<Categoria>> getAllCategoriesByIdUser() {
        long userId = currentUserUtil.getCurrentId();
        System.out.println(userId);
        return new ResponseEntity<>(categoriaService.getAllCategorieByIdUsuario(userId),HttpStatus.OK);  
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> getCategoriesById(@PathVariable long id) {
        long userId = currentUserUtil.getCurrentId();

        return new ResponseEntity<>(categoriaService.getCategoriesById(id,userId),HttpStatus.OK);  
    }

    @PostMapping()
    public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
        long userId = currentUserUtil.getCurrentId();

        Optional<Usuario> usuario = usuarioService.getUserById(userId);

        categoria.setUsuario(usuario.get());

        return new ResponseEntity<>(categoriaService.saveCategorie(categoria),HttpStatus.OK);  
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable long id,@RequestBody Categoria categoria) {
        long userId = currentUserUtil.getCurrentId();

        Optional<Usuario> usuario = usuarioService.getUserById(userId);

        categoria.setUsuario(usuario.get());
        return new ResponseEntity<>(categoriaService.updateCategorie(id,categoria),HttpStatus.OK);  
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategorie(@PathVariable long id){
        long userId = currentUserUtil.getCurrentId();

        return new ResponseEntity<>(categoriaService.deleteCategorie(id,userId),HttpStatus.OK);
    }
    
}
