package com.userdata.app;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.userdata.app" })
public class Application {

	public static void main(String[] args)
			throws URISyntaxException, JSONException, JsonParseException, JsonMappingException, IOException {
		SpringApplication.run(Application.class, args);
	}
}
