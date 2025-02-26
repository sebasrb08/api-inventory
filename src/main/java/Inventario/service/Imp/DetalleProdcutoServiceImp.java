package Inventario.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Inventario.model.DetalleProducto;
import Inventario.repository.DetalleProductoRepository;
import Inventario.service.DetalleProductoService;

@Service
public class DetalleProdcutoServiceImp implements DetalleProductoService {

    @Autowired
    DetalleProductoRepository detalleProductoRepository;

    @Override
    public List<DetalleProducto> getAllDetailByIdUser(long userId) {
        return detalleProductoRepository.findAllByUsuarioId(userId);
        
    }

    @Override
    public Optional<DetalleProducto> getDetailById(long id,long userId) {
        return detalleProductoRepository.findByIdAndUsuarioId(id,userId);
    }
    
    public List<DetalleProducto> getAllDetailById(List<Long> id){
        return detalleProductoRepository.findAllById(id);
    }


    @Override
    public DetalleProducto saveDetail(DetalleProducto detalleProducto) {
        return detalleProductoRepository.save(detalleProducto);
    }

    @Override
    public DetalleProducto updateDetail(long id, DetalleProducto detalleProducto) {
        Optional<DetalleProducto> detalleId= detalleProductoRepository.findById(id);
        
        detalleId.get().setCantidad(detalleProducto.getCantidad());
        detalleId.get().setProducto(detalleProducto.getProducto());
        return detalleProductoRepository.save(detalleId.get());
    }

    @Override
    public String deleteDetail(long id,long userId) {
        Optional<DetalleProducto> detalleId= detalleProductoRepository.findById(id);
        if (detalleId.get() == null) {
            return "No se ha eliminado Los detalles";
        }
        if (detalleId.get().getUsuario().getId() != userId) {
            return "No tienes permiso para eliminar este detalle";
        }
        detalleProductoRepository.deleteByDetalleProductoIdAndUsuarioId(id, userId);
        return "Se han eliminado los Detalles";
    }
    
}
