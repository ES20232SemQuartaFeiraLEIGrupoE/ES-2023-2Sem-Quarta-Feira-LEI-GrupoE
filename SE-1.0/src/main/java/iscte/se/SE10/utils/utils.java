package iscte.se.SE10.utils;

public class utils {

    /**
     * Função que reorganiza a informação para um formato pretendido
     * @param date String que representa uma data
     * @param hour String que representa uma hora
     * @return retorna uma String com Data e hora reorganizados
     */
    public static String formatDateToWeb(String date, String hour) {
        String[] aux = date.split("/");
        String formattedDate = aux[2] + "-" + aux[1] + "-" + aux[0];
        return formattedDate + "T" + hour;
    }


    /**
     *
     * @param info
     * @return
     */
    public static  String formatDateToLocal(String info) {
        String[] aux = info.split("T");
        String[] hour = aux[0].split("-");
        return hour[2] + "/" + hour[1] + "/" + hour[0];
    }

    public static  String formatHourToLocal(String info) {
        return info.split("T")[1];
    }
}
