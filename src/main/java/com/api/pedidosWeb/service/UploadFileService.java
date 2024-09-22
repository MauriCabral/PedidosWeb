package com.api.pedidosWeb.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.api.pedidosWeb.repository.IUploadFileRepository;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService implements IUploadFileRepository {

    private final static String UPLOADS_FOLDER = "src/main/resources/static/uploads";

    public Resource load(String filename) throws MalformedURLException {
        Path filePath = Paths.get("src/main/resources/static/uploads").resolve(filename).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Failed to load file: " + filename);
        }
    }

    @Override
    public String copy(MultipartFile file, String existingFilename) throws IOException {
        if (existingFilename != null) {
            delete(existingFilename);
        }

        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);
        Files.copy(file.getInputStream(), rootPath);
        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File file = rootPath.toFile();
        if(file.exists() && file.canRead()) {
            if(file.delete()) {
                return true;
            }
        }
        return false;
    }

    private Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename);
    }
}
