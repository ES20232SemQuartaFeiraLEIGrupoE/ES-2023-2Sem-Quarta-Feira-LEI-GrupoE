package iscte.se.SE10.utils;


import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Classe que contém métodos úteis à reorganização de Strings e a copiar ficheiros remotos
 * @author Grupo E
 * @version 1.0
 */

public class Utils {

    /**
     * Construtor default
     */
    public Utils(){}

    /**
     * Método que reorganiza uma String para um formato pretendido
     * @param date String que representa uma data
     * @param hour String que representa uma hora
     * @return retorna uma String com Data e hora organizada da seguinte forma: ...-...-...T...
     */
    public static String formatDateToWeb(String date, String hour) {
        String[] aux = date.split("/");
        String formattedDate = aux[2] + "-" + aux[1] + "-" + aux[0];
        return formattedDate + "T" + hour;
    }

    /**
     * Método que reorganiza uma String para um formato pretendido
     * @param info String com uma data e hora organizada organizada da seguinte forma ...-...-...T...
     * @return retorna uma String com Data e hora organizada da seguinte forma: ...-...-...
     */
    public static  String formatDateToLocal(String info) {
        String[] aux = info.split("T");
        String[] hour = aux[0].split("-");
        return hour[2] + "/" + hour[1] + "/" + hour[0];
    }

    /**
     * Método que extrai a hora de uma String que tem a seguinte organização: data T hora
     * @param info String com uma data e hora organizada organizada da seguinte forma: data T hora
     * @return retorna uma String apenas com a hora
     */
    public static String formatHourToLocal(String info) {
        return info.split("T")[1];
    }

    /**
     * Método que extrai a data de uma String que tem a seguinte organização: data hora
     * @param info String com uma data e hora organizada organizada da seguinte forma: data hora
     * @return retorna uma String com a data organizada da seguinte forma: .../.../...
     */
    public static String formatIcsDateToLocal(String info) {
        String[] aux = info.split(" ");
        String[] hour = aux[0].split("-");
        return hour[2] + "/" + hour[1] + "/" + hour[0];
    }

    /**
     * Método que extrai a hora de uma String que tem a seguinte organização: data hora
     * @param info String com uma data e hora organizada organizada da seguinte forma: data hora
     * @return retorna uma String com a hora
     */
    public static String formatIcsHourToLocal(String info) {
        return info.split(" ")[1];
    }

    /**
     * Método que recebe uma String com o endereço de um ficheiro remoto e utiliza a biblioteca FileUtils para copiar
     * o conteúdo ficheiro remoto para um ficheiro temporário que é devolvido
     * @param uri endereço do ficheiro remoto
     * @return retorna um ficheito temporário com o conteúdo do ficheiro cujo download foi feito
     */
    public static File DownloadWebCall(String uri) {
        String httpsURI = "https:" + uri.split(":")[1];
        try {
            File temp = new File("temp");
            FileUtils.copyURLToFile(new URL(httpsURI), temp);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Valid to open file");
    }
}
