package Inventario.service;

import java.util.List;
import java.util.Optional;


import Inventario.model.Producto;

public interface ProductoService {
    public List<Producto> getAllProductByIdUsuario(long userId);

    public Optional<Producto> getProductById(long id, long userId);

    public List<Producto> getProductsById(List<Long> id) ;

    public Producto saveProduct(Producto producto);

    public List<Producto>saveAllProduct(List<Producto> productos);
    
    public Producto updateProduct(long id,Producto producto);

    public String deleteProduct(long id, long userId);

    public void restCantidad(long id, int cantidad);
}
