package csvToJson_jsonToCsv;

import java.io.File;
// Adicionar as seguintes dependências do ficheiro aspose-cells-23.1.jar

// É necessário importar esta biblioteca
//import com.aspose.cells.Workbook;

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
}
