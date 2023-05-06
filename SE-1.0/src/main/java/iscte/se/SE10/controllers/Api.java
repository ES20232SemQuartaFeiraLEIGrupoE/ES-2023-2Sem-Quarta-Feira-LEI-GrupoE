package iscte.se.SE10.controllers;



import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


import static iscte.se.SE10.utils.FileReader.*;
import static iscte.se.SE10.utils.FileWriter.*;

/**
 *
 * @author Grupo E
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class Api {

    // Lê csv e retorna um json. Falta ler json
    @PostMapping("/blocks")
    public String getBlocks(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("I was callledddd");
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        if ("csv".equals(extension))
            return formatToWeb( readCSV(file.getInputStream()));

        if ("json".equals(extension))
            return formatToWeb( readJson(file.getInputStream(), "local"));

        return "[]";
    }

    // Lê csv e retorna um json. Falta ler json
    @PostMapping("/web")
    public String getBlocks2(@RequestParam String uri) throws IOException {
        System.out.println("I was callllllledddd");
        return formatToWeb(readIcs(uri));
    }

    // grava localmente
    @PostMapping("/savecsv")
    public String saveCsv(@RequestParam("file") MultipartFile file) throws IOException {
        return save(file, "csv");
    }

    // grava localmente
    @PostMapping("/savejson")
    public String saveJson(@RequestParam("file") MultipartFile file) throws IOException {
        return save(file, "json");
    }




}
