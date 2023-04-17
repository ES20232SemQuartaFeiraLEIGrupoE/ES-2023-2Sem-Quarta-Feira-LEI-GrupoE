package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.aspose.cells.LoadFormat;
import com.aspose.cells.Workbook;
import com.aspose.cells.TxtLoadOptions;
import model.Block;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class ConvertFiles {

	public static void csvToJson(File csv_file, String path) {
		try {
			String name = path.substring(path.lastIndexOf('\\'));
			TxtLoadOptions loadOptions = new TxtLoadOptions(LoadFormat.CSV);
			loadOptions.setSeparator(';');
			Workbook workbook = new Workbook(csv_file.getPath(), loadOptions);
			workbook.save(path.substring(0, path.lastIndexOf('\\')) + "\\" + FilenameUtils.getBaseName(name) + ".json");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ficheiro nao encontrado");
		}
	}


	public static void jsonToCsv(File json_file, String path) {
		try {
			String name = path.substring(path.lastIndexOf('\\'));
			Workbook workbook = new Workbook(json_file.getPath());
			workbook.save(path.substring(0, path.lastIndexOf('\\')) + "\\" + FilenameUtils.getBaseName(name) + ".csv");
		} catch (Exception e) {
			System.out.println("Ficheiro nao encontrado");
		}
	}

	public static List<Block> csvToArray() {
		File csv_file = FileReaderWriter.uploadFile();
		List<Block> result = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(csv_file);
			while (scanner.hasNext()) {
				List<String> data = List.of(scanner.nextLine().split(";"));
				Block block = new Block();
				for (int i = 0; i< data.size(); i++) block.setAttribute(i , data.get(i));
				result.add(block);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro nao encontrado");
		}
		return result;
	}

	public static List<Block> jsonToArrayList() {
		File json_file = FileReaderWriter.uploadFile();
		List<Block> list = new ArrayList<>();
		JSONParser parser = new JSONParser();
		try {
			JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(json_file));
			for (Object o : jsonArray) {
				JSONObject jsonObject = (JSONObject) o;
				String course = (String) jsonObject.get("Curso");
				String curricular_unit = (String) jsonObject.get("Unidade Curricular");
				String shift = (String) jsonObject.get("Turno");
				String team = (String) jsonObject.get("Turma");
				String number_of_subscribers = Long.toString((Long)jsonObject.get("Inscritos no turno"));
				String day_of_week = (String) jsonObject.get("Dia da semana");
				String hour_begin = (String) jsonObject.get("Hora inicio da aula");
				String hour_end = (String) jsonObject.get("Hora fim da aula");
				String date = (String) jsonObject.get("Data da aula");
				String room = (String) jsonObject.get("Sala atribuida a aula");
				String size_room = Long.toString((Long)jsonObject.get("Lotacao da sala"));
				Block block = new Block(course, curricular_unit, shift, team, number_of_subscribers, day_of_week, hour_begin, hour_end, date, room, size_room);
				list.add(block);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro nao encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}

}
