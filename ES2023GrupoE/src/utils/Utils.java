package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utils {

    public static void csvToFile(String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);

            pw.println("Nome, Idade, Cidade"); // cabeçalho do arquivo

            pw.println("João, 30, São Paulo");
            pw.println("Maria, 25, Rio de Janeiro");
            pw.println("Pedro, 40, Belo Horizonte");

            pw.close();
            fw.close();
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo CSV: " + e.getMessage());
        }
    }

    public static ArrayList<JSONObject> jsonToArrayList(String fileName){
        ArrayList<JSONObject>  list = new JSONParser();
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray jsonArray = (JSONArray) obj;
            for (Object obj : jsonArray){
                JSONObject jsonObject = (JSONObject) o;
                list.add(jsonObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


}
