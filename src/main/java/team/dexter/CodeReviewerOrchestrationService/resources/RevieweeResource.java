package team.dexter.CodeReviewerOrchestrationService.resources;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import team.dexter.CodeReviewerCommons.dtos.RevieweeDto;
import team.dexter.CodeReviewerOrchestrationService.exceptions.ResourceNotFoundException;

@RestController
public class RevieweeResource {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${reviewee.service.base.url}")
	private String revieweeServiceBaseUrl;

	/*
	 * sign up page lands here
	 */
	@PostMapping("/reviewee")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createReviewee(@RequestBody RevieweeDto revieweeDto) {
		String url = revieweeServiceBaseUrl = "/reviewee";
		boolean result = restTemplate.postForObject(url, revieweeDto, Boolean.class);
		if (!result) {
			throw new HTTPException(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	/*
	 * login page lands here
	 */
	@GetMapping("/reviewee")
	public RevieweeDto[] getReviewee(HttpServletRequest request) {
		String url = revieweeServiceBaseUrl + "/reviewee?" + request.getQueryString();
		try {
			ResponseEntity<RevieweeDto[]> response = restTemplate.getForEntity(url, RevieweeDto[].class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new ResourceNotFoundException();
			throw new HTTPException(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
}
