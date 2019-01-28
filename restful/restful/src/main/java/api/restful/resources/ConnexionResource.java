package api.restful.resources;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import api.restful.dao.Dao;

@Path("/Connexion")
public class ConnexionResource {

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newMovie(@FormParam("login") String login, @FormParam("password") String password,
			@Context HttpServletResponse servletResponse) throws IOException {
		try {
			// We check the connection
			if (login != null && !(login.equals("")) && Dao.instance.getUser().containsKey(login)
					&& Dao.instance.getUser().get(login).getpassword().equals(password)) {
				servletResponse.sendRedirect("../menu.jsp");
			} else {
				servletResponse.sendRedirect("../connection.jsp");
			}
		} catch (NumberFormatException e) {
			System.out.println(e.toString());
		}
	}

}
