package com.maveric.springboot.backend.apirest.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

// importante para que sea reconocida como servicio

@Service
public class UploadFileServiceImpl implements IUploadFileService{

    // Para los logs
    private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
    private final static String DIRECTORIO_UPLOAD = "uploads";

    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {
        Path rutaArchivo = getPath(nombreFoto);
        log.info(rutaArchivo.toString());
//        log.trace("A TRACE Message");
//        log.debug("A DEBUG Message");
//        log.info("An INFO Message");
//        log.warn("A WARN Message");
//        log.error("An ERROR Message");

        Resource recurso = new UrlResource(rutaArchivo.toUri());

        if(!recurso.exists() && !recurso.isReadable()){
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-picture.jpg").toAbsolutePath();
            recurso = new UrlResource(rutaArchivo.toUri());
            log.error("Error no se pudo cargar la imagen: " + nombreFoto);
        }
        return  recurso;
    }

    @Override
    public String copiar(MultipartFile archivo, Long idCliente) throws IOException {
        String nombreArchivo = idCliente.toString() + "_" + UUID.randomUUID().toString() + "_" + requireNonNull(archivo.getOriginalFilename()).replace(" ","");
        Path rutaArchivo = getPath(nombreArchivo);

        log.info(rutaArchivo.toString(), "RutaArchivo-log");
        Files.copy(archivo.getInputStream(), rutaArchivo);
        return nombreArchivo;
    }

    @Override
    public boolean eliminar(String nombreFoto) {
        if(nombreFoto != null && nombreFoto.length() > 0) {
            Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
            File archivoFotoAnterior = rutaFotoAnterior.toFile();
            if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                archivoFotoAnterior.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {
        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
    }
}
