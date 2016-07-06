package com.kainos.training.login.service.resource;

import com.kainos.training.login.service.model.Users;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/home")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
//    private final String requiredUsername;
//    private final String requiredPassword;

    ArrayList<Users> usersArrayList;


    public LoginResource(String requiredUsername, String requiredPassword) {
        usersArrayList = new ArrayList<>();
        if(requiredUsername != null) {
            addUser(requiredUsername.toLowerCase(), requiredPassword);
        }
    }

    private void addUser(String requiredUsername, String requiredPassword) {
        // to stop issues when null users are entered
        if (requiredUsername != null) {
            usersArrayList.add(new Users(requiredUsername.toLowerCase(), requiredPassword));
        }
    }

    @Path("/login")
    @POST
    public Response login(@FormParam("username") String username,
           @FormParam("password") String password) {

        if(username == null) {
            return Response.status(401).entity("Username cannot be empty.").build();
        } else if (this.checkLoginDetailsAreCorrect(username.toLowerCase(), password)) {
            return Response.status(204).entity("Login Success").build();
        } else if (!this.checkLoginDetailsAreCorrect(username.toLowerCase(), password)) {
            return Response.status(401).entity("Incorrect Username/Password").build();
        } else {
            return Response.status(503).entity("Server unavailable").build();
        }

    }

    @Path("/register")
    @POST
    public Response register(@FormParam("username") String requiredUsername,
                             @FormParam("password") String requiredPassword) {
        if(requiredUsername == null || requiredUsername.equals("")) {
            return Response.status(401).entity("Username cannot be null/empty during registration").build();
        } else if(requiredPassword == null || requiredPassword.equals("")) {
            return Response.status(401).entity("Password cannot be null/empty during registration").build();
        } else if(this.checkUsernameExists(requiredUsername.toLowerCase())) {
            return Response.status(401).entity("User already exists, unable to create.").build();
        } else if(!this.checkUsernameExists(requiredUsername.toLowerCase())) {
            addUser(requiredUsername.toLowerCase(), requiredPassword);
            return Response.status(204).entity("Registration Successful").build();
        } else {
            // service unavailable
            return Response.status(503).build();
        }
    }

    @Path("/changePassword")
    @POST
    public Response changePassword(@FormParam("username") String username,
                                   @FormParam("password") String password,
                                   @FormParam("newPassword") String newPassword) {


        for(Users users : usersArrayList) {
            // checks if login details are correct
            if(checkLoginDetailsAreCorrect(username.toLowerCase(), password)) {
                // if new password meets current standards of not null/empty it will clear that user and add it as new
                // user with the new password
                if(newPassword != null && !newPassword.equals("")) {
                    users.setPassword(newPassword);
                    usersArrayList.clear();
                    addUser(username.toLowerCase(), newPassword);
                    return login(username.toLowerCase(), newPassword);
                } else {
                    return Response.status(401).entity("New password cannot be empty/null.").build();
                }

            } else {
                return Response.status(401).entity("Incorrect username/password, " +
                        "unable to change password.").build();
            }
        }

        return Response.status(503).build();
    }



    private Boolean checkLoginDetailsAreCorrect(String username, String password) {
        for(Users users : usersArrayList) {
            if(users.getUsername().toLowerCase().equals(username.toLowerCase()) && users.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    private Boolean checkUsernameExists(String username) {
        for(Users users : usersArrayList) {
            if(users.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

}
