package iscte.se.SE10.utils;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;
import iscte.se.SE10.model.Block;


import java.util.Map;


import org.springframework.web.multipart.MultipartFile;

import static iscte.se.SE10.utils.FileReader.readJson;

/**
 * Classe para ler e escrever em ficheiros
 *
 * @author Grupo E
 * @version 1.0
 */

public class FileWriter {

    /**
     * Função que recebe o nome para um ficheiro que será criado através de uma lista de blocks
     *
     * @param data lista de blocks
     */
    public static void saveInCSV(List<Block> data) {
        try {
            PrintWriter writer = new PrintWriter("schedule.csv");

            writer.println(Block.getCSVHeader()); // cabeçalho do arquivo
            for (Block b : data)
                writer.println(b.getAsCsv());
            writer.close();
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo CSV: " + e.getMessage());
        }
    }


    public static void saveInJson(List<Block> data) {
        try {
            PrintWriter writer = new PrintWriter("schedule.json");
            for (Block block : data) {
                writer.println(block.getAsJson());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    public static String save(MultipartFile file, String extension) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println("File name: " + file.getOriginalFilename());
        System.out.println("File size: " + file.getSize() + " bytes");

        // parse the info
        List<Block> data = readJson(file.getInputStream(), "web");

        // Transfer the file to the output path
        if ("csv".equals(extension))
            saveInCSV(data);

        if ("json".equals(extension))
            saveInJson(data);

        return "File saved ";
    }

    public static String formatToWeb(List<Block> data) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Block block : data) {
            result.add(block.getAsScheduleFormat());
        }
        Gson gson = new Gson();
        String json = gson.toJson(result);
        return json;
    }
}