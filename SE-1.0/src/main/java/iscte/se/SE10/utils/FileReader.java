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

import java.util.ArrayList;

import java.util.List;

import java.util.Map;


import static iscte.se.SE10.utils.Utils.downloadWebCall;

/**
 * Classe utilizada para ler ficheiros
 * @author Grupo E
 * @version 1.0
 */
public class FileReader {

    /**
     * Construtor default
     */
    public FileReader(){
        // No Initialization required
    }

	/**
	 * Função que lê um CSV através de um objeto InputStream e cria uma lista de objetos Block
	 * @param inputStream objeto inputStream
	 * @return retorna uma lista de blocks
	 */

	public static List<Block> readCSV(InputStream inputStream) {
		List<Block> blocks = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String firstline = reader.readLine();
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

	/**
	 * Função que lê um CSV através de um objeto InputStream e cria uma lista de objetos Block
	 * @param inputStream objeto inputStream
	 * @param type String que identifica o formato do ficheiro
	 * @return lista de objetos Block
	 */

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
        } catch (Exception e) {}
        return blocks;
    }

	/**
	 * Função que através de um ficheiro remoto cria uma lista do objetos Block
	 * @param uri endereço do ficheiro remoto
	 * @return retorna uma lista de objetos Block
	 */
    public static List<Block> readIcs(String uri) {
        File temp = downloadWebCall(uri);
        List<Block> blocks = new ArrayList<>();
        ICalendar ical;
        try {
            ical = Biweekly.parse(temp).first();
            List<VEvent> events = ical.getEvents(); //Lista dos eventos
            for (VEvent e : events) {
                try{
                Block b = Block.createFromWebCalendar(ConvertFiles.getRelevantInfo(e));
                blocks.add(b);
                }catch (Exception ex){
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return blocks;
    }

}
