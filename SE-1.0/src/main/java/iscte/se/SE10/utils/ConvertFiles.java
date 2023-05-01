package iscte.se.SE10.utils;

import java.io.*;

import java.util.*;

import com.aspose.cells.*;
import com.google.gson.Gson;
import iscte.se.SE10.model.Block;
import org.apache.commons.io.FilenameUtils;


/**
 * Classe para guardar e converter ficheiros
 *
 * @author Grupo E
 * @version 1.0
 */
public class ConvertFiles {

    /**
     * Função que converte um ficheiro .csv para .json
     *
     * @param csv_file ficheiro .csv a converter
     * @param path     path para onde queremos guardar o ficheiro convertido
     */

    public static void csvToJson(File csv_file, String path) {
        try {
            String name = path.substring(path.lastIndexOf('\\'));
            TxtLoadOptions loadOptions = new TxtLoadOptions(LoadFormat.CSV);
            loadOptions.setSeparator(';');
            Workbook workbook = new Workbook(csv_file.getPath(), loadOptions);
            workbook.save(path.substring(0, path.lastIndexOf('\\')) + "\\" + FilenameUtils.getBaseName(name) + ".json");
        } catch (Exception e) {
            System.out.println("Ficheiro nao encontrado");
        }
    }

    // 2023-05-01T09:00:00

    public static String csvToJson2(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            String[] headers = lines.get(0).split(";");
            List<Map<String, String>> result = new ArrayList<>();
            for (int i = 1; i < lines.size(); i++) {
                String[] values = lines.get(i).split(";");
                Map<String, String> map = new HashMap<>();
                String data = "";
                String in = "";
                String out = "";
                for (int j = 0; j < headers.length; j++) {
                    if ("Hora início da aula".equals(headers[j])) {
                        in = values[j];
                        continue;
                    }
                    if ("Hora fim da aula".equals(headers[j])) {
                        out = values[j];
                        continue;
                    }
                    if ("Data da aula".equals(headers[j])) {
                        data = values[j];
                        continue;
                    }
                    map.put(headers[j], values[j]);
                }
                String[] aux = data.split("/");
                data = aux[2] + "-" + aux[1] + "-" + aux[0];
                map.put("start", data + "T" + in);
                map.put("end", data + "T" + out);
                result.add(map);
            }
            Gson gson = new Gson();
            String json = gson.toJson(result);
            return json;
        } catch (Exception e) {
            System.out.println("Ficheiro nao encontrado");
        }
        return "FFFFFFFFFFFFFFFF";
    }


    /**
     * Função que converte um ficheiro .csv para .json
     *
     * @param json_file ficheiro .json a converter
     * @param path      path para onde queremos guardar o ficheiro convertido
     */
    public static void jsonToCsv(File json_file, String path) {
        try {
            String name = path.substring(path.lastIndexOf('\\'));
            TxtSaveOptions saveOptions = new TxtSaveOptions(SaveFormat.CSV);
            saveOptions.setSeparator(';');
            Workbook workbook = new Workbook(json_file.getPath());
            workbook.save(path.substring(0, path.lastIndexOf('\\')) + "\\" + FilenameUtils.getBaseName(name) + ".csv", saveOptions);

        } catch (Exception e) {
            System.out.println("Ficheiro nao encontrado");
        }
    }


    /**
     * Função que passa um ficheiro .csv para uma lista
     *
     * @param csv_file ficheiro .csv que queremos passar para uma lista
     * @return retorna uma lista com os blocks criados através dos campos do ficheiro .csv recebido como parâmetro
     */
    public static List<Block> csvToArray(File csv_file) {

        List<Block> result = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(csv_file);
            while (scanner.hasNext()) {

                List<String> data = List.of(scanner.nextLine().split(";"));
                Block block = new Block();
                for (int i = 0; i < data.size(); i++) block.setAttribute(i, data.get(i));

                result.add(block);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro nao encontrado");
        }
        return result;
    }


    /**
     * Função que carrega um ficheiro .json, converte para csv e passa para uma lista de blocks
     *
     * @return retorna uma lista de blocks criados com os campos do ficheiro .json convertido para .csv
     */
    public static List<Block> jsonToArrayList(File json_file) {
        jsonToCsv(json_file, json_file.getPath());
        File csv_file = new File(json_file.getPath().replace(".json", ".csv"));
        return csvToArray(csv_file);
    }


}
