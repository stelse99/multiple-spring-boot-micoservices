package demo.microservice2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Microservice2Controller {

	private final static Logger LOGGER = LoggerFactory.getLogger(Microservice2Controller.class);

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "/")
	String displayDefaultMessage() {
		String message = "Hello World, from Microservice2!";
		LOGGER.info("{}", message);
		return message;
	}

	@RequestMapping("/callmicroservice2")
	String displayDefaultMessageWithParameter(@RequestParam String input) {
		// Hitting the microservice2 from microservice1 to fetch the response.
		// For simplicity we are directly configuring the url earlier.
		// For production ready applications it should be populated from the AWS param
		// store or the properties file.
		final String microservice2Url = "http://microservice2:10092/microservice2";
		final String response = (String) restTemplate.exchange(microservice2Url, HttpMethod.GET, null, String.class)
				.getBody();

		LOGGER.info("The response received from microservice2= " + response);
		// Returning the response to the user.
		return response;
	}
}
