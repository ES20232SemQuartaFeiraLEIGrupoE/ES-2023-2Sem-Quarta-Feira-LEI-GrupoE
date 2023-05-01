package iscte.se.SE10.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iscte.se.SE10.model.Block;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Api {

    @GetMapping("/blocks")
    public String getBlocks() throws JsonProcessingException {
        // Retrieve blocks from your database or another source
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block("Disciplina 1", "2023-05-01", "08:30", "09:00" , "D104", "LEI", "manha", "Luis", "5", "10", "10"));

        List<Map<String, String>> result = new ArrayList<>();
        for (Block block : blocks) {
            Map<String, String> blockMap = new HashMap<>();
            blockMap.put("title", block.getCourse());
            blockMap.put("numeroInsc", block.getNumberOfSubscribers());
            blockMap.put("sizeRoom", block.getSizeRoom());
            result.add(blockMap);
        }

        ObjectMapper mapper = new ObjectMapper();
        return "[\n" +
                "{\n" +
                "\"title\": \"Disciplina 1\",\n" +
                "\"start\": \"2023-05-01T08:30:00\",\n" +
                "\"end\": \"2023-05-01T09:00:00\",\n" +
                "\"room\": \"D104\",\n" +
                "\"course\": \"LEI\",\n" +
                "\"turno\": \"manha\",\n" +
                "\"prof\": \"Luis\",\n" +
                "\"numeroInsc\": \"5\",\n" +
                "\"sizeRoom\": \"10\"\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"Disciplina 1\",\n" +
                "\"start\": \"2023-05-01T09:30:00\",\n" +
                "\"end\": \"2023-05-01T10:00:00\",\n" +
                "\"room\": \"D104\",\n" +
                "\"course\": \"LEI\",\n" +
                "\"turno\": \"manha\",\n" +
                "\"prof\": \"Luis\",\n" +
                "\"numeroInsc\": \"5\",\n" +
                "\"sizeRoom\": \"10\"\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"Disciplina 3\",\n" +
                "\"start\": \"2023-05-02T10:00:00\",\n" +
                "\"end\": \"2023-05-02T11:00:00\",\n" +
                "\"room\": \"D105\",\n" +
                "\"course\": \"LCC\",\n" +
                "\"turno\": \"tarde\",\n" +
                "\"prof\": \"Maria\",\n" +
                "\"numeroInsc\": \"7\",\n" +
                "\"sizeRoom\": \"20\"\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"Disciplina 4\",\n" +
                "\"start\": \"2023-05-02T11:30:00\",\n" +
                "\"end\": \"2023-05-02T12:30:00\",\n" +
                "\"room\": \"D105\",\n" +
                "\"course\": \"LCC\",\n" +
                "\"turno\": \"tarde\",\n" +
                "\"prof\": \"Maria\",\n" +
                "\"numeroInsc\": \"7\",\n" +
                "\"sizeRoom\": \"20\"\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"Disciplina 5\",\n" +
                "\"start\": \"2023-05-03T14:00:00\",\n" +
                "\"end\": \"2023-05-03T16:00:00\",\n" +
                "\"room\": \"D203\",\n" +
                "\"course\": \"MEI\",\n" +
                "\"turno\": \"noite\",\n" +
                "\"prof\": \"Joana\",\n" +
                "\"numeroInsc\": \"15\",\n" +
                "\"sizeRoom\": \"30\"\n" +
                "} ]";
    }
}
