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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Inventario.Dto.CompraDto;
import Inventario.model.Cliente;
import Inventario.model.Compra;
import Inventario.model.DetalleProducto;
import Inventario.model.Usuario;
import Inventario.service.ClienteService;
import Inventario.service.CompraService;
import Inventario.service.DetalleProductoService;
import Inventario.service.UsuarioService;
import Inventario.util.CurrentUserUtil;

@RequestMapping("/compra")
@RestController

public class CompraController {

    @Autowired
    CompraService compraService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    DetalleProductoService detalleService;

    @Autowired 
    UsuarioService usuarioService;

    @Autowired
    CurrentUserUtil currentUserUtil;

    @GetMapping()
    public ResponseEntity<List<Compra>> getAllCompraByIdUser() {
        long userId = currentUserUtil.getCurrentId();

        return new ResponseEntity<>(compraService.getAllBuyByIdUser(userId),HttpStatus.OK);  
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Compra>> getCompraById(@PathVariable long id) {
        long userId = currentUserUtil.getCurrentId();

        return new ResponseEntity<>(compraService.getBuyById(id,userId),HttpStatus.OK);  
    }

    @PostMapping()
    public ResponseEntity<Compra> saveCompra(@RequestBody CompraDto compraDto) {
        long userId = currentUserUtil.getCurrentId();

        Optional<Usuario> usuario = usuarioService.getUserById(userId);
        Optional<Cliente> cliente =clienteService.getClientById(compraDto.getId_cliente(),userId);

        List<DetalleProducto> detalles = detalleService.getAllDetailById(compraDto.getDetalleProductos());

        Compra compra= Compra.builder()
        .cliente(cliente.get())
        .detalleProductos(detalles)
        .usuario(usuario.get())
        .build();

        return new ResponseEntity<>(compraService.saveBuy(compra),HttpStatus.OK);  
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable long id,@RequestBody CompraDto compraDto) {

        long userId = currentUserUtil.getCurrentId();

        Optional<Usuario> usuario = usuarioService.getUserById(userId);
        Optional<Cliente> cliente =clienteService.getClientById(compraDto.getId_cliente(),userId);

        List<DetalleProducto> detalles = detalleService.getAllDetailById(compraDto.getDetalleProductos());
        Compra compra= Compra.builder()
        .cliente(cliente.get())
        .detalleProductos(detalles)
        .usuario(usuario.get())
        .build();

        

        return new ResponseEntity<>(compraService.updateBuy(id,compra),HttpStatus.OK);  
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompra(@PathVariable long id){
        long userId = currentUserUtil.getCurrentId();
        return new ResponseEntity<>(compraService.deleteBuy(id,userId),HttpStatus.OK);
    }

    
}
