package iscte.se.SE10.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.Serializable;

import java.util.*;

import static iscte.se.SE10.utils.Utils.*;

/**
 * Classe Block é a classe que cria um objeto que contém toda a informação
 * necessária para ser apresentada num horário
 *
 * @author Grupo E
 * @version 1.0
 */
public class Block implements Serializable {

    /**
     * Constantes que representam o header do block
     */
    static final String CURSO = "Curso";
    static final String UNIDADE_CURRICULAR = "Unidade Curricular";
    static final String TURNO = "Turno";
    static final String TURMA = "Turma";
    static final String INSCRITOS_NO_TURNO = "Inscritos no turno";
    static final String DIA_DA_SEMANA = "Dia da semana";
    static final String HORA_INICIO_AULA = "Hora início da aula";
    static final String HORA_FIM_AULA = "Hora fim da aula";
    static final String DATA_DA_AULA = "Data da aula";
    static final String SALA_ATRIBUIDA = "Sala atribuída à aula";
    static final String LOTACAO_SALA = "Lotação da sala";

    /**
     * Array de String que representa as keys da classe Block
     */
    protected static final String[] keys = {CURSO,UNIDADE_CURRICULAR,TURNO , TURMA,INSCRITOS_NO_TURNO, DIA_DA_SEMANA, HORA_INICIO_AULA, HORA_FIM_AULA, DATA_DA_AULA, SALA_ATRIBUIDA, LOTACAO_SALA};


    /**
     * Map que será utilizado para associar as keys aos valores atribuídos
     */
    private Map<String, String> data;


    /**
     * Construtor de um objeto Block dado um Map
     * @param data Map
     */
    public Block(Map<String, String> data){
        this.data = data;
    }

    /**
     * Função que que retorna o Map<String,String> data
     * @return retorna a informação contida dentro do Block
     */
    public Map<String, String> getBlockData (){
        return data;
    }

    /**
     * Função que cria instãncias Block a partir de uma String CSV
     * @param data String CSV
     * @return retorna uma nova instãncia do objeto Block
     */

    public static Block createFromCSV(String data) {
        String[] keys = Block.keys;
        String[] values = data.split(";");

        if (values.length > keys.length)
            throw new RuntimeException("Quantidade de atributos invalida");

        Map<String, String> block = new LinkedHashMap<>();

        for (int i = 0; i < keys.length; i++)
            if (i >= values.length) block.put(keys[i], "Indefinido");
            else block.put(keys[i], values[i]);

        return new Block(block);
    }

    /**
     * Método que recebe um Map e inicializa um objeto Block
     * @param data Map de Strings
     * @return retorna um objeto Block inicializado com o conteúdo do Map
     */

    public static Block createFromScheduleFormat(Map<String, String> data) {
        String start = data.remove("start");
        String end = data.remove("end");
        data.put(HORA_INICIO_AULA, formatHourToLocal(start));
        data.put(HORA_FIM_AULA, formatHourToLocal(end));
        data.put(DATA_DA_AULA, formatDateToLocal(start));
        return new Block(data);
    }

    /**
     * Método que recebe um Map com a informação de um horário WEB e cria instâncias do objeto Block
     * @param webInfo Map com a informação de um calendário WEB
     * @return retorna instâncias do objeto Block
     */
    public static Block createFromWebCalendar(Map<String, String> webInfo) {
        Map<String, String> blocks = new LinkedHashMap<>();

        for(String key : keys){
            blocks.put(key, "Indefinido");
        }

        blocks.put(TURNO, webInfo.get(TURNO));
        blocks.put(UNIDADE_CURRICULAR, webInfo.get("Unidade de execução"));
        blocks.put(HORA_INICIO_AULA, formatIcsHourToLocal(webInfo.get("Início")));
        blocks.put(HORA_FIM_AULA, formatIcsHourToLocal(webInfo.get("Fim")));
        blocks.put(DATA_DA_AULA, formatIcsDateToLocal(webInfo.get("Início")));
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
     * @return retorna uma lista de objetos Block o atributo Map da classe Block
     */
    public List<Map<String, String>> getAsList(){
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

    /**
     * Método que devolve uma String JSON com os values do atributo Map de um objeto Block
     * @return retorna uma String CSV
     */

    public String getAsJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CURSO, data.get(CURSO));
        jsonObject.addProperty(UNIDADE_CURRICULAR, data.get(UNIDADE_CURRICULAR));
        jsonObject.addProperty(TURNO, data.get(TURNO));
        jsonObject.addProperty(TURMA, data.get(TURMA));
        jsonObject.addProperty(INSCRITOS_NO_TURNO, data.get(INSCRITOS_NO_TURNO));
        jsonObject.addProperty(DIA_DA_SEMANA, data.get(DIA_DA_SEMANA));
        jsonObject.addProperty(HORA_INICIO_AULA, data.get(HORA_INICIO_AULA));
        jsonObject.addProperty(HORA_FIM_AULA, data.get(HORA_FIM_AULA));
        jsonObject.addProperty(DATA_DA_AULA, data.get(DATA_DA_AULA));
        jsonObject.addProperty(SALA_ATRIBUIDA, data.get(SALA_ATRIBUIDA));
        jsonObject.addProperty(LOTACAO_SALA, data.get(LOTACAO_SALA));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }

    /**
     * Método que copia o atributo Map da classe Block e devolve um LinkedHashMap num formato WEB
     * @return retorna um LinkedHashMap num formato WEB
     */

    public Map<String, String> getAsScheduleFormat() {
        Map<String, String> copyData = new LinkedHashMap<>(data);
        String startHour = copyData.remove(HORA_INICIO_AULA);
        String endHour = copyData.remove(HORA_FIM_AULA);
        String classDate = copyData.remove(DATA_DA_AULA);
        copyData.put("start", formatDateToWeb(classDate, startHour));
        copyData.put("end", formatDateToWeb(classDate, endHour));
        return copyData;
    }
    //easter egg


}
