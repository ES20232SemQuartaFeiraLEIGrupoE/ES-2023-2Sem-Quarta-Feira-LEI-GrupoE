package iscte.se.SE10.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Classe em java spring que fornece uma resposta HTML para uma solicitação do calendário
 *
 * @author Grupo E
 * @version 1.0
 */

@Controller
public class CalendarController {

    /**
     * Construtor default
     */
    public CalendarController() {
    }

    /**
     * Método que controla as solicitações HTTP GET e retorna uma página HTML
     *
     * @return retorna uma String com o código HTML, os cabeçalhos da resposta e o HttpStatus.OK
     */
    @GetMapping(value = "/calendar", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ResponseEntity<String> getCalendar() {
        String html = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "  <meta charset=\"utf-8\">\n"
                + "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.css\" />\n"
                + "  <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\n"
                + "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js\"></script>\n"
                + "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.js\"></script>\n"
                + "  <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<div class=\"rift\">\n"
                + "        <div class=\"top\">\n"
                + "            <div class=\"flex-c\">\n"
                + "                <h2>Carregar Ficheiro Local</h2>\n"
                + "                <input class=\"support\" type=\"file\" id=\"file-input\">\n"
                + "                <button class=\" btn-load-file\">Carregar Ficheiro</button>\n"
                + "                <h2>Carregar Webcal</h2>\n"
                + "                 <form id=\"url-form\" >\n"
                + "                   <input type=\"text\" id=\"uri\" class=\"webcalform\" name=\"uri\">\n"
                + "                   <button type=\"submit\" class=\"btn-load-file\">Carregar Ficheiro</button>\n"
                + "                 </form>\n"
                + "            </div><br>\n"
                + "        </div>\n"
                + "        <div>\n"
                + "            <h2>Guardar Calendário</h2>\n"
                + "            <button class=\" btn-load-file\" onclick=\"saveToCSV()\">Guardar em CSV</button>\n"
                + "            <button class=\" btn-load-file\" onclick=\"saveToJson()\">Guardar em JSON</button>\n"
                + "        </div>\n"
                + "        <div class=\"mid\">\n"
                + "            <h2>Info</h2>\n"
                + "            <div> \n"
                + "                <h3 class=\"countertxt\">Número de Sobrelotações</h3> \n"
                + "                <h3 class=\"counter\">0 </h3>\n"
                + "            </div>\n"
                + "            <div> \n"
                + "                <h3 class=\"counterotxt\">Número de Sobreposições</h3> \n"
                + "                <h3 class=\"countero\"> 0 </h3>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"adc\">\n"
                + "        <h1> App Calendar </h1> "
                + "        <div>\n"
                + "            <h2>Selecionar Curso</h2>\n"
                + "            <select id=\"coursesDropdown\" class=\"\"></select>\n"
                + "        </div>\n"
                + "        <h3>Selecionar Cadeiras</h3>\n"
                + "        <ul id=\"subjectSelector\"></ul>\n"
                + "    </div>\n"
                + "    <div class=\"calendar\">\n"
                + "\n"
                + "<div class=\"adc\">\n"
                + "  <div id=\"calendar\"></div>\n"
                + "  <script defer src=\"script.js\"></script>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(html, headers, HttpStatus.OK);
    }
}