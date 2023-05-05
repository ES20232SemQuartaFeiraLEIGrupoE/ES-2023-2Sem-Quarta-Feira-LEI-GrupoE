package iscte.se.SE10.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import iscte.se.SE10.model.Block;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileReader {

    public static List<Block> readCSV(InputStream inputStream) {
        List<Block> blocks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                Block block = Block.createFromCSV(line);
                blocks.add(block);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return blocks;
    }

    public static List<Block> readJson(InputStream inputStream, String type) {
        List<Block> blocks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Gson gson = new Gson();
            List<Map<String, String>> data = gson.fromJson(reader, new TypeToken<List<Map<String, String>>>(){}.getType());
            for (Map<String, String> map : data) {
                Block block = ("web".equals(type))? Block.createFromScheduleFormat(map) : new Block(map);
                blocks.add(block);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blocks;
    }
}
