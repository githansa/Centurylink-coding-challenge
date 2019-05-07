package com.centurylink.challenge;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/git")
public class FollowersController {

	@Autowired
	RestTemplate restTemplate;

	@Value("${git.followers.all}")
	private String FOLLOWERS_URL;

	@RequestMapping(method = RequestMethod.GET, path = "/all",produces = "application/json")
	public String getFollowers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange(FOLLOWERS_URL, HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET,produces = "application/json")
	public String getEachuser(@PathVariable("id") String userId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange(FOLLOWERS_URL + "/" + userId , HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping(value = "/user/{id}/followers", method = RequestMethod.GET,produces = "application/json")
	public String getEachusersFollwers(@PathVariable("id") String userId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange(FOLLOWERS_URL + "/" + userId + "/followers" , HttpMethod.GET, entity, String.class).getBody();
	}
}
