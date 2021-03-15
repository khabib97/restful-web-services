package com.old.school.rest.webservices.restfulwebservices.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	//Version : No rock and solid rule
	
	//below two good for caching but pollute uri
	
	//1. URI versioning = > twitter
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("kawser habib");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("kawser", "habib"));
	}
	
	//2. Request parameter versioning => amazon
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 getPersonV1UsingParam() {
		return new PersonV1("kawser habib");
	}
	
	@GetMapping(value="/person/param", params ="version=2")
	public PersonV2 getPersonV2UsingParam() {
		return new PersonV2(new Name("kawser", "habib"));
	}
	
	//Below two versioning = unable caching, documentation is complex
	
	//3. (Custom) Header versioning => microsoft
	@GetMapping(value="/person/header", headers ="X-API-VERSION=1")
	public PersonV1 getPersonV1UsingHeaderParam() {
		return new PersonV1("kawser habib");
	}
	
	@GetMapping(value="/person/header", headers ="X-API-VERSION=2 ")
	public PersonV2 getPersonV2UsingHeaderParam() {
		return new PersonV2(new Name("kawser", "habib"));
	}
	
	//4. Accept header/MIME type/Media type versioning => github
	@GetMapping(value="/person/produces", produces ="application/vnd.app-v1+json")
	public PersonV1 getPersonV1UsingProducesHeaderParam() {
		return new PersonV1("kawser habib");
	}
	
	@GetMapping(value="/person/produces", produces ="application/vnd.app-v2+json")
	public PersonV2 getPersonV2UsingProducesHeaderParam() {
		return new PersonV2(new Name("kawser", "habib"));
	}

}
