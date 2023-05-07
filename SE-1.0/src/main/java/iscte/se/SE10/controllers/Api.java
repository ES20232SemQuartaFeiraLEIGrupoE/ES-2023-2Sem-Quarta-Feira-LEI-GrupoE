package iscte.se.SE10.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static iscte.se.SE10.utils.FileReader.*;
import static iscte.se.SE10.utils.FileWriter.*;

/**
 * Classe que implementa a API-RESTful
 *
 * @author Grupo E
 * @version 1.0
 */

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api")
public class Api {

    /**
     * Construtor default
     */
    public Api() {
        // No Initialization required
    }

    /**
     * Método que lê o objeto MultipartFile e consoante o formato de defenido devolve uma String nesse formato
     *
     * @param file MultipartFile que é o arquivo que queremos guardar
     * @return retorna uma String num formato CSV ou JSON consoante o formato lido no objeto MultipartFile
     * ou vazia na situação em que não encontra nenhum dos dois formatos
     * @throws IOException Input/Output exception
     */
    @PostMapping("/blocks")
    public String getBlocks(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (fileName != null) {
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

            if ("csv".equals(extension))
                return formatToWeb(readCSV(file.getInputStream()));

            if ("json".equals(extension))
                return formatToWeb(readJson(file.getInputStream(), "local"));

        }
        return "[]";
    }

    /**
     * Método que recebe um endereço e devolve uma lista de objetos Block em formato WEB
     *
     * @param uri endereço do ficheiro remoto
     * @return retorna uma String JSON com o conteúdo da lista de objetos Block
     */
    @PostMapping("/web")
    public String getBlocks2(@RequestParam String uri) {
        return formatToWeb(readIcs(uri));
    }

    /**
     * Método que recebe um objeto MultipartFile e chama a função para guardar no formato CSV
     *
     * @param file objeto MultipartFile que é o arquivo que queremos guardar
     * @return retorna uma String com status da ação
     * @throws IOException Input/Output exception
     */
    // grava localmente
    @PostMapping("/savecsv")
    public ResponseEntity<Resource> saveCsv(@RequestParam("file") MultipartFile file) throws IOException {
        File savedFile = save(file, "csv");
        Path path = savedFile.toPath();
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .contentLength(savedFile.length())
                .contentType(MediaType.parseMediaType("application/json"))
                .body(resource);
    }

    /**
     * Método que recebe um objeto MultipartFile e chama a função para guardar no formato JSON
     *
     * @param file objeto MultipartFile que é o arquivo que queremos guardar
     * @return retorna uma String com status da ação
     * @throws IOException Input/Output exception
     */
    // grava localmente
    @PostMapping("/savejson")
    public ResponseEntity<Resource> saveJson(@RequestParam("file") MultipartFile file) throws IOException {
        File savedFile = save(file, "json");
        Path path = savedFile.toPath();
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .contentLength(savedFile.length())
                .contentType(MediaType.parseMediaType("application/json"))
                .body(resource);
    }
}
