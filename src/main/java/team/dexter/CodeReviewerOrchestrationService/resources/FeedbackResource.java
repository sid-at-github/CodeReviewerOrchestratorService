package team.dexter.CodeReviewerOrchestrationService.resources;

import java.util.List;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeDto;
import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeRequestDto;

public class FeedbackResource {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${reviewee.service.url}")
	private String revieweeServiceUrl;

	/*
	 * feedback list API
	 */
	@GetMapping("/feedbackList")
	public List<RevieweeCodeDto> getFeedbackList(@RequestBody RevieweeCodeRequestDto info) {
		boolean result = restTemplate.getForObject(revieweeServiceUrl, Boolean.class);
		if (!result) {
			throw new HTTPException(HttpStatus.NOT_FOUND.value());
		}
		return null;
	}
}
