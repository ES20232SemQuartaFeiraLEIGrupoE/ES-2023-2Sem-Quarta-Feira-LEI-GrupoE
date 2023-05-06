package iscte.se.SE10.model;

import biweekly.component.VEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

import static iscte.se.SE10.utils.FileReader.readIcs;
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


    /**
     * Construtor de um objeto Block dado um Map<String, String>
     * @param data Map<String, String>
     */
    public Block(Map<String, String> data){
        this.data = data;
    }

    /**
     * Função que cria instãncias Block a partir de uma String CSV
     * @param data String CSV
     * @return retorna uma nova instãncia do objeto Block
     */

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

    /**
     * Método que recebe um Map<String, String> e inicializa um objeto Block
     * @param data Map de Strings
     * @return retorna um objeto Block inicializado com o conteúdo do Map
     */

    public static Block createFromScheduleFormat(Map<String, String> data) {
        String start = data.remove("start");
        String end = data.remove("end");
        data.put("Hora início da aula", formatHourToLocal(start));
        data.put("Hora fim da aula", formatHourToLocal(end));
        data.put("Data da aula", formatDateToLocal(start));
        return new Block(data);
    }

    public static Block createFromWebCalendar(Map<String, String> webInfo) {
        Map<String, String> blocks = new LinkedHashMap<>();

        for(String key : keys){
            blocks.put(key, "null");
        } // {Turno=PISIDPL03, Unidade de execução=Projeto de Integração de Sistemas de Informação Distribuídos, Fim=2023-05-09 14:30, Início=2023-05-09 13:00}

        blocks.put("Turno", webInfo.get("Turno"));
        blocks.put("Unidade Curricular", webInfo.get("Unidade de execução"));
        blocks.put("Hora início da aula", formatIcsHourToLocal(webInfo.get("Início")));
        blocks.put("Hora fim da aula", formatIcsHourToLocal(webInfo.get("Fim")));
        blocks.put("Data da aula", formatIcsDateToLocal(webInfo.get("Início")));
        System.out.println(blocks);
        return new Block(blocks);
    }

    /**
     * Método que devolve o header do objeto Block
     * @return retorna o header
     */
    public static String getCSVHeader() {
        return String.join(";", Block.keys);
    }


    /**
     * Método que devolve a conversão do atributo Map do objeto Block numa lista de Map
     * @return retorna uma lista de objetos Blocko atributo Map da classe Block
     */
    private List<Map<String, String>> getAsList(){
        List<Map<String, String>> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            Map<String, String> map = new HashMap<>();
            map.put(entry.getKey(), entry.getValue());
            list.add(map);
        }
        return list;
    }

    /**
     * Método que devolve uma String CSV com os values do atributo Map de um objeto Block
     * @return retorna uma String CSV
     */
    public String getAsCsv() {
        return String.join(";", data.values());
    }

    public String getAsJson() {
        Gson gson = new Gson();
//        String json = gson.toJson(this.getAsList());
        return gson.toJson(this.getAsList());
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
