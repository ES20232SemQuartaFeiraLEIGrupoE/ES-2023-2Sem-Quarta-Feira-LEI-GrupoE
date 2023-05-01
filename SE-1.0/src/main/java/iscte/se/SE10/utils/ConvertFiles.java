package iscte.se.SE10.utils;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.aspose.cells.*;
import iscte.se.SE10.model.Block;
import org.apache.commons.io.FilenameUtils;


/**
 * Classe para guardar e converter ficheiros
 * @author Grupo E
 * @version 1.0
 */
public class ConvertFiles {

	/**
	 * Função que converte um ficheiro .csv para .json
	 * @param csv_file ficheiro .csv a converter
	 * @param path path para onde queremos guardar o ficheiro convertido
	 */

	public static void csvToJson(File csv_file, String path) {
		try {
			String name = path.substring(path.lastIndexOf('\\'));
			TxtLoadOptions loadOptions = new TxtLoadOptions(LoadFormat.CSV);
			loadOptions.setSeparator(';');
			Workbook workbook = new Workbook(csv_file.getPath(), loadOptions);
			workbook.save(path.substring(0, path.lastIndexOf('\\')) + "\\" + FilenameUtils.getBaseName(name) + ".json");
		} catch (Exception e) {
			System.out.println("Ficheiro nao encontrado");
		}
	}



	/**
	 * Função que converte um ficheiro .csv para .json
	 * @param json_file ficheiro .json a converter
	 * @param path path para onde queremos guardar o ficheiro convertido
	 */
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


	/**
	 * Função que passa um ficheiro .csv para uma lista
	 * @param csv_file ficheiro .csv que queremos passar para uma lista
	 * @return retorna uma lista com os blocks criados através dos campos do ficheiro .csv recebido como parâmetro
	 */
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


	/**
	 * Função que carrega um ficheiro .json, converte para csv e passa para uma lista de blocks
	 * @return retorna uma lista de blocks criados com os campos do ficheiro .json convertido para .csv
	 */
	public static List<Block> jsonToArrayList(File json_file) {
		jsonToCsv(json_file,json_file.getPath());
		File csv_file = new File(json_file.getPath().replace(".json",".csv"));
		return csvToArray(csv_file);
	}


}
