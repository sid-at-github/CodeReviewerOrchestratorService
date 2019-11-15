package team.dexter.CodeReviewerOrchestrationService.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import team.dexter.CodeReviewerOrchestrationService.dtos.RevieweeRequestDto;

@RestController
public class RevieweeResource {

	@PostMapping("/quotations")
	public String createReviewee(@RequestBody RevieweeRequestDto info) {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("http://localhost:9001/reviewee", String.class);
		return result;
	}
}
