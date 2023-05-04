package iscte.se.SE10.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JFileChooser;
import java.util.Locale;
import java.util.TimeZone;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import iscte.se.SE10.model.Block;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;

import org.json.simple.JSONObject;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.DateEnd;
import biweekly.util.ICalDate;

/**
 * Classe para ler e escrever em ficheiros
 * 
 * @author Grupo E
 * @version 1.0
 */

public class FileReaderWriter {

	/**
	 *
	 * @return retorna o ficheiro selecionado através do explorador de ficheiros
	 */

	public static File uploadFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Upload File");
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// TODO open schedule
		}
		return fileChooser.getSelectedFile();
	}

	/**
	 * Função que recebe um ficheiro como parâmetro e guarda no formato selecionado
	 * pelo utilizador
	 * 
	 * @param file ficheiro que queremos guardar localmente
	 */

	public static void saveFileLocal(File file) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save File");
		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			String ext = FilenameUtils.getExtension(file.toString());
			switch (ext) {
			case "json":
				ConvertFiles.jsonToCsv(file, fileToSave.getAbsolutePath());
				break;
			case "csv":
				ConvertFiles.csvToJson(file, fileToSave.getAbsolutePath());
				break;
			}
		}

	}

	/**
	 * Função que recebe um URL de onde irá ler os dados existentes
	 * 
	 * @param url  url dos dados a serem lidos
	 * @param file ficheiro recebido como parâmetro onde serão escritos os dados
	 *             lidos do URL
	 * @throws IOException
	 */

	public static void copyURLToFile(URL url, File f) throws IOException {
		FileUtils.copyURLToFile(url, f);
	}

	public static void copyURLToFile2(URI url, File f) throws ClientProtocolException, IOException {
		String userAgent = "-";

		CloseableHttpClient httpclient = HttpClients.custom().setUserAgent(userAgent).build();
		HttpGet httpget = new HttpGet(url);
		httpget.addHeader("Accept-Language", "en-US");
		httpget.addHeader("Cookie", "");

		System.out.println("Executing request " + httpget.getRequestLine());
		try (CloseableHttpResponse response = httpclient.execute(httpget)) {
			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			String body = EntityUtils.toString(response.getEntity());
			System.out.println(body);
			Files.writeString(f.toPath(), body);
		}
	}

	/**
	 * Função que recebe o nome para um ficheiro que será criado através de uma
	 * lista de blocks
	 * 
	 * @param fileName nome do ficheiro onde serão escritos os blocks lidos da lista
	 * @param data     lista de blocks
	 */
	public static void csvToFile(String fileName, List<Block> data) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(fileName));

			writer.println(Block.getHeader()); // cabeçalho do arquivo

			for (Block b : data)
				writer.printf(b.toString());

			writer.close();
		} catch (IOException e) {
			System.err.println("Erro ao criar arquivo CSV: " + e.getMessage());
		}
	}

	/**
	 * Função que recebe um horário (lista de Block) e escreve num ficheiro CSV e
	 * salva num path definido
	 * 
	 * @param data lista de Block
	 */
	public static File scheduleToCsv(List<Block> data) {
		File file = new File("csvteste");

		// falta decidir o path que queremos para o ficheiro gerado
		try {
			
			FileWriter outputFile = new FileWriter(file);
			CSVFormat format = CSVFormat.EXCEL;

			CSVPrinter print = new CSVPrinter(outputFile, format);
			print.printRecord(Block.getHeader().toString());
			for (Block block : data) {

				print.printRecord(block.toStringColumn());
			}
			print.close();
			return file;
		} catch (Exception e) {
			System.out.println("-> FileReaderWriter / scheduleToCsv - Não foi possivel criar o ficheiro CSV");
			e.printStackTrace();

		}
		return file;

	}

	/**
	 * Função que recebe um horário (lista de Block) e escreve num ficheiro JSON e
	 * salva num path definido
	 * 
	 * @param data lista de Block
	 */
	public static JSONObject scheduleToJson(List<Block> data) {
		JSONObject jsonFile = new JSONObject();

		for (Block block : data) {
			jsonFile.put("course", block.getCourse());
			jsonFile.put("curricular_unit", block.getCurricular_unit());
			jsonFile.put("shift", block.getShift());
			jsonFile.put("team", block.getTeam());
			jsonFile.put("number_of_subscribers", block.getNumberOfSubscribers());
			jsonFile.put("day_of_week", block.getDayOfWeek());
			jsonFile.put("hour_begin", block.getHour_begin());
			jsonFile.put("hour_end", block.getHour_end());
			jsonFile.put("date", block.getDate());
			jsonFile.put("room", block.getRoom());
			jsonFile.put("size_room", block.getSizeRoom());
		}
		try {
			// falta definir o path que queremos para o ficheiro gerado
			FileWriter fileWriter = new FileWriter("jsonteste");
			fileWriter.write(jsonFile.toJSONString() + "\n");
			fileWriter.close();
			return jsonFile;
		} catch (Exception e) {
			System.out.println("-> FileReaderWriter / scheduleToJson - Não foi possivel criar o ficheiro JSON");
			e.printStackTrace();
		}
		return jsonFile;
	}

	/**
	 * Função que recebe um URI de webcal e transforma num ficheiro json
	 * 
	 * 
	 * @param
	 */

	public static String icsToBlockList(URI uri) {
		String URI = uri.toString();
		String httpsURI = "https" + URI.split(":")[1];
		File csv = new File("");
		String course;
		String curricular_unit;
		String shift;
		String team;
		String number_of_subscribers;
		String day_of_week;
		String hour_begin;
		String hour_end;
		String date;
		String room;
		String size_room;
		List<Block> blockList = new ArrayList<Block>();
		SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
		SimpleDateFormat outputFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        outputFormat.setTimeZone(TimeZone.getDefault());
        outputFormat2.setTimeZone(TimeZone.getDefault());
		File f = new File("temp");
		try {
			// fazer download do ficheiro ics para um ficheiro temporário f
			FileUtils.copyURLToFile(new URL(httpsURI), f);
			// converter o ficherio no tipo ICalendar da biblioteca Biweekly
			ICalendar ical = Biweekly.parse(f).first();
			List<VEvent> events = ical.getEvents();
			for (VEvent e : events) {
				String[] Description = e.getDescription().toString().split("\n");
				course = null;
				curricular_unit = Description[1].split(":")[1].strip();
				team = null;
				number_of_subscribers = "0";
				//parse start date into a date class with format EEE MMM dd HH:mm:ss zzz yyyy to parse it into output format yyyymmddThhmmssZ and then to string
				hour_begin = outputFormat.format(inputFormat.parse(e.getDateStart().getValue().toString())).toString();
				//same as above but for end date
				hour_end = outputFormat.format(inputFormat.parse(e.getDateEnd().getValue().toString())).toString();
				date = outputFormat2.format(inputFormat.parse(e.getDateStart().getValue().toString())).toString();
				room = e.getLocation().getValue().toString().split(" ")[0];
				size_room = "30";
				// some events have 15 fields, the others have 17.
				if (Description.length == 15) {
					shift = null;
					// get the day of the week
					LocalDate date1 = LocalDate.parse(Description[5].split(" ")[1]);
					DayOfWeek dw = date1.getDayOfWeek();
					day_of_week = dw.getDisplayName(TextStyle.FULL, new Locale("pt")).toUpperCase().substring(0, 3);
				} else {
					shift = Description[3].split(":")[1];
					// get the day of the week
					LocalDate date1 = LocalDate.parse(Description[4].split(" ")[1]);
					DayOfWeek dw = date1.getDayOfWeek();
					day_of_week = dw.getDisplayName(TextStyle.FULL, new Locale("pt")).toUpperCase().substring(0, 3);

				}
				blockList.add(new Block(course, curricular_unit, shift, team, number_of_subscribers,day_of_week, hour_begin, hour_end, date, room, size_room));
			}
			csv= scheduleToCsv(blockList);
			return ConvertFiles.csvToJson2(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return csv.toString();

	}

}
