package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Block;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

//		File file = FileReaderWriter.uploadFile();
//		FileReaderWriter.saveFileLocal(file);


		List<Block> list = ConvertFiles.jsonToArrayList();
		for (Block j : list) System.out.println(j.toString());
//
//		File json_file = FileReaderWriter.uploadFile();
//		ConvertFiles.jsonToCsv(json_file, json_file.getPath());
//
//
//
//

	}
}
