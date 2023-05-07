package iscte.se.SE10.JUnitTests;

import biweekly.component.VEvent;
import iscte.se.SE10.utils.ConvertFiles;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


class ConvertFilesTest {

    @Test
    public void testConstructor() {
        ConvertFiles cf = new ConvertFiles();
        assertNotNull(cf);
    }

    @Test
    public void testGetRelevantInfo() {
        String description = "Unidade de execução: ME\n" +
                "Turno: 01789TP01\n" +
                "Início: 13:00:00\n" +
                "Fim: 14:30:00\n";

        VEvent event = new VEvent();
        event.setDescription(description);

        Map<String, String> relevantInfo = ConvertFiles.getRelevantInfo(event);
        assertEquals("ME", relevantInfo.get("Unidade de execução"));
        assertEquals("01789TP01", relevantInfo.get("Turno"));
        assertEquals("13:00:00", relevantInfo.get("Início"));
        assertEquals("14:30:00", relevantInfo.get("Fim"));
    }
}