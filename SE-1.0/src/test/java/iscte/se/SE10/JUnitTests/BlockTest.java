package iscte.se.SE10.JUnitTests;//package iscte.se.SE10.JUnitTests;
//
//import iscte.se.SE10.model.Block;
//import org.junit.jupiter.api.Test;
//
//import java.time.DayOfWeek;
//import java.time.LocalTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class BlockTest {
//
//    @Test
//    void setAttribute() {
//        Block block = new Block();
//
//        // Set the attributes using the setAttribute() method
//        block.setAttribute(0, "ME");
//        block.setAttribute(1, "Teoria dos Jogos e dos Contratos");
//        block.setAttribute(2, "01789TP01");
//        block.setAttribute(3, "MEA1");
//        block.setAttribute(4, "30");
//        block.setAttribute(5, "Sex");
//        block.setAttribute(6, "13:00:00");
//        block.setAttribute(7, "14:30:00");
//        block.setAttribute(8, "02/12/2022");
//        block.setAttribute(9, "AA2.25");
//        block.setAttribute(10, "34");
//
//
//        assertEquals("ME", block.getCourse());
//        assertEquals("Teoria dos Jogos e dos Contratos", block.getCurricular_unit());
//        assertEquals("01789TP01", block.getShift());
//        assertEquals("MEA1", block.getTeam());
//        assertEquals("30", block.getNumberOfSubscribers());
//        assertEquals("Sex", block.day_of_week);
//        assertEquals("13:00:00", block.hour_begin);
//        assertEquals("14:30:00", block.hour_end);
//        assertEquals("02/12/2022", block.getDate());
//        assertEquals("AA2.25", block.getRoom());
//        assertEquals("34", block.getSizeRoom());
//
//    }
//
//    @Test
//    void getHeader() {
//        String header = Block.getHeader();
//        assertEquals("course;curricular_unit;shift;team;number_of_subscribers;day_of_week;hour_begin;hour_end;date;room;size_room", header);
//    }
//
//
//
//    @Test
//    void testToString() {
//        Block b = new Block("ME","Teoria dos Jogos e dos Contratos","01789TP01","MEA1","30","Sex","13:00:00","14:30:00","02/12/2022","AA2.25","34");
//        String block_string = b.toString();
//        assertEquals("ME,Teoria dos Jogos e dos Contratos,01789TP01,MEA1,30,Sex,13:00:00,14:30:00,02/12/2022,AA2.25,34\n" , block_string);
//    }
//
//    @Test
//    void getHour_begin() {
//        String hour = "13:00:00";
//        Block b = new Block();
//        b.setAttribute(6, hour);
//        assertEquals(LocalTime.parse(hour), b.getHour_begin());
//    }
//
//    @Test
//    void getHour_end() {
//        String hour = "13:00:00";
//        Block b = new Block();
//        b.setAttribute(7, hour);
//        assertEquals(LocalTime.parse(hour), b.getHour_end());
//    }
//
//    @Test
//    void getDayOfWeek() {
//        String day = "Monday";
//        Block b = new Block();
//        b.setAttribute(5 , day);
//        assertEquals(DayOfWeek.MONDAY , b.getDayOfWeek());
//    }
//}