package Inventario.service;

import java.util.List;
import java.util.Optional;


import Inventario.model.Compra;
import Inventario.model.DetalleProducto;
public interface CompraService {
    public List<Compra> getAllBuyByIdUser(long id);

    public Optional<Compra> getBuyById(long id,long userId);

    public List<Compra> getBuyAllById(List<Long> id);

    public Compra saveBuy(Compra compra);
    
    public Compra updateBuy(long id,Compra compra);

    public String deleteBuy(long id,long userId);

     public long getTotal(List<DetalleProducto> detalles);
}
