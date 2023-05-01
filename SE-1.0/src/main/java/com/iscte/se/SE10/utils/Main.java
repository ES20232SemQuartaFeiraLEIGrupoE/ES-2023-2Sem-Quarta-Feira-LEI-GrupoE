package com.iscte.se.SE10.utils;


import java.io.*;
import java.util.List;
import java.util.Scanner;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



/**
 * @author Grupo E
 * @version 1.0
 */
public class Main {

	/**
	 * Função main
	 * @param args array de strings
	 */
	public static void main(String[] args) {

		//Changes the look of the File chooser

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}


		// DEMO
		textMainInterface();
//		options();


	}

	public static void textMainInterface (){
		System.out.print("-------------- Demo --------------\n");
		System.out.print("1. Convert File\n");
		System.out.print("2. Show Gui\n");
		System.out.print("Insert Option: ");
	}

//	public static void options(){
//		Scanner scanner = new Scanner(System.in);
//		switch (scanner.nextInt()){
//			case 2:
//				HorarioMain.main(null);
//				break;
//			case 1:
//				System.out.println("-------------- Demo --------------");
//				System.out.println("1. JSON to CSV");
//				System.out.println("2. CSV to JSON");
//				System.out.println("3. JSON to Array");
//				System.out.println("4. CSV to Array");
//				System.out.print ("Insert Option: ");
//				switch (scanner.nextInt()){
//					case 3:
//						File json_file = FileReaderWriter.uploadFile();
//						List<Block> json_list = ConvertFiles.jsonToArrayList(json_file);
//						for(Block b: json_list) System.out.print(b);
//						break;
//					case 4:
//						File csv_file = FileReaderWriter.uploadFile();
//						List<Block> csv_list = ConvertFiles.csvToArray(csv_file);
//						for(Block b: csv_list) System.out.print(b);
//						break;
//					default:
//						File file = FileReaderWriter.uploadFile();
//						FileReaderWriter.saveFileLocal(file);
//				}
//		}
	}

