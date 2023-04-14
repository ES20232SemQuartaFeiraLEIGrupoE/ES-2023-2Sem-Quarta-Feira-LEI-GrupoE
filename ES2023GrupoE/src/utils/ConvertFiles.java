package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.aspose.cells.Workbook;
import model.Block;
import org.apache.commons.io.FilenameUtils;

public class ConvertFiles {

	public static void csvToJson(File csv_file, String path) {
		try {
			String name = path.substring(path.lastIndexOf('\\'));
			Workbook workbook = new Workbook(csv_file.getPath());
			workbook.save(path.substring(0, path.lastIndexOf('\\')) + "\\" + FilenameUtils.getBaseName(name) + ".json");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ficheiro nao encontrado");
		}
	}

	// É necessário editar o path que queremos para o ficheiro
	public static void jsonToCsv(File json_file, String path) {
		try {
			String name = path.substring(path.lastIndexOf('\\'));
			Workbook workbook = new Workbook(json_file.getPath());
			workbook.save(path.substring(0, path.lastIndexOf('\\')) + "\\" + FilenameUtils.getBaseName(name) + ".csv");
		} catch (Exception e) {
			System.out.println("Ficheiro nao encontrado");
		}
	}

	public static List<Block> csvToArray(File csv_file) {
		List<Block> result = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(csv_file);
			while (scanner.hasNext()) {
				String[] data = scanner.nextLine().split(";");
				Block block = new Block(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
						data[9], data[10]);
				result.add(block);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro nao encontrado");
		}
		return result;
	}
}
