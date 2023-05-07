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
    public CalendarController() {/* No parameters required */ }

    /**
     * Método que controla as solicitações HTTP GET e retorna uma página HTML
     *
     * @return retorna uma String com o código HTML, os cabeçalhos da resposta e o HttpStatus.OK
     */
    @GetMapping(value = "/calendar", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ResponseEntity<String> getCalendar() {
        String html = """
                <!DOCTYPE html>
                <html>
                <head>
                  <meta charset="utf-8">
                  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.css" />
                  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
                  <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.js"></script>
                  <link rel="stylesheet" type="text/css" href="styles.css">
                </head>
                <body>
                <div class="rift">
                        <div class="top">
                            <div class="flex-c">
                                <h2>Carregar Ficheiro Local</h2>
                                <input class="support" type="file" id="file-input">
                                <button class=" btn-load-file">Carregar Ficheiro</button>
                                <h2>Carregar Webcal</h2>
                                 <form id="url-form" >
                                   <input type="text" id="uri" class="webcalform" name="uri">
                                   <button type="submit" class="btn-load-file">Carregar Ficheiro</button>
                                 </form>
                            </div><br>
                        </div>
                        <div>
                            <h2>Guardar Calendário</h2>
                            <button class=" btn-load-file" onclick="saveToCSV()">Guardar em CSV</button>
                            <button class=" btn-load-file" onclick="saveToJson()">Guardar em JSON</button>
                        </div>
                        <div class="mid">
                            <h2>Info</h2>
                            <div>\s
                                <h3 class="countertxt">Número de Sobrelotações</h3>\s
                                <h3 class="counter">0 </h3>
                            </div>
                            <div>\s
                                <h3 class="counterotxt">Número de Sobreposições</h3>\s
                                <h3 class="countero"> 0 </h3>
                            </div>
                        </div>
                    </div>
                    <div class="adc">
                        <h1> App Calendar </h1>         <div>
                            <h2>Selecionar Curso</h2>
                            <select id="coursesDropdown" class=""></select>
                        </div>
                        <h3>Selecionar Cadeiras</h3>
                        <ul id="subjectSelector"></ul>
                    </div>
                    <div class="calendar">

                <div class="adc">
                  <div id="calendar"></div>
                  <script defer src="script.js"></script>
                    </div>
                </body>
                </html>""";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(html, headers, HttpStatus.OK);
    }
}