package Inventario.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Inventario.model.Usuario;
import Inventario.repository.UsuarioRepository;
import Inventario.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUser() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUserById(long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario saveUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUser(long id, Usuario usuario) {
        Optional<Usuario> usuarioId = usuarioRepository.findById(id);
        usuarioId.get().setNombre(usuario.getNombre());
        usuarioId.get().setApellido(usuario.getApellido());
        usuarioId.get().setEmail(usuario.getEmail());
        usuarioId.get().setTelefono(usuario.getTelefono());
        usuarioId.get().setPassword(usuario.getPassword());
        usuarioId.get().setUsername(usuario.getUsername());
        
        return usuarioRepository.save(usuarioId.get());
    }


    @Override
    public String deleteUser(long id) {
        Optional<Usuario> usuarioId = usuarioRepository.findById(id);
        if (usuarioId.get() == null) {
            return "El usuario no ha podido ser eliminado";
        }
        usuarioRepository.delete(usuarioId.get());
        return"El usuario se ha eliminado";
    }
    
}
