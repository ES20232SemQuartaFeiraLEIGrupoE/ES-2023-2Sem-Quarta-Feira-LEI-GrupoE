package iscte.se.SE10.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import iscte.se.SE10.model.Block;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;

import static iscte.se.SE10.utils.utils.DownloadWebCall;

public class FileReader {

    public static List<Block> readCSV(InputStream inputStream) {
        List<Block> blocks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                Block block = Block.createFromCSV(line);
                blocks.add(block);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return blocks;
    }

    public static List<Block> readJson(InputStream inputStream, String type) {
        List<Block> blocks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Gson gson = new Gson();
            List<Map<String, String>> data = gson.fromJson(reader, new TypeToken<List<Map<String, String>>>() {
            }.getType());
            for (Map<String, String> map : data) {
                Block block = ("web".equals(type)) ? Block.createFromScheduleFormat(map) : new Block(map);
                blocks.add(block);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blocks;
    }

    public static List<Block> readIcs(String uri) {
        File temp = DownloadWebCall(uri);
        List<Block> blocks = new ArrayList<>();
        ICalendar ical = null;
        try {
            ical = Biweekly.parse(temp).first();
            List<VEvent> events = ical.getEvents(); //Lista dos eventos
            for (VEvent e : events) {
                Block b = Block.createFromWebCalaendar(ConvertFiles.getRelevantInfo(e));
                blocks.add(b);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return blocks;
    }
}
