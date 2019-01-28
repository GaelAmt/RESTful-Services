package api.restful.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import api.restful.dao.Dao;
import api.restful.model.MovieTheater;

@Path("/movieTheaters")
public class MovieTheaterResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of movie theaters in text format
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<MovieTheater> getMovieTheaterBrowser() {
		List<MovieTheater> mt = new ArrayList<MovieTheater>();
		mt.addAll(Dao.instance.getMovieTheaters().values());
		return mt;
	}

	// return the list in xml format for applications
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<MovieTheater> getMovieTheaterApp() {
		List<MovieTheater> movies = new ArrayList<MovieTheater>();
		movies.addAll(Dao.instance.getMovieTheaters().values());
		return movies;
	}

	// takes a post request in entrance, and add the movie theater to the list
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newMovieTheater(@FormParam("name") String name, @FormParam("location") String location,
			@Context HttpServletResponse servletResponse) throws IOException {
		// We add the movie theater to the list if the name is not taken and is not
		// empty
		if (name != null && !(name.equals("")) && !(Dao.instance.getMovies().containsKey(name))) {
			MovieTheater mt = new MovieTheater(name, location);
			Dao.instance.getMovieTheaters().put(mt.getId(), mt);
			servletResponse.sendRedirect("../create_movie_theater.jsp");
		}
	}

	// return the details of a movie theater
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_XML)
	public MovieTheater getMovieTheaterBrowser(@PathParam("id") int id) {
		return Dao.instance.getMovieTheaters().get(id);
	}

	// return the details of a movie
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public MovieTheater getMovieTheaterApp(@PathParam("id") int id) {
		return Dao.instance.getMovieTheaters().get(id);
	}

	// We check that the name in the path is the same as in the xml, if it's the
	// same and the name exists in the collection, we update it.
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response putMovieTheater(JAXBElement<MovieTheater> mt, @PathParam("id") int id) {
		int id2 = mt.getValue().getId();
		if (Dao.instance.getMovieTheaters().containsKey(id2) && id == id2) {
			Dao.instance.getMovieTheaters().get(id2).setName(mt.getValue().getName());
			Dao.instance.getMovieTheaters().get(id2).setCity(mt.getValue().getCity());
			return Response.created(uriInfo.getAbsolutePath()).build();
		} else {
			return Response.noContent().build();
		}
	}

	// Same, but for xml text
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.TEXT_XML)
	public Response putMovieTheater2(JAXBElement<MovieTheater> mt, @PathParam("id") int id) {
		int id2 = mt.getValue().getId();
		if (Dao.instance.getMovieTheaters().containsKey(id2)) {
			Dao.instance.getMovieTheaters().put(id2, mt.getValue());
			return Response.created(uriInfo.getAbsolutePath()).build();
		} else {
			return Response.noContent().build();
		}
	}

	// We delete the movie theater and the associated session
	@DELETE
	@Path("/{id}")
	public Response deleteMovieTheater(@PathParam("id") int id) {
		if (Dao.instance.getMovieTheaters().containsKey(id)) {
			Dao.instance.getMovieTheaters().remove(id);
			return Response.ok().build();
		} else {
			return Response.noContent().build();
		}
	}
}
