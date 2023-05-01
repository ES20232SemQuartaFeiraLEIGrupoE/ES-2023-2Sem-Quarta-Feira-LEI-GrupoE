package iscte.se.SE10.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iscte.se.SE10.model.Block;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static iscte.se.SE10.utils.ConvertFiles.csvToJson2;

@RestController
@RequestMapping("/api")
public class Api {

    @PostMapping("/blocks")
    public String getBlocks(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());

        String json = csvToJson2(file.getInputStream());

        return json;
    }

//                "[\n" +
//                "{\n" +
//                "\"title\": \"Disciplina 1\",\n" +
//                "\"start\": \"2023-05-01T08:30:00\",\n" +
//                "\"end\": \"2023-05-01T09:00:00\",\n" +
//                "\"room\": \"D104\",\n" +
//                "\"course\": \"LEI\",\n" +
//                "\"turno\": \"manha\",\n" +
//                "\"prof\": \"Luis\",\n" +
//                "\"numeroInsc\": \"5\",\n" +
//                "\"sizeRoom\": \"10\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"title\": \"Disciplina 1\",\n" +
//                "\"end\": \"2023-05-01T10:00:00\",\n" +
//                "\"room\": \"D104\",\n" +
//                "\"course\": \"LEI\",\n" +
//                "\"turno\": \"manha\",\n" +
//                "\"prof\": \"Luis\",\n" +
//                "\"numeroInsc\": \"5\",\n" +
//                "\"sizeRoom\": \"10\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"title\": \"Disciplina 3\",\n" +
//                "\"start\": \"2023-05-02T10:00:00\",\n" +
//                "\"end\": \"2023-05-02T11:00:00\",\n" +
//                "\"room\": \"D105\",\n" +
//                "\"course\": \"LCC\",\n" +
//                "\"turno\": \"tarde\",\n" +
//                "\"prof\": \"Maria\",\n" +
//                "\"numeroInsc\": \"7\",\n" +
//                "\"sizeRoom\": \"20\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"title\": \"Disciplina 4\",\n" +
//                "\"start\": \"2023-05-02T11:30:00\",\n" +
//                "\"end\": \"2023-05-02T12:30:00\",\n" +
//                "\"room\": \"D105\",\n" +
//                "\"course\": \"LCC\",\n" +
//                "\"turno\": \"tarde\",\n" +
//                "\"prof\": \"Maria\",\n" +
//                "\"numeroInsc\": \"7\",\n" +
//                "\"sizeRoom\": \"20\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"title\": \"Disciplina 5\",\n" +
//                "\"start\": \"2023-05-03T14:00:00\",\n" +
//                "\"end\": \"2023-05-03T16:00:00\",\n" +
//                "\"room\": \"D203\",\n" +
//                "\"course\": \"MEI\",\n" +
//                "\"turno\": \"noite\",\n" +
//                "\"prof\": \"Joana\",\n" +
//                "\"numeroInsc\": \"15\",\n" +
//                "\"sizeRoom\": \"30\"\n" +
//                "} ]";

}
