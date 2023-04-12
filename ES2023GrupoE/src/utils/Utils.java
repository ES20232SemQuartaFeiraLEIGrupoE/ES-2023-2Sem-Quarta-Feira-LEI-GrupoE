package utils;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utils {
    public static ArrayList<JSONObject> jsonToArrayList(String fileName){
        ArrayList<JSONObject>  list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray jsonArray = (JSONArray) obj;
            for (Object o : jsonArray){
                JSONObject jsonObject = (JSONObject) o;
                list.add(jsonObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
