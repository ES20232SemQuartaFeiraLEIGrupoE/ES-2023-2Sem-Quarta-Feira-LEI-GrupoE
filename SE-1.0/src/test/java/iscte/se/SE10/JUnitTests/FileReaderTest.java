package iscte.se.SE10.JUnitTests;

import iscte.se.SE10.model.Block;
import iscte.se.SE10.utils.FileReader;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {
    @Test
    void readCSVEmptyFileTest() {
        // String CSV vazio
        String emptyCsv = "";

        InputStream inputStream = new ByteArrayInputStream(emptyCsv.getBytes());

        List<Block> blocksCSV = FileReader.readCSV(inputStream);
        // verifica se retorna uma lista fazia no método
        assertTrue(blocksCSV.isEmpty());
    }

    @Test
    void testReadJsonWithEmptyInput() {
        // Dados de entrada
        String json = "[{}]";
        InputStream inputStream = new ByteArrayInputStream(json.getBytes());
        String type = "web";

        // Execução do método
        List<Block> blocks = FileReader.readJson(inputStream, type);

        // Verificação do resultado esperado
        assertTrue(blocks.isEmpty());
    }


    @Test
    void readIcsValidLinkTest() {
        List<Block> blocks = FileReader.readIcs("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar" +
                ".do?method=iCalendar&username=gigca@iscte.pt&password=HfOvgRuJSLdkuVD2ZJWhW8J80BjItDxzzTZ6CbjNdDS" +
                "CYzVnujToQAaQBTMThu6G4tlkvErewMSAMGyFulrTEDJho2buarIWXrwG2LWVv1ysWeirhpkK2OCmYS1g8oN3");
        // verifica se é falso que a lista de objetos Block está vazia
        assertFalse(blocks.isEmpty());
    }


}
