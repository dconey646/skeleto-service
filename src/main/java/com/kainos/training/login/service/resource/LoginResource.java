package com.kainos.training.login.service.resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.kainos.training.login.service.model.User;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {
	private final String requiredUsername;
	private final String requiredPassword;

	public LoginResource(String requiredUsername, String requiredPassword) {
		this.requiredUsername = requiredUsername;
		this.requiredPassword = requiredPassword;
	}
	
	@POST
	public Response login(User user) {

		if (user.getUsername() != null && user.getUsername().equalsIgnoreCase(requiredUsername)) {
			if (user.getPassword() != null && user.getPassword().equals(requiredPassword)) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
