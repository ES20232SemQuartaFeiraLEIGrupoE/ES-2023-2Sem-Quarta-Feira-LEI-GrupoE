package iscte.se.SE10.controllers;



import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


import static iscte.se.SE10.utils.FileReader.*;
import static iscte.se.SE10.utils.FileWriter.*;

/**
 * Classe que implementa a API-RESTful
 * @author Grupo E
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class Api {

    /**
     * Construtor default
     */
    public Api(){}
    /**
     * Método que lê o objeto MultipartFile e consoante o formato de defenido devolve uma String nesse formato
     * @param file MultipartFile que é o arquivo que queremos guardar
     * @return retorna uma String num formato CSV ou JSON consoante o formato lido no objeto MultipartFile
     * ou vazia na situação em que não encontra nenhum dos dois formatos
     * @throws IOException Input/Output exception
     */
    @PostMapping("/blocks")
    public String getBlocks(@RequestParam("file") MultipartFile file) throws IOException {
        //System.out.println("I was callledddd");
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        if ("csv".equals(extension))
            return formatToWeb( readCSV(file.getInputStream()));

        if ("json".equals(extension))
            return formatToWeb( readJson(file.getInputStream(), "local"));

        return "[]";
    }

    /**
     * Método que recebe um endereço e devolve uma lista de objetos Block em formato WEB
     * @param uri endereço do ficheiro remoto
     * @return retorna uma String JSON com o conteúdo da lista de objetos Block
     * @throws IOException Input/Output exception
     */
    @PostMapping("/web")
    public String getBlocks2(@RequestParam String uri) throws IOException {
        //System.out.println("I was callllllledddd");
        return formatToWeb(readIcs(uri));
    }

    /**
     * Método que recebe um objeto MultipartFile e chama a função para guardar no formato CSV
     * @param file objeto MultipartFile que é o arquivo que queremos guardar
     * @return retorna uma String com status da ação
     * @throws IOException Input/Output exception
     */
    // grava localmente
    @PostMapping("/savecsv")
    public String saveCsv(@RequestParam("file") MultipartFile file) throws IOException {
        return save(file, "csv");
    }

    /**
     * Método que recebe um objeto MultipartFile e chama a função para guardar no formato JSON
     * @param file objeto MultipartFile que é o arquivo que queremos guardar
     * @return retorna uma String com status da ação
     * @throws IOException Input/Output exception
     */
    // grava localmente
    @PostMapping("/savejson")
    public String saveJson(@RequestParam("file") MultipartFile file) throws IOException {
        return save(file, "json");
    }
}
