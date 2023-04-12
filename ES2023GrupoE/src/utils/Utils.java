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
