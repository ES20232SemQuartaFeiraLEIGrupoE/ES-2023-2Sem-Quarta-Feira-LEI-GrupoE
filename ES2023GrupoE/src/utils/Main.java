package utils;

import java.io.File;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.Block;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
//		File csv_file = new File("Files_for_Tests/exemplo_horario.csv");
//		File json_file = new File ("Files_for_Tests/exemplo_horario.json");

		File file = FileReaderWriter.uploadFile();

		FileReaderWriter.saveFileLocal(file);

//		List<Block> list = ConvertFiles.csvToArray(csv_file);
//		for (Block b: list){
//			System.out.print(b);
//		}

//		ConvertFiles.csvToJson(csv_file);

//		ConvertFiles.jsonToCsv(json_file);
	}

}
