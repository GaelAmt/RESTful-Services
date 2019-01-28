package api.restful.dao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.restful.model.*;

public enum Dao {
	instance;
	
	private Map<Integer, MovieTheater> movieTheaters;
	private Map<String, Movie> movies;
	private Map<Integer, Session> sessions;
	private Map<String, User> user;
	
	private Dao() {
		movieTheaters = new HashMap<Integer, MovieTheater>();
		movies = new HashMap<String, Movie>();
		sessions = new HashMap<Integer, Session>();
		user = new HashMap<String, User>();
		
		//We add movieTheaters, movies and session to the corresponding HashMap in this part
		Movie movie1 = new Movie("The Lord of the Rings: The Return of the King", 13, 201, "English", "None", "Elijah Wood, Sean Astin, "
				+ "Ian McKellen, Andy Serkis, Viggo Mortensen, Orlando Bloom");
		Movie movie2 = new Movie("Kimi No Na Wa", 13, 106, "Japanese", "English", "Miyamizu Mitsuha, Tachibana Taki");
		Movie movie3 = new Movie("Django Unchained", 18, 164, "English", "None", "Jamie Foxx, Christoph Waltz, Leonardo DiCaprio");
		Movie movie4 = new Movie("Forrest Gump", 13, 140, "English", "None", "Tom Hanks, Gary Sinise, Robin Wright");
		Movie movie5 = new Movie("Last Train to Busan", 18, 118, "English", "None", "Gong Yoo, Yumi Jung, Dong-seok Ma");
		
		MovieTheater mt1 = new MovieTheater("Pathe", "Paris");
		MovieTheater mt2 = new MovieTheater("Carmes theater", "Orleans");
		MovieTheater mt3 = new MovieTheater("Pathe", "Orleans");
		MovieTheater mt4 = new MovieTheater("Gael's Theater", "Villejuif");
		
		Session session1 = new Session("The Lord of the Rings: The Return of the King", mt2.getId(), "17/12/18 20:00");
		Session session2 = new Session("The Lord of the Rings: The Return of the King", mt2.getId(), "19/12/18 20:00");
		Session session3 = new Session("The Lord of the Rings: The Return of the King", mt2.getId(), "21/12/18 21:00");
		Session session4 = new Session("The Lord of the Rings: The Return of the King", mt2.getId(), "23/12/18 21:00");
		Session session5 = new Session("Kimi No Na Wa", mt3.getId(), "18/12/18 15:00");
		Session session6 = new Session("Kimi No Na Wa", mt3.getId(), "19/12/18 16:00");
		Session session7 = new Session("Kimi No Na Wa", mt3.getId(), "20/12/18 15:00");
		Session session8 = new Session("Django Unchained", mt2.getId(), "21/12/18 11:00");
		Session session9 = new Session("Django Unchained", mt2.getId(), "20/12/18 10:00");
		Session session10 = new Session("Django Unchained", mt2.getId(), "15/12/18 15:00");
		Session session11 = new Session("Forrest Gump", mt1.getId(), "17/12/18 20:00");
		Session session12 = new Session("Forrest Gump", mt1.getId(), "18/12/18 20:00");
		Session session13 = new Session("Forrest Gump", mt1.getId(), "20/12/18 20:00");
		Session session14 = new Session("Kimi No Na Wa", mt4.getId(), "18/12/18 12:00");
		Session session15 = new Session("Kimi No Na Wa", mt4.getId(), "17/12/18 12:00");
		Session session16 = new Session("Kimi No Na Wa", mt4.getId(), "19/12/18 12:00");
		Session session17 = new Session("Last Train to Busan", mt2.getId(), "17/12/18 15:00");
		Session session18 = new Session("Last Train to Busan", mt2.getId(), "17/12/18 18:00");
		Session session19 = new Session("Last Train to Busan", mt3.getId(), "22/12/18 16:00");
		Session session20 = new Session("Last Train to Busan", mt3.getId(), "23/12/18 16:00");
		Session session21 = new Session("Last Train to Busan", mt3.getId(), "22/12/18 16:00");
		
		User user1 = new User("admin", "admin");
		User user2 = new User("Paul","123");
		User user3 = new User("truc","bidule");
		
		movies.put(movie1.getTitle(), movie1);
		movies.put(movie2.getTitle(), movie2);
		movies.put(movie3.getTitle(), movie3);
		movies.put(movie4.getTitle(), movie4);
		movies.put(movie5.getTitle(), movie5);
		
		movieTheaters.put(mt1.getId(), mt1);
		movieTheaters.put(mt2.getId(), mt2);
		movieTheaters.put(mt3.getId(), mt3);
		movieTheaters.put(mt4.getId(), mt4);
		
		//We use put in this case instead of addSession because every session added was manually checked
		sessions.put(session1.getId(), session1);
		sessions.put(session2.getId(), session2);
		sessions.put(session3.getId(), session3);
		sessions.put(session4.getId(), session4);
		sessions.put(session5.getId(), session5);
		sessions.put(session6.getId(), session6);
		sessions.put(session7.getId(), session7);
		sessions.put(session8.getId(), session8);
		sessions.put(session9.getId(), session9);
		sessions.put(session10.getId(), session10);
		sessions.put(session11.getId(), session11);
		sessions.put(session12.getId(), session12);
		sessions.put(session13.getId(), session13);
		sessions.put(session14.getId(), session14);
		sessions.put(session15.getId(), session15);
		sessions.put(session16.getId(), session16);
		sessions.put(session17.getId(), session17);
		sessions.put(session18.getId(), session18);
		sessions.put(session19.getId(), session19);
		sessions.put(session20.getId(), session20);
		sessions.put(session21.getId(), session21);
		
		user.put(user1.getlogin(), user1);
		user.put(user2.getlogin(), user2);
		user.put(user3.getlogin(), user3);
	}
	
	public Map<Integer, MovieTheater> getMovieTheaters() {
		return movieTheaters;
	}

	public Map<String, Movie> getMovies() {
		return movies;
	}

	public Map<Integer, Session> getSessions() {
		return sessions;
	}
	
	public Map<String, User> getUser(){
		return user;
	}

	//We need to check that the name of the movie and theater exist
	//If it exists, we add the session to the HashMap and returns true.
	//We return false if one doesn't exist
	public Boolean addSession(String movieName, int theaterId, String date) {
		if (movieTheaters.containsKey(theaterId) && movies.containsKey(movieName) && !(date.equals(""))) {
			Session s = new Session(movieName, theaterId, date);
			sessions.put(s.getId(), s);
			return true;
		} else {
			return false;
		}
	}
	
	//Return every session in a city
	public Collection<Session> getSessionsInCity(String city){
		List<MovieTheater> mt = new ArrayList<MovieTheater>();
		List<Session> s = new ArrayList<Session>();
		
		for(MovieTheater movieTheater : movieTheaters.values()) {
			if (movieTheater.getCity().equals(city)) {
				mt.add(movieTheater);
			}
		}
		
		for(MovieTheater movieTheater : mt) {
			for (Session session : sessions.values()) {
				if (session.getIdTheater() == movieTheater.getId()) {
					s.add(session);
				}
			}
		}
		return s;
	}

	//If we remove a movie from the list, we also remove all the session associated to it 
	public void removeMovie(String name) {
		if (movies.containsKey(name)) {
			movies.remove(name);
			List<Session> s = new ArrayList<Session>();
			s.addAll(sessions.values());
			for(Session session : s) {
				if (session.getNameMovie().equals(name)) {
					sessions.remove(session.getId());
				}
			}
		}
	}
	
	//If we remove a movie theater from the list, we also remove all the session associated to it
	public void removeMovieTheater(int id) {
		if (movieTheaters.containsKey(id)) {
			movieTheaters.remove(id);
			for (Session s : sessions.values()) {
				if (s.getIdTheater() == id) {
					sessions.remove(id);
				}
			}
		}
	}
}
