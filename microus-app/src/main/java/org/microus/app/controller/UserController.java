package org.microus.app.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping("/user")
	public Map<String, String> user(Principal user) {
		return Collections.singletonMap("name", user.getName());
	}

}
