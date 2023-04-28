package com.iscte.se.SE10.JUnitTests;

import com.iscte.se.SE10.model.Block;
import com.iscte.se.SE10.utils.ConvertFiles;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConvertFilesTest {

    private String csv_data = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora inicio da aula;Hora fim da aula;Data da aula;Sala atribuida a aula;Lotacao da sala\n" +
                              "ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Sex;13:00:00;14:30:00;02/12/2022;AA2.25;34";
    private File csv_file = createTempFile(csv_data);

    private String json_data = "{\"Curso\":\"ME\",\"Unidade Curricular\":\"Teoria dos Jogos e dos Contratos\",\"Turno\":\"01789TP01\",\"Turma\":\"MEA1\",\"Inscritos no turno\":\"30\",\"}";
    private File json_file = createTempFile(json_data);

    @Test
    void csvToJson() throws IOException, ParseException {
        String path = "Files_for_Tests\\csv_file.json";
        ConvertFiles.csvToJson(csv_file, path);

        File json_file = new File(path);
        assertTrue(json_file.exists());
        assertTrue(json_file.isFile());
        assertTrue(json_file.length() > 0);

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(json_file));

        JSONObject firstBlock = (JSONObject) jsonArray.get(0);
        assertEquals("ME", firstBlock.get("Curso"));
        assertEquals("Teoria dos Jogos e dos Contratos", firstBlock.get("Unidade Curricular"));
        assertEquals("01789TP01", firstBlock.get("Turno"));
        assertEquals("MEA1", firstBlock.get("Turma"));
        assertEquals(30L, firstBlock.get("Inscritos no turno"));
        assertEquals("Sex", firstBlock.get("Dia da semana"));
        assertEquals("13:00:00", firstBlock.get("Hora inicio da aula"));
        assertEquals("14:30:00", firstBlock.get("Hora fim da aula"));
        assertEquals("02/12/2022", firstBlock.get("Data da aula"));
        assertEquals("AA2.25", firstBlock.get("Sala atribuida a aula"));
        assertEquals( 34L, firstBlock.get("Lotacao da sala"));

        json_file.delete();

    }

    @Test
    void jsonToCsv() throws IOException {
        String path = "Files_for_Tests\\json_file.csv";
        ConvertFiles.jsonToCsv(json_file,path);
        File csv_file = new File(path);
        List<String> lines = Files.readAllLines(csv_file.toPath());
        assertEquals(3, lines.size());
        assertEquals("Curso;Unidade Curricular;Turno;Turma;Inscritos no turno", lines.get(0));
        assertEquals("ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30", lines.get(1));
        csv_file.delete();
        
    }

    @Test
    void csvToArray() {
        List<Block> blocks = ConvertFiles.csvToArray(csv_file);
        assertEquals(2, blocks.size());
        Block b1 = blocks.get(1);
        assertEquals("ME", b1.course);
        assertEquals("Teoria dos Jogos e dos Contratos", b1.curricular_unit);
        assertEquals("01789TP01", b1.shift);
        assertEquals("MEA1", b1.team);
        assertEquals("30", b1.number_of_subscribers);
        assertEquals("Sex", b1.day_of_week);
        assertEquals("13:00:00", b1.hour_begin);
        assertEquals("14:30:00", b1.hour_end);
        assertEquals("02/12/2022", b1.date);
        assertEquals("AA2.25", b1.room);
        assertEquals("34", b1.size_room);
        
    }

    private File createTempFile(String data) {
        try {
            File tempFile = File.createTempFile("test", ".csv");
            FileWriter writer = new FileWriter(tempFile);
            writer.write(data);
            writer.close();
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Failed to create temp file");
        }
    }


    @Test
    void jsonToArrayList() {
        List<Block> blocks = ConvertFiles.jsonToArrayList(json_file);
        assertEquals(3, blocks.size());
        Block b1 = blocks.get(1);
        assertEquals("ME", b1.course);
        assertEquals("Teoria dos Jogos e dos Contratos", b1.curricular_unit);
        assertEquals("01789TP01", b1.shift);
        assertEquals("MEA1", b1.team);
        assertEquals("30", b1.number_of_subscribers);
        
    }
}