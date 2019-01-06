package com.userdata.app.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userdata.app.entities.User;

@Service
public class UserService {

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private Environment env;

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * This method is used to save user data
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	public String saveUser(User user) throws ParseException {
		
		String walletBalanceUrl = env.getProperty("user.app.url");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("email", user.getEmail());
		map.add("start", dateFormat(user.getStartTime()));
		map.add("end", dateFormat(user.getEndTime()));

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(walletBalanceUrl, request, String.class);

		return response.getStatusCode().toString();
	}

	/**
	 * This method is used to retrieve the user details
	 * @param user
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<User> getUsers(User user) throws IOException, ParseException {
		
		String walletBalanceUrl = env.getProperty("user.app.url");

		final String uri = walletBalanceUrl+"?email=" + user.getEmail();
		
		List<User> listUser = null;
		User userBO = null;
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

		String listOfString = result.getBody();
		if(null != listOfString && !"[ null ]".equals(listOfString)) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = null;
			rootNode = mapper.readTree(listOfString);
			listUser = new ArrayList<>();
			Iterator<JsonNode> elements = rootNode.elements();
			while (elements.hasNext()) {
				JsonNode node = elements.next();
				userBO = new User();
				userBO.setStartTime(String.valueOf(node.get("start")));
				userBO.setEndTime(String.valueOf(node.get("end")));
				userBO.setEmail(String.valueOf(node.get("email")));
				listUser.add(userBO);
			}
		}
		return listUser;
	}

	/**
	 * This method formats the date according to the docker service input
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	private String dateFormat(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date date = sdf.parse(strDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return new StringBuilder().append(cal.get(Calendar.DATE)).append(".").append(cal.get(Calendar.MONTH) + 1)
				.append(".").append(cal.get(Calendar.YEAR)).append(" ").append(cal.get(Calendar.HOUR)).append(":")
				.append(cal.get(Calendar.MINUTE)).toString();
	}
}
