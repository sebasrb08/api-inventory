package Inventario.service;

import java.util.List;
import java.util.Optional;

import Inventario.model.DetalleProducto;

public interface DetalleProductoService {
    public List<DetalleProducto> getAllDetailByIdUser(long userId);

    public Optional<DetalleProducto> getDetailById(long id,long userId);

    public DetalleProducto saveDetail(DetalleProducto detalleProducto);
    
    public DetalleProducto updateDetail(long id,DetalleProducto detalleProducto);

    public String deleteDetail(long id,long userId);

    public List<DetalleProducto> getAllDetailById(List<Long> id);

}
