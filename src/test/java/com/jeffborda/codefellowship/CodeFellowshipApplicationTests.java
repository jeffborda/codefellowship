package com.jeffborda.codefellowship;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeFellowshipApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void homepageLoad() {
		//make a get request to "/"
		//get a response
		//  response not null
		//  response code is 200
		//  hacky: check that <h1>Hello</h1> is in the response
		//    better: traverse the DOM (requires more setup)
		//  all the resources (css, img, scripts) in the page also load
	}

}

