package Inventario.controller;

import Inventario.service.FileUploadService;
import Inventario.util.CurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")

public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    CurrentUserUtil currentUserUtil;

    @PatchMapping("/producto/{id}")
    public ResponseEntity <?> upload(@PathVariable long id, @RequestParam("image") MultipartFile file) {
        long userId = currentUserUtil.getCurrentId();

        return ResponseEntity.ok(fileUploadService.upload(id, file, userId));
    }
}
