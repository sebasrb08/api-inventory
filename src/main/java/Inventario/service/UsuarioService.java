package Inventario.service;

import java.util.List;
import java.util.Optional;

import Inventario.model.Usuario;

public interface UsuarioService {
    public List<Usuario> getAllUser();

    public Optional<Usuario> getUserById(long id);

    public Usuario saveUser(Usuario usuario);
    
    public Usuario updateUser(long id,Usuario usuario);

    public String deleteUser(long id);
}
