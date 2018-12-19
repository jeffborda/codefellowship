package com.jeffborda.codefellowship;

import com.jeffborda.codefellowship.controllers.ApplicationUserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CodeFellowshipApplicationTests {

	@LocalServerPort
	private int port;
	@Autowired
	ApplicationUserController controller;


	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void homepageLoad() {
		//***FUTURE THINGS TO TEST:
		//make a get request to "/"
		//get a response
		//  response not null
		//  response code is 200
		//  hacky: check that <h1>Hello</h1> is in the response
		//    better: traverse the DOM (requires more setup)
		//  all the resources (css, img, scripts) in the page also load
	}

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGreetingMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("Welcome to Code Fellowship");
	}

}

