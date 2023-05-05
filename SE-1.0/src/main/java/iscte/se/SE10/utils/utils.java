package iscte.se.SE10.utils;

import iscte.se.SE10.model.Block;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class utils {
    public static String formatDateToWeb(String date, String hour) {
        String[] aux = date.split("/");
        String formattedDate = aux[2] + "-" + aux[1] + "-" + aux[0];
        return formattedDate + "T" + hour;
    }

    public static String formatDateToLocal(String info) {
        String[] aux = info.split("T");
        String[] hour = aux[0].split("-");
        return hour[2] + "/" + hour[1] + "/" + hour[0];
    }

    public static String formatHourToLocal(String info) {
        return info.split("T")[1];
    }
    public static String formatIcsDateToLocal(String info) {
        String[] aux = info.split(" ");
        String[] hour = aux[0].split("-");
        return hour[2] + "/" + hour[1] + "/" + hour[0];
    }
    public static String formatIcsHourToLocal(String info) {
        return info.split(" ")[1];
    }

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
