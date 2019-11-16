package team.dexter.CodeReviewerOrchestrationService.resources;

import java.util.List;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeDto;
import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeRequestDto;

@RestController
public class RevieweeCodeResource {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${revieweeCode.service.base.url}")
	private String revieweeCodeServiceBaseUrl;

	/*
	 * upload code page lands here
	 */
	@PostMapping("/revieweeCode")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createRevieweeCode(@RequestBody RevieweeCodeDto revieweeCodeDto) {
		String url = revieweeCodeServiceBaseUrl + "/revieweeCode";
		boolean result = restTemplate.postForObject(url, revieweeCodeDto, Boolean.class);
		if (!result) {
			throw new HTTPException(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	/*
	 * code list page lands here
	 */
	@GetMapping("/revieweeCodeList")
	public List<RevieweeCodeDto> getRevieweeCodeList(@RequestBody RevieweeCodeRequestDto info) {
		boolean result = restTemplate.getForObject(revieweeCodeServiceBaseUrl, Boolean.class);
		if (!result) {
			throw new HTTPException(HttpStatus.NOT_FOUND.value());
		}
		return null;
	}

	/*
	 * code detail page lands here
	 */
	@GetMapping("/revieweeCodeList/{codeId}")
	public List<RevieweeCodeDto> getRevieweeCode(@RequestParam String codeId) {
		boolean result = restTemplate.getForObject(revieweeCodeServiceBaseUrl, Boolean.class);
		if (!result) {
			throw new HTTPException(HttpStatus.NOT_FOUND.value());
		}
		return null;
	}
}
