package Inventario.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Inventario.Dto.ProductoDto;
import Inventario.model.Categoria;
import Inventario.model.Producto;
import Inventario.model.Usuario;
import Inventario.service.CategoriaService;
import Inventario.service.CompraService;
import Inventario.service.ProductoService;
import Inventario.service.UsuarioService;
import Inventario.util.CurrentUserUtil;

@RestController
@RequestMapping("/producto")

public class ProductoController {
    @Autowired
    ProductoService productoService;

    @Autowired
    CompraService compraService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CurrentUserUtil currentUserUtil;


    @GetMapping()
    public ResponseEntity<List<Producto>> getAllProductByIdUser() {

        long userId = currentUserUtil.getCurrentId();

        return new ResponseEntity<>(productoService.getAllProductByIdUsuario(userId),HttpStatus.OK);  
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Producto>> getProductById(@PathVariable long id) {
        long userId = currentUserUtil.getCurrentId();


        return new ResponseEntity<>(productoService.getProductById(id,userId),HttpStatus.OK);  
    }

    @PostMapping()
    public ResponseEntity<Producto> saveProduct(@RequestBody() ProductoDto productoDto) {
        
                // Guardar el producto
                long userId = currentUserUtil.getCurrentId();
                Optional<Categoria> categoria = categoriaService.getCategoriesById(productoDto.getId_categoria(), userId);
                Optional<Usuario> usuario = usuarioService.getUserById(userId);
    
                Producto producto = Producto.builder()
                    .nombre(productoDto.getNombre())
                    .cantidad(productoDto.getCantidad())
                    .sku(productoDto.getSku())
                    .precio(productoDto.getPrecio())
                    .descripcion(productoDto.getDescripcion())
                    .categoria(categoria.get())  // Manejo de posibles excepciones
                    .usuario(usuario.get())  // Manejo de posibles excepciones
                    .build();
                return new ResponseEntity<>(productoService.saveProduct(producto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProduct(@PathVariable long id,@RequestBody ProductoDto productoDto) {

      
                // Guardar el producto
                long userId = currentUserUtil.getCurrentId();
                Optional<Usuario> usuario = usuarioService.getUserById(userId);
                Optional<Categoria> categoria = categoriaService.getCategoriesById(productoDto.getId_categoria(), userId);

                Producto producto = Producto.builder()
                    .id(id)
                    .nombre(productoDto.getNombre())
                    .cantidad(productoDto.getCantidad())
                    .sku(productoDto.getSku())
                    .precio(productoDto.getPrecio())
                    .descripcion(productoDto.getDescripcion())
                    .categoria(categoria.get())  // Manejo de posibles excepciones
                    .image(productoDto.getImage())
                    .usuario(usuario.get())  
                    .build();
    
                return new ResponseEntity<>(productoService.saveProduct(producto), HttpStatus.OK);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id){
        long userId = currentUserUtil.getCurrentId();

        return new ResponseEntity<>(productoService.deleteProduct(id,userId),HttpStatus.OK);
    }
}
