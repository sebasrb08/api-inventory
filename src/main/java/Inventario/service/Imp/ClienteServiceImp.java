package Inventario.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Inventario.model.Cliente;
import Inventario.repository.ClienteRepository;
import Inventario.service.ClienteService;

@Service
public class ClienteServiceImp implements ClienteService {


    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllClientByIdUser(long userId) {
        return clienteRepository.findAllByUsuarioId(userId);
    }

    @Override
    public Optional<Cliente> getClientById(long id,long userId) {
        return clienteRepository.findByIdAndUsuarioId(id, userId);
    }

    @Override
    public Cliente saveClient(Cliente cliente) {
       return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateClient(long id, Cliente cliente) {
        Optional<Cliente> clienteId= clienteRepository.findById(id);

        clienteId.get().setNombre(cliente.getNombre());
        clienteId.get().setApellido(cliente.getApellido());
        clienteId.get().setEmail(cliente.getEmail());
        clienteId.get().setTelefono(cliente.getTelefono());

        return clienteRepository.save(clienteId.get());
    }

    @Override
    public String deleteClient(long id,long userId) {
        Optional<Cliente> clienteId = clienteRepository.findById(id);
        if (clienteId.get() == null) {
            return "Cliente no se pudo eliminar por id no valido";
        }
        if(clienteId.get().getUsuario().getId() != userId){
            return "No tienes permiso para eliminar este cliente";
        }
        clienteRepository.deleteByClienteIdAndUsuarioId(id,userId);
        return "Cliente Eliminado";

    }
    
}
