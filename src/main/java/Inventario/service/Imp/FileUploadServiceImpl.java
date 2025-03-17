package Inventario.service.Imp;

import Inventario.model.Producto;
import Inventario.service.FileUploadService;
import Inventario.service.ProductoService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    Cloudinary cloudinary;

    @Autowired
    ProductoService productoService;

    @Override
    public Producto upload(long id, MultipartFile file,long userId) {

        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "webp", "avif");


        Optional<Producto> producto = productoService.getProductById(id, userId);

        String extensions = null;

        if (file.getOriginalFilename() != null) {
            String[] splitName = file.getOriginalFilename().split("\\.");
            extensions = splitName[splitName.length - 1];
        }

        try {

            Map<String, Object> resultUpload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "inventory"));

            String imageUrl = resultUpload.get("secure_url").toString();

            producto.get().setImage(imageUrl);

            productoService.updateProduct(id, producto.get());
            return producto.get();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
