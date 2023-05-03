package iscte.se.SE10.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JFileChooser;

import iscte.se.SE10.model.Block;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FilenameUtils;
import java.net.URL;
import org.json.simple.JSONObject;

/**
 * Classe para ler e escrever em ficheiros
 * @author Grupo E
 * @version 1.0
 */

public class FileReaderWriter {

	/**
	 *
	 * @return retorna o ficheiro selecionado através do explorador de ficheiros
	 */

	public static File uploadFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Upload File");
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// TODO open schedule
		}
		return fileChooser.getSelectedFile();
	}


	/**
	 * Função que recebe um ficheiro como parâmetro e guarda no formato selecionado pelo utilizador
	 * @param file ficheiro que queremos guardar localmente
	 */

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


	/**
	 * Função que recebe um URL de onde irá ler os dados existentes
	 * @param url url dos dados a serem lidos
	 * @param file ficheiro recebido como parâmetro onde serão escritos os dados lidos do URL
	 */

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


	/**
	 * Função que recebe o nome para um ficheiro que será criado através de uma lista de blocks
	 * @param fileName nome do ficheiro onde serão escritos os blocks lidos da lista
	 * @param data lista de blocks
	 */
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

	/**
	 * Função que recebe um horário (lista de Block) e escreve num ficheiro CSV
	 * e salva num path definido
	 * @param data lista de Block
	 */
	public static void scheduleToCsv(List<Block> data){

		// falta decidir o path que queremos para o ficheiro gerado
		File file = new File("");
		try{
			FileWriter outputFile = new FileWriter(file);
			CSVFormat format = CSVFormat.EXCEL;

			CSVPrinter print = new CSVPrinter(outputFile, format);
			print.printRecord(Block.getHeader().toString());
			for (Block block : data) {

				print.printRecord(block.toStringColumn());
			}
			print.close();
		} catch (Exception e){
			System.out.println("-> FileReaderWriter / scheduleToCsv - Não foi possivel criar o ficheiro CSV");
			e.printStackTrace();

		}

	}

	/**
	 * Função que recebe um horário (lista de Block) e escreve num ficheiro JSON
	 * e salva num path definido
	 * @param data lista de Block
	 */
	public static void scheduleToJson(List<Block> data){
		JSONObject jsonFile = new JSONObject();

		for (Block block : data) {
			jsonFile.put("course", block.getCourse());
			jsonFile.put("curricular_unit", block.getCurricular_unit());
			jsonFile.put("shift", block.getShift());
			jsonFile.put("team", block.getTeam());
			jsonFile.put("number_of_subscribers", block.getNumberOfSubscribers());
			jsonFile.put("day_of_week", block.getDayOfWeek());
			jsonFile.put("hour_begin", block.getHour_begin());
			jsonFile.put("hour_end", block.getHour_end());
			jsonFile.put("date", block.getDate());
			jsonFile.put("room", block.getRoom());
			jsonFile.put("size_room", block.getSizeRoom());
		}
		try{
			// falta definir o path que queremos para o ficheiro gerado
			FileWriter fileWriter = new FileWriter("");
			fileWriter.write(jsonFile.toJSONString()+"\n");
			fileWriter.close();
		} catch (Exception e){
			System.out.println("-> FileReaderWriter / scheduleToJson - Não foi possivel criar o ficheiro JSON");
			e.printStackTrace();
		}
	}



}