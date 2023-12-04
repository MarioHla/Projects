package DevOps404.Inventura.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DevOps404.Inventura.DAO.StatistcsDAO;
import DevOps404.Inventura.entity.StatisticsError;
import DevOps404.Inventura.entity.StatisticsUserInfo;

@RestController
@RequestMapping("/statistics")
@CrossOrigin
@PreAuthorize("hasRole('DIRECTOR')")
public class StatisticsController {
	
	private StatistcsDAO service;

	public StatisticsController(StatistcsDAO service) {
		this.service = service;
	}
	
	@GetMapping("all")
	public ResponseEntity<StatisticsError> getAllStatistics() {
		return new ResponseEntity<>(service.getGlobalStatistics(), HttpStatus.OK);
	}
	
	@GetMapping("workerStatistics")
	public ResponseEntity<StatisticsError> getWorkerStatistics(@RequestParam(value = "id") Long id) {
		return new ResponseEntity<>(service.getWorkerStatistics(id), HttpStatus.OK);
	}
	
	@GetMapping("workers")
	public ResponseEntity<List<StatisticsUserInfo>> getAllWorkers() {
		return new ResponseEntity<>(service.getAllWorkers(), HttpStatus.OK);
	}
	
	

}
