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

import Inventario.model.Cliente;
import Inventario.model.Usuario;
import Inventario.service.ClienteService;
import Inventario.service.UsuarioService;
import Inventario.util.CurrentUserUtil;

@RequestMapping("/cliente")
@RestController
@CrossOrigin(origins = {"http://localhost:8081","exp://192.168.80.10:8081"})

public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    CurrentUserUtil currentUserUtil;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<Cliente>> getAllClient() {
        long userId = currentUserUtil.getCurrentId();

        return new ResponseEntity<>(clienteService.getAllClientByIdUser(userId),HttpStatus.OK);  
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> getClientById(@PathVariable long id) {
        long userId = currentUserUtil.getCurrentId();

        return new ResponseEntity<>(clienteService.getClientById(id, userId),HttpStatus.OK);  
    }

    @PostMapping()
    public ResponseEntity<Cliente> saveClient(@RequestBody Cliente cliente) {
        long userId = currentUserUtil.getCurrentId();
        
        Optional<Usuario> usuario = usuarioService.getUserById(userId);

        cliente.setUsuario(usuario.get());
        return new ResponseEntity<>(clienteService.saveClient(cliente),HttpStatus.OK);  
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateClient(@PathVariable long id,@RequestBody Cliente cliente) {
        long userId = currentUserUtil.getCurrentId();
        
        Optional<Usuario> usuario = usuarioService.getUserById(userId);

        cliente.setUsuario(usuario.get());
        return new ResponseEntity<>(clienteService.updateClient(id,cliente),HttpStatus.OK);  
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable long id){
        long userId = currentUserUtil.getCurrentId();

        return new ResponseEntity<>(clienteService.deleteClient(id,userId),HttpStatus.OK);
    }
}
