package Inventario.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Inventario.jwt.JwtService;
import Inventario.model.Producto;
import Inventario.repository.ProductoRepository;
import Inventario.service.ProductoService;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    JwtService jwtService;



    @Override
    public List<Producto> getAllProductByIdUsuario(long userId) {


        return productoRepository.findAllByUsuarioId(userId);
    }

    @Override
    public Optional<Producto> getProductById(long productoId, long userId) {
       return productoRepository.findByIdAndUsuarioId(productoId,userId);
    }

    public List<Producto> getProductsById(List<Long> id) {
        return productoRepository.findAllById(id);
     }

    @Override
    public Producto saveProduct(Producto producto) {
       return productoRepository.save(producto);
    }

    public List<Producto>saveAllProduct(List<Producto> productos){
        return productoRepository.saveAll(productos);
    }

    @Override
    public Producto updateProduct(long id, Producto producto) {
        Optional<Producto>productoId= productoRepository.findById(id);
        productoId.get().setNombre(producto.getNombre());
        productoId.get().setPrecio(producto.getPrecio());
        productoId.get().setCantidad(producto.getCantidad());
        productoId.get().setCategoria(producto.getCategoria());
        productoId.get().setDescripcion(producto.getDescripcion());
        productoId.get().setSku(producto.getSku());
        productoId.get().setImage(producto.getImage());
        return productoRepository.save(productoId.get());
    }

    @Override
    public String deleteProduct(long id,long userId) {
        Optional<Producto>productoId= productoRepository.findById(id);
        if (productoId.get() == null) {
            return "Producto no fue eliminado";
        }

        if (productoId.get().getUsuario().getId() != userId) {
            return "No tienes permiso para eliminar este producto";
        }
        productoRepository.deleteByProductoIdAndUsuarioId(id,userId );
        return "Producto eliminado";

    }

    public void restCantidad(long id, int cantidad){
        Optional<Producto>productoId= productoRepository.findById(id);
        productoId.get().setCantidad(productoId.get().getCantidad()-cantidad);
        productoRepository.save(productoId.get());
    }
    
}
