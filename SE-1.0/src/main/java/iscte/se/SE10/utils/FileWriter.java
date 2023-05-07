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
 * @author Grupo E
 * @version 1.0
 */

public class FileWriter {

    /**
     * Construtor default
     */
    public FileWriter(){
        // No Initialization required
    }

    /**
     * Função que escreve o conteúdo de uma lista de blocks recebida num ficheiro CSV
     * @param data lista de objetos Block
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

    /**
     * Função que escreve o conteúdo de uma lista de blocks recebida num ficheiro JSON
     * @param data lista de objetos Block
     */
    public static void saveInJson(List<Block> data) {
        try {
            PrintWriter writer = new PrintWriter("schedule.json");
            writer.println('[');
            for (int i = 0; i<data.size() -1 ; i++) {
                writer.println(data.get(i).getAsJson() + ',');
            }
            writer.println(data.get(data.size()-1).getAsJson());
            writer.println(']');
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    /**
     * Função que guarda o conteúdo do objeto MultipartFile num ficheiro JSON ou CSV
     * @param file objeto MultipartFile que é o arquivo que queremos guardar
     * @param extension string que define o formato do ficheiro que iremos guardar
     * @return retorna uma string indicativa de que o ficheiro foi guardado
     * @throws IOException Input/Output exception
     */

    public static String save(MultipartFile file, String extension) throws IOException {
        List<Block> data = readJson(file.getInputStream(), "web");

        if ("csv".equals(extension))
            saveInCSV(data);

        if ("json".equals(extension))
            saveInJson(data);

        return "File saved ";
    }

    /**
     * Função que muda o formato dos blocks e passa para uma String JSON
     * @param data lista de blocks
     * @return retorna uma String JSON
     */
    public static String formatToWeb(List<Block> data) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Block block : data) {
            result.add(block.getAsScheduleFormat());
        }
        Gson gson = new Gson();
        return gson.toJson(result);
    }
}