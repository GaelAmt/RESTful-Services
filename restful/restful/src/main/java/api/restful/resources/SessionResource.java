package api.restful.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import api.restful.dao.Dao;
import api.restful.model.Session;

@Path("/sessions")
public class SessionResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return all the session
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Session> getSessionBrowser() {
		List<Session> s = new ArrayList<Session>();
		s.addAll(Dao.instance.getSessions().values());
		return s;
	}

	// same
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Session> getSessionApp() {
		List<Session> s = new ArrayList<Session>();
		s.addAll(Dao.instance.getSessions().values());
		return s;
	}

	// takes a post request in entrance, and add the session to the list
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newMovie(@FormParam("theaterId") String theaterId, @FormParam("movieName") String movieName,
			@FormParam("date") String date, @Context HttpServletResponse servletResponse) throws IOException {
		if (Dao.instance.getMovies().containsKey(movieName)
				&& Dao.instance.getMovieTheaters().containsKey(Integer.parseInt(theaterId))) {
			Dao.instance.addSession(movieName, Integer.parseInt(theaterId), date);
			servletResponse.sendRedirect("../create_session.jsp");
		}
	}

	// return the details of a session
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_XML)
	public Session getSessionBrowser(@PathParam("id") int id) {
		return Dao.instance.getSessions().get(id);
	}

	// return the details of a session
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Session getMovieApp(@PathParam("id") int id) {
		return Dao.instance.getSessions().get(id);
	}

	// We check that the name in the path is the same as in the xml, if it's the
	// same and the name exists in the collection, we update it.
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response putMovie(JAXBElement<Session> mt, @PathParam("id") int id) {
		int id2 = mt.getValue().getId();
		if (Dao.instance.getSessions().containsKey(id2) && id == id2) {
			Dao.instance.getSessions().get(id2).setDate(mt.getValue().getDate());
			Dao.instance.getSessions().get(id2).setIdTheater(mt.getValue().getIdTheater());
			Dao.instance.getSessions().get(id2).setNameMovie(mt.getValue().getNameMovie());
			return Response.created(uriInfo.getAbsolutePath()).build();
		} else {
			return Response.noContent().build();
		}
	}

	// Same, but for xml text
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.TEXT_XML)
	public Response putMovie2(JAXBElement<Session> mt, @PathParam("id") int id) {
		int id2 = mt.getValue().getId();
		if (Dao.instance.getSessions().containsKey(id2) && id == id2) {
			Dao.instance.getSessions().get(id2).setDate(mt.getValue().getDate());
			Dao.instance.getSessions().get(id2).setIdTheater(mt.getValue().getIdTheater());
			Dao.instance.getSessions().get(id2).setNameMovie(mt.getValue().getNameMovie());
			return Response.created(uriInfo.getAbsolutePath()).build();
		} else {
			return Response.noContent().build();
		}
	}

	// We delete the movie theater and the associated session
	@DELETE
	@Path("/{id}")
	public Response deleteMovie(@PathParam("id") int id) {
		if (Dao.instance.getSessions().containsKey(id)) {
			Dao.instance.getSessions().remove(id);
			return Response.ok().build();
		} else {
			return Response.noContent().build();
		}
	}
	
	@GET
	@Path("/city")
	@Produces(MediaType.TEXT_HTML)
	public String getSessionsInCityHtml(@QueryParam("name") String name) {
		List<Session> sessions = (List<Session>) Dao.instance.getSessionsInCity(name);
		StringBuilder s = new StringBuilder();
		s.append("<!DOCTYPE html>");
		s.append("<html>");
		s.append("<head><title>Sessions in " + name + "</title></head>");
		s.append("<body><h2>Sessions in " + name + "</h2>");
		for (Session session : sessions) {
			s.append("<p>Movie : <a href=\"../movies/" + session.getNameMovie() + "\">" + session.getNameMovie()
					+ "</a>, Date : " + session.getDate() + ", Movie Theater name : "
					+ Dao.instance.getMovieTheaters().get(session.getIdTheater()).getName() + "</p>");
		}
		s.append("</body>");
		s.append("</html>");
		return s.toString();
	}

	@GET
	@Path("/city/{name}")
	@Produces(MediaType.TEXT_XML)
	public Collection<Session> getSessionsInCityBrowser(@PathParam("name") String name) {
		return Dao.instance.getSessionsInCity(name);
	}

	@GET
	@Path("/city/{name}")
	@Produces(MediaType.APPLICATION_XML)
	public Collection<Session> getSessionsInCityApp(@PathParam("name") String name) {
		return Dao.instance.getSessionsInCity(name);
	}
}
