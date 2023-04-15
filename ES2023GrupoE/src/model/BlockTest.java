package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    @Test
    void setAttribute() {
    }

    @Test
    void getHeader() {
        String header = Block.getHeader();
        assertEquals("course;curricular_unit;shift;team;number_of_subscribers;day_of_week;hour_begin;hour_end;date;room;size_room", header);
    }

    @Test
    void testToString() {
        Block b = new Block("ME","Teoria dos Jogos e dos Contratos","01789TP01","MEA1","30","Sex","13:00:00","14:30:00","02/12/2022","AA2.25","34");
        String block_string = b.toString();
        assertEquals("ME,Teoria dos Jogos e dos Contratos,01789TP01,MEA1,30,Sex,13:00:00,14:30:00,02/12/2022,AA2.25,34\n" , block_string);
    }
}