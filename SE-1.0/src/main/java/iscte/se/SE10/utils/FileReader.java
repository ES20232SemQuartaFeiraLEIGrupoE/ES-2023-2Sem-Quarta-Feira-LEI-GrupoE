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

	public static List<Block> icsToBlockList(URI uri) {
		String URI = uri.toString();
		//Converter de webcal: para htpps:
		String httpsURI = "https:" + URI.split(":")[1];
		final String[] keys = { "Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno", "Dia da semana",
				"Hora início da aula", "Hora fim da aula", "Data da aula", "Sala atribuída à aula", "Lotação da sala" };
		List<Block> blockList = new ArrayList<Block>();
		//Formato de Input dos eventos
		SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		//Formato da hora de inicio e fim
		SimpleDateFormat formatohorainifim = new SimpleDateFormat("HH:mm:ss");
		//Formato da data
		SimpleDateFormat formatodata = new SimpleDateFormat("dd/MM/yyyy");
		File temp = new File("temp");
		try {
			// fazer download do ficheiro ics para um ficheiro temporário f
			FileUtils.copyURLToFile(new URL(httpsURI), temp);
			// converter o ficherio no tipo ICalendar da biblioteca Biweekly
			ICalendar ical = Biweekly.parse(temp).first();
			//Lista dos eventos
			List<VEvent> events = ical.getEvents();
			//Ciclo para iterar todos os eventos
			for (VEvent e : events) {
				//Bloco que vai ser criado nesta iteração do ciclo
				Map<String, String> block = new LinkedHashMap<>();
				//Descrição do evento
				String[] Description = e.getDescription().toString().split("\n");
				// Curso
				block.put(keys[0], null);
				// Unidade Curricular
				block.put(keys[1], Description[1].split(":")[1].strip());
				// Turma
				block.put(keys[2], null);
				// Inscritos
				block.put(keys[4], "0");
				// Hora Inicio
				// parse start date into a date class with format EEE MMM dd HH:mm:ss zzz yyyy
				// to parse it into output format HH:mm:ss and then to string
				block.put(keys[6],
						formatohorainifim.format(inputFormat.parse(e.getDateStart().getValue().toString().strip())).toString());
				// Hora Fim
				// same as above but for end date
				block.put(keys[7],
						formatohorainifim.format(inputFormat.parse(e.getDateStart().getValue().toString().strip())).toString());
				// Data Aula
				block.put(keys[8],
						formatodata.format(inputFormat.parse(e.getDateStart().getValue().toString().strip())).toString());
				// Sala
				block.put(keys[9], e.getLocation().getValue().toString().split(" ")[0].strip());
				// Lotação da sala
				block.put(keys[10], "30");

				// some events have 15 fields, the others have 17.
				if (Description.length == 15) {
					block.put(keys[3], Description[2].split(":")[1]);
					// get the day of the week
					LocalDate date1 = LocalDate.parse(Description[5].split(" ")[1].strip());
					// Dia da semana em ingles
					DayOfWeek dw = date1.getDayOfWeek();
					// Dia da Semana
					// Vai buscar o código do dia da semana, passa para portugues, passa para
					// maisculas, e tiramos só os 3 primeiros caracteres.
					block.put(keys[5],
							dw.getDisplayName(TextStyle.FULL, new Locale("pt")).toUpperCase().substring(0, 3));
				} else {
					block.put(keys[3], Description[3].split(":")[1]);
					// get the day of the week
					LocalDate date1 = LocalDate.parse(Description[4].split(" ")[1]);
					DayOfWeek dw = date1.getDayOfWeek();
					block.put(keys[5],
							dw.getDisplayName(TextStyle.FULL, new Locale("pt")).toUpperCase().substring(0, 3));

				}
				blockList.add(new Block(block));
			}
			temp.delete();
			return blockList;
		} catch (IOException e) {
			temp.delete();
			e.printStackTrace();
		} catch (ParseException e1) {
			temp.delete();
			e1.printStackTrace();
		}
		temp.delete();
		return blockList;

	}
}
