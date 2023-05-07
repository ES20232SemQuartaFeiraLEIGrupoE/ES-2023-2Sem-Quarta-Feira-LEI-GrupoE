package iscte.se.SE10.utils;

import java.util.*;
import biweekly.component.VEvent;


/**
 * Classe para extrair informação de eventos de um calendário
 * @author Grupo E
 * @version 1.0
 */
public class ConvertFiles {

    /**
     * Array de String que representa as webKeys
     */
    protected static final String[] webKeys = {"Unidade de execução", "Turno", "Início", "Fim"};

    /**
     * Construtor default
     */
    public ConvertFiles(){
        // No Initialization required
    }
    /**
     * Método que extrai os campos de um evento do calendário e devolve num Map
     * @param e recebe um evento de calendário
     * @return retorna um Map com as informações extraídas do evento do calendário
     */
    public static Map<String, String> getRelevantInfo(VEvent e) {
        Map<String, String> webInfo = new HashMap<>();
        String[] description = e.getDescription().getValue().split("\n");
        for (String s : description) {
            if("\r".equals(s))
                continue;
            try{
            String key = s.substring(0, s.indexOf(":"));
            String value = s.substring(s.indexOf(":") + 2).strip();

            for (String webKey : webKeys) {
                if (key.contains(webKey))
                    webInfo.put(key, value);
            }
            }catch (Exception ex){}
        }

        return webInfo;
    }

}
