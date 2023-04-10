package utils;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Utils.csvToFile("test.csv");
		System.out.println("main");
		List<Block> list = Utils.csvToArray("exemplo_horario.csv");
		for (Block b: list) System.out.println(b);
	}

}
