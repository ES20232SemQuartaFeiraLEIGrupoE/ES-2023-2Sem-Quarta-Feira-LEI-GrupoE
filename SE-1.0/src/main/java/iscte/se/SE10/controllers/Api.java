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
        return mapper.writeValueAsString(result);
    }
}
