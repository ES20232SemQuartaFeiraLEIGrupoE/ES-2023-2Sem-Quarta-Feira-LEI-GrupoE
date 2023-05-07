package iscte.se.SE10.JUnitTests;//package iscte.se.SE10.JUnitTests;

import iscte.se.SE10.model.Block;
import iscte.se.SE10.utils.FileWriter;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class FileWriterTest {

    private Map<String, String> mapBlocks = new HashMap<>();
    private List<Block> blocks = new ArrayList<>();


    @Test
    public void saveInCSV_JsonExistTest() throws IOException {
        // Chamar o método
        FileWriter.saveInCSV(creatList());
        FileWriter.saveInJson(creatList());

        File fileCSV = new File("schedule.csv");
        File fileJson = new File("schedule.json");
        List<String> lines = Files.readAllLines(Paths.get("schedule.csv"));

        // verificar se o ficheiro foi criado
        assertTrue(fileJson.exists());
        assertTrue(fileCSV.exists());

        // eliminar o ficheiro depois do teste
        fileCSV.delete();
        fileJson.delete();
    }

    @Test
    void saveInCSV_JsonTestEmptyList() throws IOException {
        // lista vazia
        List<Block> emptyList = new ArrayList<>();
        FileWriter.saveInCSV(emptyList);
        File fileCSV = new File("nao_cria.csv");
        File fileJson = new File("nao_cria.json");

        // verifica que o ficheiro não foi criado
        assertFalse(fileCSV.exists());
        assertFalse(fileJson.exists());
    }

    public List<Block> creatList(){
        blocks.add(new Block(createMap()));
        return blocks;
    }

    private Map<String, String> createMap() {
        mapBlocks.put("Curso","ME");
        mapBlocks.put("Unidade Curricular","Teoria dos Jogos e dos Contratos");
        mapBlocks.put("Turno","01789TP01");
        mapBlocks.put("Turma","MEA1");
        mapBlocks.put("Inscritos no turno","30");
        mapBlocks.put("Dia da semana","Sex");
        mapBlocks.put("Hora início da aula","13:00:00");
        mapBlocks.put("Hora fim da aula","14:30:00");
        mapBlocks.put("Data da aula","02/12/2022");
        mapBlocks.put("Sala atribuída à aula","AA2.25");
        mapBlocks.put("Lotação da sala","34");

        return mapBlocks;
    }

    @Test
    void formatToWebIsntEmptyString() {
        // verificar se não retorna uma String null
        assertNotNull(FileWriter.formatToWeb(creatList()));
    }

    @Test
    void testFormatToWebSingleBlock() {
        String result = FileWriter.formatToWeb(creatList());
        // verificar se o retorno do método é o esperado
        assertEquals("[{\"Inscritos no turno\":\"30\",\"Lotação da sala\":\"34\"," +
                "\"Turma\":\"MEA1\",\"Turno\":\"01789TP01\",\"Curso\":\"ME\"," +
                "\"Unidade Curricular\":\"Teoria dos Jogos e dos Contratos\"," +
                "\"Dia da semana\":\"Sex\",\"Sala atribuída à aula\":\"AA2.25\"," +
                "\"start\":\"2022-12-02T13:00:00\",\"end\":\"2022-12-02T14:30:00\"}]", result);
    }

}
