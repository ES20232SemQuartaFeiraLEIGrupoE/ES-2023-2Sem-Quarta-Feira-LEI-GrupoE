package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import org.apache.commons.io.FilenameUtils;
import java.net.URL;
import model.Block;

public class FileReaderWriter {


	public static File uploadFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Upload File");
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// TODO open schedule
		}
		return fileChooser.getSelectedFile();
	}

	public static void saveFileLocal(File file) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save File");
		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			String ext = FilenameUtils.getExtension(file.toString());
			switch (ext) {
			case "json":
				ConvertFiles.jsonToCsv(file, fileToSave.getAbsolutePath());
				break;
			case "csv":
				ConvertFiles.csvToJson(file, fileToSave.getAbsolutePath());
				break;
			}
		}

	}

	public static void copyURLToFile(URL url, File file) {
		try {
			InputStream input = url.openStream();
			if (file.exists()) {
				if (file.isDirectory())
					throw new IOException("File '" + file + "' is a directory");

				if (!file.canWrite())
					throw new IOException("File '" + file + "' cannot be written");
			} else {
				File parent = file.getParentFile();
				if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
					throw new IOException("File '" + file + "' could not be created");
				}
			}

			FileOutputStream output = new FileOutputStream(file);

			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}

			input.close();
			output.close();

			System.out.println("File '" + file + "' downloaded successfully!");
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}
	}

	public static void csvToFile(String fileName, List<Block> data) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(fileName));

			writer.println(Block.getHeader()); // cabeçalho do arquivo

			for (Block b : data)
				writer.printf(b.toString());

			writer.close();
		} catch (IOException e) {
			System.err.println("Erro ao criar arquivo CSV: " + e.getMessage());
		}
	}


}
