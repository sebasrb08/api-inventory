package Inventario.service;

import Inventario.model.Producto;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    Producto upload(long id, MultipartFile file, long userId);
}
