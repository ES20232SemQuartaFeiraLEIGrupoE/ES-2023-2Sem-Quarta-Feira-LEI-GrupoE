package JUnitTests;

import model.Block;
import org.junit.jupiter.api.Test;
import utils.ConvertFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConvertFilesTest {

    @Test
    void csvToJson() {
    }

    @Test
    void csvToArray() {
        String csv_data = "ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Sex;13:00:00;14:30:00;02/12/2022;AA2.25;34";
        File csv_file = createTempFile(csv_data);
        List<Block> blocks = ConvertFiles.csvToArray(csv_file);
        assertEquals(1, blocks.size());
        Block b1 = blocks.get(0);
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
}