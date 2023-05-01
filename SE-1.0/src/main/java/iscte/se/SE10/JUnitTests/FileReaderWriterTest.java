package iscte.se.SE10.JUnitTests;

import iscte.se.SE10.model.Block;
import iscte.se.SE10.utils.FileReaderWriter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class FileReaderWriterTest {

    @Test
    void csvToFile() throws IOException {

        List<Block> blocks = Arrays.asList(
                new Block("ME","Teoria dos Jogos e dos Contratos","01789TP01","MEA1","30","Sex","13:00:00","14:30:00","02/12/2022","AA2.25","34"),
                new Block("ME","Teoria dos Jogos e dos Contratos","01789TP01","MEA1","30","Sex","13:00:00","14:30:00","02/12/2022","AA2.25","34")
        );

        // write data to a CSV file
        String fileName = "Files_for_Tests\\test.csv";
        FileReaderWriter.csvToFile(fileName, blocks);

        // check that the file was created
        assertTrue(Files.exists(Paths.get(fileName)), "CSV file was not created");

        // read the file and check its contents
        List<String> lines = Files.readAllLines(Path.of(fileName));
        assertEquals(3, lines.size());
        assertEquals("course;curricular_unit;shift;team;number_of_subscribers;day_of_week;hour_begin;hour_end;date;room;size_room", lines.get(0));
        assertEquals("ME,Teoria dos Jogos e dos Contratos,01789TP01,MEA1,30,Sex,13:00:00,14:30:00,02/12/2022,AA2.25,34", lines.get(1));
        assertEquals("ME,Teoria dos Jogos e dos Contratos,01789TP01,MEA1,30,Sex,13:00:00,14:30:00,02/12/2022,AA2.25,34", lines.get(2));
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