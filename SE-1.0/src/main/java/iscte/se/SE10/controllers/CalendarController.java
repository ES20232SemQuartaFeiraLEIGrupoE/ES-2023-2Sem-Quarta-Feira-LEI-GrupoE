package iscte.se.SE10.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalendarController {

    @GetMapping(value = "/calendar", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ResponseEntity<String> getCalendar() {
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.css\" />\n" +
                "  <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\n" +
                "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js\"></script>\n" +
                "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.js\"></script>\n" +
                "  <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n" +
                "</head>\n" +
                "\n" +
                "<input class=\"custom-select\" type=\"file\" id=\"file-input\">" +
                "<t>      </t>" +
                "<button class=\" btn-load-file\">Load file</button>" +
                "\n" +
                "<button class=\" btn-load-file\" onclick=\"saveBlocksToApi()\">Save Blocks to API</button>" +
                "<t>      </t>" +
                "<select id=\"coursesDropdown\" class=\"custom-select\"></select>\n" +
                "<ul id=\"subjectSelector\">" +

                "</ul>" +
                " <div> <t class=\"countertxt\">Quantidade de Superlotações = </t> <t class=\"counter\">0 </t></div>\n" +
                " <div> <t class=\"counterotxt\">Quantidade de Sobreposições = </t> <t class=\"countero\">0 </t></div>" +
                "<body>\n" +
                "  <div id=\"calendar\"></div>\n" +
                "  <script defer src=\"script.js\"></script>\n" +
                "</body>\n" +
                "</html>";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(html, headers, HttpStatus.OK);
    }
}