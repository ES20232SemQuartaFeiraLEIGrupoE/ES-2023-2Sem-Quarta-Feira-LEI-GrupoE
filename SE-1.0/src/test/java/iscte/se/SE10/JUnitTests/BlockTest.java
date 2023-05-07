package iscte.se.SE10.JUnitTests;

import iscte.se.SE10.model.Block;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockTest {

    @Test
    public void testCreateFromCSV() {
        String data = "ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Sex;13:00:00;14:30:00;01/05/2023;AA2.25;34";
        Block block = Block.createFromCSV(data);

        assertEquals("ME", block.data.get("Curso"));
        assertEquals("Teoria dos Jogos e dos Contratos", block.data.get("Unidade Curricular"));
        assertEquals("01789TP01", block.data.get("Turno"));
        assertEquals("MEA1", block.data.get("Turma"));
        assertEquals("30", block.data.get("Inscritos no turno"));
        assertEquals("Sex", block.data.get("Dia da semana"));
        assertEquals("13:00:00", block.data.get("Hora início da aula"));
        assertEquals("14:30:00", block.data.get("Hora fim da aula"));
        assertEquals("01/05/2023", block.data.get("Data da aula"));
        assertEquals("AA2.25", block.data.get("Sala atribuída à aula"));
        assertEquals("34", block.data.get("Lotação da sala"));
    }

    @Test
    public void testCreateFromCSVInvalidData() {
        String data = "ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Sex;13:00:00;14:30:00;01/05/2023;AA2.25;34;10";
        try {
            Block block = Block.createFromCSV(data);
            fail("Expected RuntimeException to be thrown, but it wasn't.");
        } catch (RuntimeException e) {
            // Exception was thrown, test passed
        }
    }

    @Test
    public void testCreateFromScheduleFormat() {
        // Create a sample data map with "start" and "end" properties
        Map<String, String> data = new HashMap<>();
        data.put("Curso", "ME");
        data.put("Unidade Curricular", "Teoria dos Jogos e dos Contratos");
        data.put("start", "2023-05-07T13:00:00");
        data.put("end", "2023-05-07T14:30:00");

        // Call the createFromScheduleFormat method to create a Block instance
        Block block = Block.createFromScheduleFormat(data);

        // Check that the Block instance was created correctly
        assertEquals("ME", block.data.get("Curso"));
        assertEquals("Teoria dos Jogos e dos Contratos", block.data.get("Unidade Curricular"));
        assertEquals("13:00:00", block.data.get("Hora início da aula"));
        assertEquals("14:30:00", block.data.get("Hora fim da aula"));
        assertEquals("07/05/2023", block.data.get("Data da aula"));
    }

    @Test
    void testCreateFromWebCalendar() {
        Map<String, String> webInfo = new LinkedHashMap<>();
        webInfo.put("Turno", "Manhã");
        webInfo.put("Unidade de execução", "Algoritmos e Estruturas de Dados");
        webInfo.put("Início", "2023-03-14 18:00");
        webInfo.put("Fim", "2023-03-14 19:30");

        Block block = Block.createFromWebCalendar(webInfo);

        assertEquals("Manhã", block.data.get("Turno"));
        assertEquals("Algoritmos e Estruturas de Dados", block.data.get("Unidade Curricular"));
        assertEquals("18:00", block.data.get("Hora início da aula"));
        assertEquals("19:30", block.data.get("Hora fim da aula"));
        assertEquals("14/03/2023", block.data.get("Data da aula"));
        assertEquals("Indefinido", block.data.get("Sala atribuída à aula"));
        assertEquals("Indefinido", block.data.get("Lotação da sala"));
    }

    @Test
    void testGetCSVHeader (){
        assertEquals(Block.getCSVHeader() ,"Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala");
    }

    @Test
    void testGetAsList() {
        Block block = createBlock();
        List<Map<String, String>> list = block.getAsList();
        assertEquals(block.data.size(), list.size());

        List<Map<String, String>> expectedList = Arrays.asList(
                new LinkedHashMap<String, String>() {{ put("Curso", "ME"); }},
                new LinkedHashMap<String, String>() {{ put("Unidade Curricular", "Teoria dos Jogos e dos Contratos"); }},
                new LinkedHashMap<String, String>() {{ put("Turno", "01789TP01"); }},
                new LinkedHashMap<String, String>() {{ put("Turma", "MEA1"); }},
                new LinkedHashMap<String, String>() {{ put("Inscritos no turno", "30"); }},
                new LinkedHashMap<String, String>() {{ put("Dia da semana", "Sex"); }},
                new LinkedHashMap<String, String>() {{ put("Hora início da aula", "13:00:00"); }},
                new LinkedHashMap<String, String>() {{ put("Hora fim da aula", "14:30:00"); }},
                new LinkedHashMap<String, String>() {{ put("Data da aula", "01/05/2023"); }},
                new LinkedHashMap<String, String>() {{ put("Sala atribuída à aula", "AA2.25"); }},
                new LinkedHashMap<String, String>() {{ put("Lotação da sala", "34"); }}
        );

        assertEquals(expectedList.size(), list.size());
        for (int i = 0; i < list.size(); i++) {
            assertEquals(expectedList.get(i), list.get(i));
        }
    }

    @Test
    void testgetAsCsv(){
        Block block = createBlock();
        String result = block.getAsCsv();
        String expected = "ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Sex;13:00:00;14:30:00;01/05/2023;AA2.25;34";
        assertEquals(expected, result);
    }

    @Test
    void testGetAsJson() {
        Block block = createBlock();
        String expectedJson = "{\n"
                + "  \"Curso\": \"ME\",\n"
                + "  \"Unidade Curricular\": \"Teoria dos Jogos e dos Contratos\",\n"
                + "  \"Turno\": \"01789TP01\",\n"
                + "  \"Turma\": \"MEA1\",\n"
                + "  \"Inscritos no turno\": \"30\",\n"
                + "  \"Dia da semana\": \"Sex\",\n"
                + "  \"Hora início da aula\": \"13:00:00\",\n"
                + "  \"Hora fim da aula\": \"14:30:00\",\n"
                + "  \"Data da aula\": \"01/05/2023\",\n"
                + "  \"Sala atribuída à aula\": \"AA2.25\",\n"
                + "  \"Lotação da sala\": \"34\"\n"
                + "}";
        String result = block.getAsJson();
        assertEquals(expectedJson, result);
    }

    @Test
    void testGetAsScheduleFormat() {
        Block block = createBlock();
        Map<String, String> scheduleData = block.getAsScheduleFormat();

        Map<String, String> expectedValues = new LinkedHashMap<>();
        expectedValues.put("Curso", "ME");
        expectedValues.put("Unidade Curricular", "Teoria dos Jogos e dos Contratos");
        expectedValues.put("Turno", "01789TP01");
        expectedValues.put("Turma", "MEA1");
        expectedValues.put("Inscritos no turno", "30");
        expectedValues.put("Dia da semana", "Sex");
        expectedValues.put("start", "2023-05-01T13:00:00");
        expectedValues.put("end", "2023-05-01T14:30:00");
        expectedValues.put("Sala atribuída à aula", "AA2.25");
        expectedValues.put("Lotação da sala", "34");

        for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
            assertEquals(entry.getValue(), scheduleData.get(entry.getKey()));
        }
    }

    public Block createBlock (){
        Map<String, String> data = new LinkedHashMap<>();
        data.put("Curso", "ME");
        data.put("Unidade Curricular", "Teoria dos Jogos e dos Contratos");
        data.put("Turno", "01789TP01");
        data.put("Turma", "MEA1");
        data.put("Inscritos no turno", "30");
        data.put("Dia da semana", "Sex");
        data.put("Hora início da aula", "13:00:00");
        data.put("Hora fim da aula", "14:30:00");
        data.put("Data da aula", "01/05/2023");
        data.put("Sala atribuída à aula", "AA2.25");
        data.put("Lotação da sala", "34");

        return new Block(data);
    }



}