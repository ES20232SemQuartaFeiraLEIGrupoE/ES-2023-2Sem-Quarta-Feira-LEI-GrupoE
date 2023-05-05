package iscte.se.SE10.model;

import com.google.gson.Gson;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.*;

import static iscte.se.SE10.utils.utils.*;

/**
 * Classe Block é a classe que cria um objeto que contém toda a informação
 * necessária para ser apresentada num horário
 *
 * @author Grupo E
 * @version 1.0
 */
public class Block implements Serializable {

    public static final String[] keys = {"Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno", "Dia da semana", "Hora início da aula", "Hora fim da aula", "Data da aula", "Sala atribuída à aula", "Lotação da sala"};
    private Map<String, String> data;

    public Block(Map<String, String> data){
        this.data = data;
    }

    public static Block createFromCSV(String data) {
        String[] keys = Block.keys;
        String[] values = data.split(";");

        if (values.length != keys.length)
            throw new RuntimeException("Quantidade de atributos invalida");

        Map<String, String> block = new LinkedHashMap<>();

        for (int i = 0; i < values.length; i++)
            block.put(keys[i], values[i]);

        return new Block(block);
    }

    public static Block createFromScheduleFormat(Map<String, String> data) {
        String start = data.remove("start");
        String end = data.remove("end");
        data.put("Hora início da aula", formatHourToLocal(start));
        data.put("Hora fim da aula", formatHourToLocal(end));
        data.put("Data da aula", formatDateToLocal(start));
        return new Block(data);
    }

    /**
     * Função que devolve o header do block
     *
     * @return retorna o header
     */
    public static String getCSVHeader() {
        return String.join(";", Block.keys);
    }

    private List<Map<String, String>> getAsList(){
        List<Map<String, String>> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            Map<String, String> map = new HashMap<>();
            map.put(entry.getKey(), entry.getValue());
            list.add(map);
        }
        return list;
    }

    public String getAsCsv() {
        return String.join(";", data.values());
    }

    public String getAsJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this.getAsList());
        return json;
    }

    public Map<String, String> getAsScheduleFormat() {
        Map<String, String> copyData = new LinkedHashMap<>(data);
        String startHour = copyData.remove("Hora início da aula");
        String endHour = copyData.remove("Hora fim da aula");
        String classDate = copyData.remove("Data da aula");
        copyData.put("start", formatDateToWeb(classDate, startHour));
        copyData.put("end", formatDateToWeb(classDate, endHour));
        return copyData;
    }
    //easter egg


}
