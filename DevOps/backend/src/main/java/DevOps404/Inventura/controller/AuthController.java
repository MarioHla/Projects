package DevOps404.Inventura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import DevOps404.Inventura.DAO.AuthDAO;
import DevOps404.Inventura.security.entity.JwtResponse;
import DevOps404.Inventura.security.entity.LoginRequest;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	AuthDAO authService;

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
    }
	
}
