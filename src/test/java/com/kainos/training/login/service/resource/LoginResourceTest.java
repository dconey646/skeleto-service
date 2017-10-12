package com.kainos.training.login.service.resource;

import javax.ws.rs.core.Response;

import com.kainos.training.login.service.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginResourceTest {

	@Test
	public void loginWithCorrectDetailsReturnsOkResponse() {
		// arrange
		LoginResource loginResource = new LoginResource("admin", "password");

		User user = new User();

		// act
		Response response = loginResource.login("admin", "password");

		// assert
		Assert.assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

}
