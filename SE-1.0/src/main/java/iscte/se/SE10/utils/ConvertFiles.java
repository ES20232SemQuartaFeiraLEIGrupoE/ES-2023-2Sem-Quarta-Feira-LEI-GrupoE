package iscte.se.SE10.utils;

import java.io.*;

import java.util.*;

import biweekly.component.VEvent;
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

    public static final String[] webKeys = {"Unidade de execução", "Turno", "Início", "Fim"};
    public static Map<String, String> getRelevantInfo(VEvent e) {
        Map<String, String> webInfo = new HashMap<>();
        String[] description = e.getDescription().getValue().split("\n");
        for (String s : description) {
            if("\r".equals(s))
                continue;
            String key = s.substring(0, s.indexOf(":"));
            String value = s.substring(s.indexOf(":") + 2).strip();
            for (String webKey : webKeys) {
                if (key.contains(webKey))
                    webInfo.put(key, value);
            }
        }

        return webInfo;
    }

}
