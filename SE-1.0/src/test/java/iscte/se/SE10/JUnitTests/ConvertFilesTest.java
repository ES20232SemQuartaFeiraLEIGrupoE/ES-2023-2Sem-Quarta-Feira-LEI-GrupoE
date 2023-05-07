package iscte.se.SE10.JUnitTests;

import biweekly.component.VEvent;
import iscte.se.SE10.utils.ConvertFiles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;



class ConvertFilesTest {

    @Test
     void testConstructor() {
        ConvertFiles cf = new ConvertFiles();
        Assertions.assertNotNull(cf);
    }

    @Test
     void testGetRelevantInfo() {
        String description = "Unidade de execução: ME\n" +
                "Turno: 01789TP01\n" +
                "Início: 13:00:00\n" +
                "Fim: 14:30:00\n";

        VEvent event = new VEvent();
        event.setDescription(description);

        Map<String, String> relevantInfo = ConvertFiles.getRelevantInfo(event);
        Assertions.assertEquals("ME", relevantInfo.get("Unidade de execução"));
        Assertions.assertEquals("01789TP01", relevantInfo.get("Turno"));
        Assertions.assertEquals("13:00:00", relevantInfo.get("Início"));
        Assertions.assertEquals("14:30:00", relevantInfo.get("Fim"));
    }
}