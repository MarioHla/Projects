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

import DevOps404.Inventura.DAO.NotificationDAO;
import DevOps404.Inventura.entity.ErrorInInventory;
import DevOps404.Inventura.entity.LowQuantityNotification;
import DevOps404.Inventura.requests.AddLowQuantityNotificationRequest;
import DevOps404.Inventura.requests.LongRequest;

@RestController
@RequestMapping("/notifications")
@CrossOrigin
public class NotificationController {

	private NotificationDAO service;

	public NotificationController(NotificationDAO service) {
		this.service = service;
	}
	
	@PostMapping("addNotification")
	@PreAuthorize("hasRole('WORKER')")
	public ResponseEntity<String> addNewLowQuantityNotification(@RequestBody AddLowQuantityNotificationRequest request) {
		service.addNewLowQuantityNotification(request.getArticleId(), request.getUserId());
		return new ResponseEntity<String>("Added a new low quantity notification.",HttpStatus.OK);
	}
	
	//===========================================================================================================================================

	@GetMapping("bossNotifications")
	@PreAuthorize("hasRole('BOSS')")
	public ResponseEntity<List<LowQuantityNotification>> getBossNotifications(@RequestParam(value = "bossId") Long bossId) {
		return new ResponseEntity<List<LowQuantityNotification>>(service.getAllLowQuantityNotificationsForBoss(bossId) ,HttpStatus.OK);
	}

	@PostMapping("passNotification")
	@PreAuthorize("hasRole('BOSS')")
	public ResponseEntity<String> passLowQuantityNotificationToDirector(@RequestBody LongRequest notificationId) {
		service.passLowQuantityNotificationToDirector(notificationId.getValue());
		return new ResponseEntity<String>("Notification with id " + notificationId.getValue() + " was successfully passed to the director.", HttpStatus.OK);
	}

	@PostMapping("rejectNotification")
	@PreAuthorize("hasRole('BOSS') or hasRole('DIRECTOR')")
	public ResponseEntity<String> rejectLowQuantityNotification(@RequestBody LongRequest notificationId) {
		service.markLowQuantityNotificationAsRejected(notificationId.getValue());
		return new ResponseEntity<String>("Notification with id " + notificationId.getValue() + " was successfully rejected.", HttpStatus.OK);
	}
	
	//===========================================================================================================================================

	@GetMapping("directorErrorNotifications")
	@PreAuthorize("hasRole('DIRECTOR')")
	public ResponseEntity<List<ErrorInInventory>> getDirectorErrorNotifications() {
		return new ResponseEntity<List<ErrorInInventory>>(service.getAllErrorNotifications() ,HttpStatus.OK);
	}

	@GetMapping("directorErrorNotificationsHistory")
	@PreAuthorize("hasRole('DIRECTOR')")
	public ResponseEntity<List<ErrorInInventory>> getDirectorErrorNotificationsHistory()  {
		return new ResponseEntity<List<ErrorInInventory>>(service.getAllHistoryErrorNotificationsForDirector() ,HttpStatus.OK);
	}


	@PostMapping("directorErrorNotificationAccept")
	@PreAuthorize("hasRole('DIRECTOR')")
	public ResponseEntity<String> directorAcceptErrorNotification(@RequestBody LongRequest errorId) {
		service.markErrorNotificationAsSeen(errorId.getValue());
		return new ResponseEntity<String>("Notification with id " + errorId.getValue() + " was successfully marked as accepted.", HttpStatus.OK);
	}

	@PostMapping("directorErrorNotificationReject")
	@PreAuthorize("hasRole('DIRECTOR')")
	public ResponseEntity<String> directorRejectErrorNotification(@RequestBody LongRequest errorId) {
		service.markErrorNotificationAsRejected(errorId.getValue());
		return new ResponseEntity<String>("Notification with id " + errorId.getValue() + " was successfully marked as rejected.", HttpStatus.OK);
	}
	
	//===========================================================================================================================================

	@GetMapping("directorLowQuantityNotifications")
	@PreAuthorize("hasRole('DIRECTOR')")
	public ResponseEntity<List<LowQuantityNotification>> getDirectorLowQuantityNotifications() {
		return new ResponseEntity<List<LowQuantityNotification>>(service.getAllActiveLowQuantityNotificationsForDirector() ,HttpStatus.OK);
	}

	@GetMapping("directorLowQuantityNotificationsHistory")
	@PreAuthorize("hasRole('DIRECTOR')")
	public ResponseEntity<List<LowQuantityNotification>> getDirectorLowQuantityNotificationsHistory() {
		return new ResponseEntity<List<LowQuantityNotification>>(service.getAllHistoryLowQuantityNotificationsForDirector() ,HttpStatus.OK);
	}
	
	@PostMapping("directorAcceptNotification")
	@PreAuthorize("hasRole('DIRECTOR')")
	public ResponseEntity<String> directorAcceptLowQuantityNotification(@RequestBody LongRequest notificationId) {
		service.markLowQuantityNotificationAsCompleted(notificationId.getValue());
		return new ResponseEntity<String>("Notification with id " + notificationId.getValue() + " was successfully accepted.", HttpStatus.OK);
	}

	@PostMapping("directorRejectNotification")
	@PreAuthorize("hasRole('DIRECTOR')")
	public ResponseEntity<String> directorRejectLowQuantityNotification(@RequestBody LongRequest notificationId) {
		service.markLowQuantityNotificationAsRejectedByDirector(notificationId.getValue());
		return new ResponseEntity<String>("Notification with id " + notificationId.getValue() + " was successfully rejected.", HttpStatus.OK);
	}

	

}
