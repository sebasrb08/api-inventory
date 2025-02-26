package Inventario.service.Imp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Inventario.model.Compra;
import Inventario.model.DetalleProducto;
import Inventario.repository.CompraRepository;
import Inventario.service.CompraService;

@Service
public class CompraServiceImp implements CompraService {

    @Autowired
    CompraRepository compraRepository;

    @Override
    public List<Compra> getAllBuyByIdUser(long id) {
        return compraRepository.findByUsuarioId(id);
    }

    @Override
    public Optional<Compra> getBuyById(long id,long userId) {
       return compraRepository.findByIdAndUsuarioId(id,userId);
    }

    public List<Compra> getBuyAllById(List<Long> id){
        return compraRepository.findAllById(id);
    }


    @Override
    public Compra saveBuy(Compra compra) {
        long total = getTotal(compra.getDetalleProductos());
        compra.setTotal(total);

        LocalDate fecha = LocalDate.now();
        compra.setFecha(fecha);
        
        return compraRepository.save(compra);
    }

    @Override
    public Compra updateBuy(long id, Compra compra) {
        Optional<Compra>compraId = compraRepository.findById(id); 
        LocalDate fecha = LocalDate.now();
        compraId.get().setFecha(fecha);
        compraId.get().setCliente(compra.getCliente());
        compraId.get().setDetalleProductos(compra.getDetalleProductos());
        compraId.get().setTotal(compra.getTotal());
        return compraRepository.save(compraId.get());
    }

    @Override
    public String deleteBuy(long id, long userId) {
        Optional<Compra> compraId= compraRepository.findById(id);
        if (compraId.get() == null) {
            return "Compra no se pudo eliminar por id no valido";
        }
        if (compraId.get().getUsuario().getId() != userId) {
            return "No tienes permiso para eliminar este producto";
        }
        compraRepository.deleteByCompraIdAndUsuarioId(id,userId);
        return "Compra Eliminado";
    }
    
    public long getTotal(List<DetalleProducto> detalles){
        long total=0;
        for(DetalleProducto producto: detalles){
            total += (producto.getProducto().getPrecio() * producto.getCantidad());
        }
        return total;
    }


}
