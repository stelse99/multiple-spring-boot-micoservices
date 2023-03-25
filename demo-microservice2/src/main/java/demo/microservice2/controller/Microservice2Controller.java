package demo.microservice2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/testcall")
	String callFromExternalSource() {
		String message = "Hi external service !! Its a response from from Microservice2 !!";
		return message;
	}
}
