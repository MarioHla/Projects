package DevOps404.Inventura.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/worker")
	@PreAuthorize("hasRole('WORKER') or hasRole('BOSS') or hasRole('DIRECTOR')")
	public String userAccess() {
		return "Worker Content.";
	}

	@GetMapping("/boss")
	@PreAuthorize("hasRole('BOSS')")
	public String moderatorAccess() {
		return "Boss Board.";
	}

	@GetMapping("/director")
	@PreAuthorize("hasRole('DIRECTOR')")
	public String adminAccess() {
		return "Director Board.";
	}
}