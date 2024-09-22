package com.api.pedidosWeb.repository;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface IUploadFileRepository {

    public Resource load(String filename) throws MalformedURLException;

    String copy(MultipartFile file, String existingFilename) throws IOException;

    public boolean delete(String filename);
}
