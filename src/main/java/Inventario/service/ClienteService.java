package Inventario.service;

import java.util.List;
import java.util.Optional;


import Inventario.model.Cliente;

public interface ClienteService {
    public List<Cliente> getAllClientByIdUser(long userId);

    public Optional<Cliente> getClientById(long id,long userId);

    public Cliente saveClient(Cliente cliente);
    
    public Cliente updateClient(long id,Cliente cliente);

    public String deleteClient(long id,long userId);
}
