package Inventario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Inventario.Dto.DetalleProductoDto;
import Inventario.model.DetalleProducto;
import Inventario.model.Producto;
import Inventario.model.Usuario;
import Inventario.service.CompraService;
import Inventario.service.DetalleProductoService;
import Inventario.service.ProductoService;
import Inventario.service.UsuarioService;
import Inventario.util.CurrentUserUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping("/detalle")
@RestController
@CrossOrigin(origins = {"http://localhost:8081","exp://192.168.80.10:8081"})

public class DetalleProductoController {
    @Autowired
    DetalleProductoService detalleProductoService;

    @Autowired
    ProductoService productoService;

    @Autowired
    CompraService compraService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CurrentUserUtil currentUserUtil;

    @GetMapping
    public ResponseEntity<List<DetalleProducto>> getAllDetail(){
        long userId = currentUserUtil.getCurrentId();
        return new ResponseEntity<>(detalleProductoService.getAllDetailByIdUser(userId),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity< Optional<DetalleProducto>> getDetailById(@PathVariable long id) {
        long userId = currentUserUtil.getCurrentId();

        return new ResponseEntity<>(detalleProductoService.getDetailById(id,userId),HttpStatus.OK) ;
    }
    
    @PostMapping()
    public ResponseEntity<DetalleProducto> saveDetail(@RequestBody DetalleProductoDto detalleProductoDto) {
        long userId = currentUserUtil.getCurrentId();

        Optional<Usuario> usuario =  usuarioService.getUserById(userId);
        Optional<Producto> producto = productoService.getProductById(detalleProductoDto.getId_producto(),userId);

        productoService.restCantidad(detalleProductoDto.getId_producto(),detalleProductoDto.getCantidad());
        
        DetalleProducto detalle = DetalleProducto.builder()
        .cantidad(detalleProductoDto.getCantidad())
        .producto(producto.get())
        .usuario(usuario.get())
        .build();

        return new ResponseEntity<>(detalleProductoService.saveDetail(detalle),HttpStatus.OK) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleProducto> updateDetail(@PathVariable long id,@RequestBody DetalleProductoDto detalleProductoDto) {
        long userId = currentUserUtil.getCurrentId();
        Optional<Usuario> usuario =  usuarioService.getUserById(userId);
        Optional<Producto> producto = productoService.getProductById(detalleProductoDto.getId_producto(),userId);



        DetalleProducto detalle = DetalleProducto.builder()
        .cantidad(detalleProductoDto.getCantidad())
        .producto(producto.get())
        .usuario(usuario.get())
        .build();

        return  new ResponseEntity<>(detalleProductoService.updateDetail(id,detalle), HttpStatus.OK);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDetail(@PathVariable long id){
        long userId = currentUserUtil.getCurrentId();

        return new ResponseEntity<>(detalleProductoService.deleteDetail(id,userId),HttpStatus.OK);
    }

    


}
