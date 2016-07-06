package com.kainos.training.login.service.resource;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.kainos.training.login.service.configuration.LoginConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginResourceTest {
	//Tests go here....

    LoginResource loginResource;
    LoginConfiguration loginConfiguration;
    @Before
    public void setup() {
        loginConfiguration = new LoginConfiguration();
        loginResource = new LoginResource(loginConfiguration.getUserName(), loginConfiguration.getPassword());
    }

    @Test
    public void loginSuccessTest() {
        Assert.assertTrue(loginResource.login("admin", "password").getEntity().equals("Login Success"));
    }


    @Test
    public void badUsernameAndPasswordTest() {
        Assert.assertTrue(loginResource.login("admin1", "password1").getStatus() == 401);
    }

    @Test
    public void badUsernameTest() {
        Assert.assertTrue(loginResource.login(null, "password").getEntity().equals("Username cannot be empty."));
    }

    @Test
    public void caseSensitiveTest() {
        Assert.assertTrue(loginResource.login("Admin", "Password").getStatus() == 401);
    }

    @Test
    public void badPasswordTest() {
        Assert.assertTrue(loginResource.login("admin", null).getStatus() == 401);
    }

    @Test
    public void loginUsingNullsTest() {
        Assert.assertTrue(loginResource.login(null, null).getStatus() == 401);
    }

    @Test
    public void registrationSuccessfulTest() {
        Assert.assertTrue(loginResource.register("Thomas", "password").getEntity().equals("Registration Successful"));
    }

    @Test
    public void duplicateUserRegistrationTest() {
        Assert.assertTrue(loginResource.register("admin", "password").getEntity().equals("User already exists, " +
                "unable to create."));
    }

    @Test
    public void nullUsernameDuringRegistrationTest() {
        Assert.assertTrue(loginResource.register(null, "password").getEntity().equals("Username cannot be null/empty " +
                "during registration"));
    }

    @Test
    public void nullPasswordDuringRegistrationTest() {
        Assert.assertTrue(loginResource.register("Thomas", null).getEntity().equals("Password cannot be null/empty " +
                "during registration"));
    }

    @Test
    public void successfullyChangedPasswordTest() {
        Assert.assertTrue(loginResource.changePassword("admin", "password", "password1").getStatus() == 204);
    }

}
