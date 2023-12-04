package DevOps404.Inventura.DTOI;

import java.util.List;

import DevOps404.Inventura.entity.ErrorInInventory;
import DevOps404.Inventura.entity.LowQuantityNotification;

public interface NotificationDTOI {

	public List<LowQuantityNotification> getAllLowQuantityNotificationsForBoss(Long bossId);
	public List<LowQuantityNotification> getAllActiveLowQuantityNotificationsForDirector();
	public List<LowQuantityNotification> getAllHistoryLowQuantityNotificationsForDirector();
	public void addNewLowQuantityNotification(Long articleId, Long warehouseId);
	public void passLowQuantityNotificationToDirector(Long notificationId);
	public void markLowQuantityNotificationAsCompleted(Long notificationId);
	public void markLowQuantityNotificationAsRejected(Long notificationId);
	public void markLowQuantityNotificationAsRejectedByDirector(Long notificationId);
	public void markErrorNotificationAsSeen(Long errorId);
	public void markErrorNotificationAsRejected(Long errorId);
	public List<ErrorInInventory> getAllUnresolvedErrors();
	public List<ErrorInInventory> getAllHistoryErrorNotificationsForDirector();
}
