package iscte.se.SE10.JUnitTests;

import iscte.se.SE10.utils.Utils;
import org.junit.jupiter.api.Test;
import java.io.File;

import static iscte.se.SE10.utils.Utils.downloadWebCall;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void testConstructor() {
        Utils utils = new Utils();
        assertNotNull(utils);
    }

    @Test
    void testDownloadWebCall() {
        String uri = "webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=tpbba@iscte.pt&password=KslsHdONYQqWJKdMse5rqh7p0kD8C91iZ8omnHEDpS5qgEGXb3khkeSKaXCUpDhmzTONGVM3IYU4dNuvQkftyfa8DstavuauoXMqrYqdByavW9Juyz1aIjHcrDmebCAS";
        File downloadedFile = downloadWebCall(uri);
        assertTrue(downloadedFile.exists());
        downloadedFile.delete();
    }
}