package api.restful.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class MovieTheater {
	private int id;
	@XmlTransient private static int nextId = 1;
	private String name;
	private String city;
	public MovieTheater() {
		
	}
	public MovieTheater(String name, String city) {
		setId(nextId);
		nextId++;
		this.name = name;
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
