package model;

import java.lang.reflect.Field;
import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Classe Block é a classe que cria um objeto que contém toda a informação
 * necessária para ser apresentada num horário
 * @author Grupo E
 * @version 1.0
 */
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

	/**
	 * Construtor default
	 */
	public Block (){}

	/**
	 * Construtor de um block com todos os campos de uma linha do horário
	 * @param course curso
	 * @param curricular_unit unidade curricular
	 * @param shift turno
	 * @param team turma
	 * @param number_of_subscribers número de inscritos
	 * @param day_of_week dia da semana
	 * @param hour_begin hora de inicio
	 * @param hour_end hora de fim
	 * @param date data
	 * @param room sala
	 * @param size_room capacidade
	 */
	public Block(String course, String curricular_unit, String shift, String team, String number_of_subscribers,
				 String day_of_week, String hour_begin, String hour_end, String date, String room, String size_room){
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

	/**
	 * Função get curso
	 * @return retorna o curso de um block
	 */
	public String getCourse() {
		return course;
	}

	/**
	 * Função get unidade curricular
	 * @return retorna a unidade curricular de um block
	 */
	public String getCurricular_unit() {
		return curricular_unit;
	}

	/**
	 * Função get hora de inicio
	 * @return retorna a hora de inicio de um block
	 */
	public LocalTime getHour_begin(){
		LocalTime hour = LocalTime.parse(hour_begin);
		return hour;
	}

	/**
	 * Função get hora de fim
	 * @return retorna a hora de fim de um block
	 */
	public LocalTime getHour_end() {
		LocalTime hour = LocalTime.parse(hour_end);
		return hour;
	}

	/**
	 * Função get dia de semana
	 * @return retorna o dia de semana de um block
	 */
	public DayOfWeek getDayOfWeek() {
		DayOfWeek day = DayOfWeek.valueOf(day_of_week.toUpperCase());
		return day;
	}

	/**
	 * Função get turno
	 * @return retorna o turno de um block
	 */
	public String getShift() {
		return shift;
	}

	/**
	 * Função get turma
	 * @return retorna a turma de um block
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * Função get número de inscritos
	 * @return retorna o número de inscritos de um block
	 */
	public String getNumberOfSubscribers() {
		return number_of_subscribers;
	}

	/**
	 * Função get data
	 * @return retorna a data de um block
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Função get sala
	 * @return retorna o número da sala de um block
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * Função get capacidade
	 * @return retorna a capacidade de um block
	 */
	public String getSizeRoom() {
		return size_room;
	}

	/**
	 * Função que altera o valor um campo de um block dado o número do campo
	 * @param attribute número do campo de um block que queremos fazer set
	 * @param data novo valor do campo que vamos fazer set
	 */
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

	/**
	 * Função que devolve o header do block
	 * @return retorna o header
	 */
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
