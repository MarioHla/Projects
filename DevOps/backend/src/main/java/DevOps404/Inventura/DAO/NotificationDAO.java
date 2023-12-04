package DevOps404.Inventura.DAO;

import java.util.List;

import org.springframework.stereotype.Service;

import DevOps404.Inventura.DTOI.NotificationDTOI;
import DevOps404.Inventura.DTOI.WarehouseDTOI;
import DevOps404.Inventura.entity.ErrorInInventory;
import DevOps404.Inventura.entity.LowQuantityNotification;

@Service
public class NotificationDAO {

	private NotificationDTOI notificationDTOI;
	private WarehouseDTOI warehouseDTOI;

	public NotificationDAO(NotificationDTOI notificationDTOI, WarehouseDTOI warehouseDTOI) {
		this.notificationDTOI = notificationDTOI;
		this.warehouseDTOI = warehouseDTOI;
	}

	public List<LowQuantityNotification> getAllLowQuantityNotificationsForBoss(Long bossId) {
		return notificationDTOI.getAllLowQuantityNotificationsForBoss(bossId);
	}

	public List<LowQuantityNotification> getAllActiveLowQuantityNotificationsForDirector() {
		return notificationDTOI.getAllActiveLowQuantityNotificationsForDirector();
	}

	public List<LowQuantityNotification> getAllHistoryLowQuantityNotificationsForDirector() {
		return notificationDTOI.getAllHistoryLowQuantityNotificationsForDirector();
	}

	public void addNewLowQuantityNotification(Long articleId, Long userId) {
		long warehouseId = warehouseDTOI.getWerehouseByUserId(userId);
		notificationDTOI.addNewLowQuantityNotification(articleId, warehouseId);
	}

	public void passLowQuantityNotificationToDirector(Long notificationId) {
		notificationDTOI.passLowQuantityNotificationToDirector(notificationId);
	}

	public void markLowQuantityNotificationAsCompleted(Long notificationId) {
		notificationDTOI.markLowQuantityNotificationAsCompleted(notificationId);
	}

	public void markLowQuantityNotificationAsRejected(Long notificationId) {
		notificationDTOI.markLowQuantityNotificationAsRejected(notificationId);
	}

	public List<ErrorInInventory> getAllErrorNotifications() {
		return notificationDTOI.getAllUnresolvedErrors();
	}


	public void markLowQuantityNotificationAsRejectedByDirector(Long notificationId){
		 notificationDTOI.markLowQuantityNotificationAsRejectedByDirector(notificationId);
	}

	public void markErrorNotificationAsSeen(Long errorId){
		notificationDTOI.markErrorNotificationAsSeen(errorId);
	}

	public void markErrorNotificationAsRejected(Long errorId ) {
		notificationDTOI.markErrorNotificationAsRejected(errorId);
	}

	public List<ErrorInInventory> getAllHistoryErrorNotificationsForDirector(){
		return notificationDTOI.getAllHistoryErrorNotificationsForDirector();
	}



}
