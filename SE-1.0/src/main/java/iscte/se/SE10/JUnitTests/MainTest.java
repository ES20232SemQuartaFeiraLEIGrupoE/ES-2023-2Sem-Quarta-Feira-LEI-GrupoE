package iscte.se.SE10.JUnitTests;

import iscte.se.SE10.utils.Main;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void textMainInterface() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //Main.textMainInterface();

        String expectedOutput = "-------------- Demo --------------\n" +
                "1. Convert File\n" +
                "2. Show Gui\n" +
                "Insert Option: ";

        assertEquals(expectedOutput, outContent.toString());
    }
}