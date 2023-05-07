package iscte.se.SE10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe para teste inicial de arranque do projeto
 * @author Grupo E
 * @version 1.0
 */
@Controller
public class MyController {

    /**
     * Construtor default
     */
    public MyController(){
        // No Initialization required
    }

    /**
     * Método que retorna Hello World! quando se dá um request
     * @return retorna uma String Hello World!
     */
    @GetMapping("/some-page")
    public String handleSomePageRequest() {
        return "Hello World!";
    }
}