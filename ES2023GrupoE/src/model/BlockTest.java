package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    @Test
    void setAttribute() {
        Block block = new Block();

        // Set the attributes using the setAttribute() method
        block.setAttribute(0, "ME");
        block.setAttribute(1, "Teoria dos Jogos e dos Contratos");
        block.setAttribute(2, "01789TP01");
        block.setAttribute(3, "MEA1");
        block.setAttribute(4, "30");
        block.setAttribute(5, "Sex");
        block.setAttribute(6, "13:00:00");
        block.setAttribute(7, "14:30:00");
        block.setAttribute(8, "02/12/2022");
        block.setAttribute(9, "AA2.25");
        block.setAttribute(10, "34");


        assertEquals("ME", block.course);
        assertEquals("Teoria dos Jogos e dos Contratos", block.curricular_unit);
        assertEquals("01789TP01", block.shift);
        assertEquals("MEA1", block.team);
        assertEquals("30", block.number_of_subscribers);
        assertEquals("Sex", block.day_of_week);
        assertEquals("13:00:00", block.hour_begin);
        assertEquals("14:30:00", block.hour_end);
        assertEquals("02/12/2022", block.date);
        assertEquals("AA2.25", block.room);
        assertEquals("34", block.size_room);

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