package DevOps404.Inventura.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DevOps404.Inventura.DAO.ChangeDAO;
import DevOps404.Inventura.entity.Change;
import DevOps404.Inventura.requests.AddChangeRequest;

@RestController
@RequestMapping("/changes")
@CrossOrigin
@PreAuthorize("hasRole('WORKER')")
public class ChangeController {
	
	private ChangeDAO service;
	
	public ChangeController(ChangeDAO changeService) {
		this.service = changeService;
	}

	@GetMapping("getChanges")
	public ResponseEntity<List<Change>> getChanges(@RequestParam(value="userId") Long userId) {
		return new ResponseEntity<List<Change>>(service.getChangesForWorker(userId),HttpStatus.OK);
	}
	
	@PostMapping("addChange")
	public ResponseEntity<String> addChange(@RequestBody AddChangeRequest request) {
		service.addChange(request.getxLocation(),request.getyLocation(), request.getAmount(), request.getWarehouseId(), request.getArticleId(), request.getUserId());
		return new ResponseEntity<String>("New chage was successfully added.", HttpStatus.OK);
	}

}
