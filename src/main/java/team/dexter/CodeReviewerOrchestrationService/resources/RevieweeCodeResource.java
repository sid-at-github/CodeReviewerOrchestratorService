package team.dexter.CodeReviewerOrchestrationService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import team.dexter.CodeReviewerCommons.dtos.FeedbackDto;
import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeDto;
import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeRequestDto;
import team.dexter.CodeReviewerOrchestrationService.exceptions.InternalServerError;

@RestController
public class RevieweeCodeResource {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${revieweeCode.service.base.url}")
	private String revieweeCodeServiceBaseUrl;

	@PostMapping("/revieweeCode")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createRevieweeCode(@RequestBody RevieweeCodeDto revieweeCodeDto) {
		String url = revieweeCodeServiceBaseUrl + "/revieweeCode";
		try {
			restTemplate.postForObject(url, revieweeCodeDto, Void.class);
		} catch (Exception e) {
			throw new InternalServerError();
		}
	}

	@GetMapping("/revieweeCode")
	public RevieweeCodeDto[] getRevieweeCodeList(RevieweeCodeRequestDto revieweeCodeRequestDto) {
		try {
			String tag=revieweeCodeRequestDto.getTag();
			String url = revieweeCodeServiceBaseUrl + "/revieweeCode"+(tag==null || "".equals(tag)?"":"?tag=" + (revieweeCodeRequestDto.getTag()));
			ResponseEntity<RevieweeCodeDto[]> response = restTemplate.getForEntity(url, RevieweeCodeDto[].class);
			return response.getBody();
		} catch (Exception e) {
			throw new InternalServerError();
		}
	}
	
	/*
	 * code review page lands here
	 */
	@GetMapping("/revieweeCode/{codeId}")
	public RevieweeCodeDto getRevieweeCode(@PathVariable String codeId) {
		try {
			String url = revieweeCodeServiceBaseUrl + "/revieweeCode/"+codeId;
			ResponseEntity<RevieweeCodeDto> response = restTemplate.getForEntity(url, RevieweeCodeDto.class);
			return response.getBody();
		} catch (Exception e) {
			throw new InternalServerError();
		}
	}

	@PutMapping("/revieweeCode/{codeId}")
	public void giveFeedback(@PathVariable String codeId, @RequestBody FeedbackDto feedbackDto) {
		try {
			String url = revieweeCodeServiceBaseUrl + "/revieweeCode/" + codeId;
			restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(feedbackDto), Void.class);
		} catch (Exception e) {
			throw new InternalServerError();
		}
	}
}
