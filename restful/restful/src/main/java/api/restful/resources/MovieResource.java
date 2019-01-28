package api.restful.resources;

import api.restful.dao.Dao;
import api.restful.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

@Path("/movies")
public class MovieResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of movies in text format
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Movie> getMoviesBrowser() {
		List<Movie> movies = new ArrayList<Movie>();
		movies.addAll(Dao.instance.getMovies().values());
		return movies;
	}

	// return the list in xml format for applications
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Movie> getMoviesApp() {
		List<Movie> movies = new ArrayList<Movie>();
		movies.addAll(Dao.instance.getMovies().values());
		return movies;
	}

	// takes a post request in entrance, and add the movie to the list
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newMovie(@FormParam("name") String name, @FormParam("age") String age,
			@FormParam("duration") String duration, @FormParam("language") String language,
			@FormParam("subtitles") String subtitles, @FormParam("actor") String actor,
			@Context HttpServletResponse servletResponse) throws IOException {
		try {
			int ageRequired = Integer.parseInt(age);
			int duration2 = Integer.parseInt(duration);
			// We add the movie to the list if the name is not taken and is not empty
			if (name != null && !(name.equals("")) && !(Dao.instance.getMovies().containsKey(name))) {
				Movie movie = new Movie(name, ageRequired, duration2, language, subtitles, actor);
				Dao.instance.getMovies().put(movie.getTitle(), movie);
				servletResponse.sendRedirect("../create_movie.jsp");
			}
		} catch (NumberFormatException e) {
			System.out.println(e.toString());
		}
	}

	// return the details of a movie
	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_HTML)
	public String getMovieHtml(@PathParam("name") String name) {
		Movie m = Dao.instance.getMovies().get(name);
		StringBuilder s = new StringBuilder();
		s.append("<html><head><title>" + name + "</title></head>");
		s.append("<body>");
		s.append("<h2>" + m.getTitle() + "</h2>");
		s.append("Age required: " + m.getAgeRequired() + "</br>");
		s.append("Duration (in minutes): " + m.getDuration() + "</br>");
		s.append("Language: " + m.getLanguage() + "</br>");
		s.append("Subtitles: " + m.getSubtitles() + "</br>");
		s.append("Actor(s): " + m.getActor() + "</br>");
		s.append("</body></html>");
		return s.toString();
	}

	// return the details of a movie
	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_XML)
	public Movie getMovieBrowser(@PathParam("name") String name) {
		return Dao.instance.getMovies().get(name);
	}

	// return the details of a movie
	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_XML)
	public Movie getMovieApp(@PathParam("name") String name) {
		return Dao.instance.getMovies().get(name);
	}

	// We check that the name in the path is the same as in the xml, if it's the
	// same and the
	// name exists in the collection, we update it.
	// We return status 204 if if it doesn't exist
	@PUT
	@Path("/{name}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response putMovie(JAXBElement<Movie> movie, @PathParam("name") String name) {
		Movie m = movie.getValue();
		if (name.equals(m.getTitle()) && Dao.instance.getMovies().containsKey(m.getTitle())) {
			Dao.instance.getMovies().put(m.getTitle(), m);
			return Response.created(uriInfo.getAbsolutePath()).build();
		} else {
			return Response.noContent().build();
		}
	}

	// Same, but for xml text
	@PUT
	@Path("/{name}")
	@Consumes(MediaType.TEXT_XML)
	public Response putMovie2(JAXBElement<Movie> movie, @PathParam("name") String name) {
		Movie m = movie.getValue();
		if (name.equals(m.getTitle()) && Dao.instance.getMovies().containsKey(m.getTitle())) {
			Dao.instance.getMovies().put(m.getTitle(), m);
			return Response.created(uriInfo.getAbsolutePath()).build(); // status 201 Resource created
		} else {
			return Response.noContent().build(); // status 204 No Content
		}
	}

	// We delete the movie and the associated session
	@DELETE
	@Path("/{name}")
	public Response deleteMovie(@PathParam("name") String name) {
		if (Dao.instance.getMovies().containsKey(name)) {
			//Dao.instance.getMovies().remove(name);
			Dao.instance.removeMovie(name);
			return Response.ok().build(); // status 200 Ok
		} else {
			return Response.noContent().build();
		}
	}
}
