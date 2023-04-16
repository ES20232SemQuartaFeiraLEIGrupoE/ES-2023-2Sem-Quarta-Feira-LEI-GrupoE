package model;

import java.lang.reflect.Field;

public class Block {
	
	public String course;
	public String curricular_unit;
	public String shift;
	public String team;
	public String number_of_subscribers;
	public String day_of_week;
	public String hour_begin;
	public String hour_end;
	public String date;
	public String room;
	public String size_room;

	public Block (){}

	public Block(String course, String curricular_unit, String shift, String team, String number_of_subscribers,String day_of_week, String hour_begin, String hour_end, String date, String room, String size_room){
		this.course = course;
		this.curricular_unit = curricular_unit;
		this.shift = shift;
		this.team = team;
		this.number_of_subscribers = number_of_subscribers;
		this.day_of_week = day_of_week;
		this.hour_begin = hour_begin;
		this.hour_end = hour_end;
		this.date = date;
		this.room = room;
		this.size_room = size_room;	
	}

	public void setAttribute(int attribute, String data){
		switch (attribute){
			case 0: course = data; break;
			case 1: curricular_unit = data; break;
			case 2: shift = data; break;
			case 3: team = data; break;
			case 4: number_of_subscribers = data; break;
			case 5: day_of_week = data; break;
			case 6: hour_begin = data; break;
			case 7: hour_end = data; break;
			case 8: date = data; break;
			case 9: room = data; break;
			default: size_room = data; break;
		}

	}

    public static String getHeader() {
        StringBuilder header = new StringBuilder();
        Class<Block> blockClass = Block.class;
        Field[] fields = blockClass.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                header.append(";");
            }
            header.append(fields[i].getName());
        }
        return header.toString();
    }

	@Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                this.course, this.curricular_unit, this.shift, this.team, this.number_of_subscribers,
                this.day_of_week, this.hour_begin, this.hour_end, this.date, this.room, this.size_room);
    }
   
}
