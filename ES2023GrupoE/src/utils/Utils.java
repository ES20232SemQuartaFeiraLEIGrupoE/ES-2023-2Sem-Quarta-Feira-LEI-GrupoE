package utils;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
}
