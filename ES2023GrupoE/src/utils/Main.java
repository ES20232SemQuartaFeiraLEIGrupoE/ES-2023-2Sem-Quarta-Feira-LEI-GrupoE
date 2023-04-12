package utils;

public class Main {

	public static void main(String[] args) {
		Utils.csvToFile("test.csv");
		System.out.println("main");

		String filename = "/test.json";
		ArrayList<JSONObject> jsonObjects = JsonReader.readJsonFile(filename);
		for (JSONObject jsonObject : jsonObjects) {
			System.out.println(jsonObject.toJSONString());
	}

}
