package iscte.se.SE10.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iscte.se.SE10.model.Block;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static iscte.se.SE10.utils.ConvertFiles.csvToJson2;

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
        System.out.println(file.getOriginalFilename());

        String json = csvToJson2(file.getInputStream());

        return json;
    }

    // grava localmente
    @PostMapping("/save")
    public String saveLocal(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println("File name: " + file.getOriginalFilename());
        System.out.println("File size: " + file.getSize() + " bytes");

        String filename = "output.json";
        Path path = Paths.get(filename);

        // Transfer the file to the output path
        file.transferTo(path);

        System.out.println("File saved to " + path);

        return "File saved to " + path;
    }

}
