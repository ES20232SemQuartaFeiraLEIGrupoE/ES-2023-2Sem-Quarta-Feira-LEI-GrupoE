package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aspose.cells.*;
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
			TxtSaveOptions saveOptions = new TxtSaveOptions(SaveFormat.CSV);
			saveOptions.setSeparator(';');
			Workbook workbook = new Workbook(json_file.getPath());
			workbook.save(path.substring(0, path.lastIndexOf('\\')) + "\\" + FilenameUtils.getBaseName(name) + ".csv", saveOptions);
		} catch (Exception e) {
			System.out.println("Ficheiro nao encontrado");
		}
	}


	public static List<Block> csvToArray(File csv_file) {
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
		jsonToCsv(json_file,json_file.getPath());
		File csv_file = new File(json_file.getPath().replace(".json",".csv"));
		return csvToArray(csv_file);

	}

}
