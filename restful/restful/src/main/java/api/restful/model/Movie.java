package api.restful.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Movie {
	private String title;
	private int ageRequired;
	private int duration;
	private String language;
	private String subtitles;
	private String actor;
	public Movie() {
		
	}
	public Movie(String title, int ageRequired, int duration, String language, String subtitles, String actor) {
		this.title = title;
		this.ageRequired = ageRequired;
		this.duration = duration;
		this.language = language;
		this.subtitles = subtitles;
		this.actor = actor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAgeRequired() {
		return ageRequired;
	}
	public void setAgeRequired(int ageRequired) {
		this.ageRequired = ageRequired;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSubtitles() {
		return subtitles;
	}
	public void setSubtitles(String subtitles) {
		this.subtitles = subtitles;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	
}
