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
import java.net.URL;
//import org.apache.commons.io.FilenameUtils;


public class Utils {
	
	public static List<Block> csvToArray(String csv_path) {
		List<Block> result = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File(csv_path));
	        while (scanner.hasNext()) {
	        	String[] data = scanner.nextLine().split(";");
	        	Block block = new Block(data[0], data[1], data[2],data[3],data[4],data[5],data[6], data[7],data[8],data[9],data[10]);
	        	result.add(block);
	        }
		}catch(FileNotFoundException e){
			System.out.println("Ficheiro n�o encontrado");
		}
		return result;
	}

	public static void csvToFile(String fileName) {
		try {
			FileWriter fw = new FileWriter(fileName);
			PrintWriter pw = new PrintWriter(fw);

			pw.println("Nome, Idade, Cidade"); // cabeçalho do arquivo

			pw.println("João, 30, São Paulo");
			pw.println("Maria, 25, Rio de Janeiro");
			pw.println("Pedro, 40, Belo Horizonte");

			pw.close();
			fw.close();
		} catch (IOException e) {
			System.err.println("Erro ao criar arquivo CSV: " + e.getMessage());
		}
	}

	
//	public static void saveFileLocal(String path) {
//		
////		Desktop desktop = Desktop.getDesktop();
////		
////		try {
////			desktop.open(new File(path));
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		String ext = FilenameUtils.getExtension(path);
//		switch (ext) {
//		case "csv":
//			csvToFile(path);
//			break;
//		// default:
//		// jsonToFile(path);
//		}
//
//	}
	
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
		}
		catch(IOException ioEx) {
			ioEx.printStackTrace();
		}
	}

}
