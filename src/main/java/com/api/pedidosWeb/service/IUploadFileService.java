package com.api.pedidosWeb.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IUploadFileService {

    Resource load(String filename) throws MalformedURLException;

    String copy(MultipartFile file, String existingFilename) throws IOException;

    void delete(String filename);
}
