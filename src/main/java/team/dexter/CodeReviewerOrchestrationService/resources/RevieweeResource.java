package team.dexter.CodeReviewerOrchestrationService.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import team.dexter.CodeReviewerCommons.dtos.RevieweeDto;
import team.dexter.CodeReviewerOrchestrationService.exceptions.InternalServerError;
import team.dexter.CodeReviewerOrchestrationService.exceptions.ResourceExistException;
import team.dexter.CodeReviewerOrchestrationService.exceptions.ResourceNotFoundException;

@RestController
public class RevieweeResource {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${reviewee.service.base.url}")
	private String revieweeServiceBaseUrl;

	@PostMapping("/reviewee")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createReviewee(@RequestBody RevieweeDto revieweeDto) {
		String url = revieweeServiceBaseUrl + "/reviewee";
		try {
			restTemplate.postForObject(url, revieweeDto, Void.class);
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
				throw new ResourceExistException();
			} else {
				throw new InternalServerError();
			}
		} catch (Exception e) {
			throw new InternalServerError();
		}
	}

	@GetMapping("/reviewee")
	public RevieweeDto[] getRevieweeList(HttpServletRequest request) {
		String url = revieweeServiceBaseUrl + "/reviewee?" + request.getQueryString();
		try {
			ResponseEntity<RevieweeDto[]> response = restTemplate.getForEntity(url, RevieweeDto[].class);
			return response.getBody();
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new ResourceNotFoundException();
			} else {
				throw new InternalServerError();
			}
		} catch (Exception e) {
			throw new InternalServerError();
		}
	}
}
