package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Utils {

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
