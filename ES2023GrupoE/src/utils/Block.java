package utils;

public class Block {
	
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
	

}
