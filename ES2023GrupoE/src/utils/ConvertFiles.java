package utils;

import java.io.File;
// Adicionar as seguintes dependências do ficheiro aspose-cells-23.1.jar
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.aspose.cells.Workbook;
import model.Block;

// É necessário importar esta biblioteca
// import com.aspose.cells.Workbook;

public class ConvertFiles {
    private File file;

    public ConvertFiles(File file){
        this.file = file;
    }

    // É necessário editar o path que queremos para o ficheiro
    public void csvToJson(ConvertFiles convert){
        try {
            Workbook workbook = new Workbook(convert.file.getName());
            workbook.save(".../nome_do_ficheiro.json");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // É necessário editar o path que queremos para o ficheiro
    public void jsonToCsv(ConvertFiles convert){
        try {
            Workbook workbook = new Workbook(convert.file.getName());
            workbook.save(".../nome_do_ficheiro.csv");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
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
}
