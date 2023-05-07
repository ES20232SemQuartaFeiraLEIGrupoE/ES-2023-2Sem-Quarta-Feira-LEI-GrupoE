package iscte.se.SE10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe Application springboot
 * @author Grupo E
 * @version 1.0
 */

@SpringBootApplication
public class Application {

	/**
	 * Construtor default
	 */
	public Application(){ /* No parameters required */ }

	/**
	 * Método main
	 * @param args Array de String
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
