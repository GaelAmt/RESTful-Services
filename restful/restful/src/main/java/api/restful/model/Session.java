package api.restful.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Session {
	private int id;
	@XmlTransient private static int nextId = 1; 
	private String nameMovie;
	private int idTheater;
	private String date;
	public Session() {
		
	}
	public Session(String nameMovie, int idTheater, String date) {
		setId(nextId);
		nextId++;
		this.nameMovie = nameMovie;
		this.idTheater = idTheater;
		this.date = date;
	}
	public String getNameMovie() {
		return nameMovie;
	}
	public void setNameMovie(String nameMovie) {
		this.nameMovie = nameMovie;
	}
	public int getIdTheater() {
		return idTheater;
	}
	public void setIdTheater(int idTheater) {
		this.idTheater = idTheater;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
