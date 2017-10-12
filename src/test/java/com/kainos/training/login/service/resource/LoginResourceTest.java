package com.kainos.training.login.service.resource;

import javax.ws.rs.core.Response;

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

		// act
		Response response = loginResource.login("", "");

		// assert
		Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		Assert.assertEquals("Success!", response.getEntity());
	}

}
