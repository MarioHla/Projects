package DevOps404.Inventura.entity;

import java.util.Date;

public class LowQuantityNotification {

	private Long notificationId;
	private Date time;
	private Integer status;
	private Long warehouseId;
	private Long articleId;
	private String articleName;

	public LowQuantityNotification() { }

	public LowQuantityNotification(Long notificationId, Date time, Integer status, Long warehouseId, Long articleId, String articleName) {
		this.notificationId = notificationId;
		this.time = time;
		this.status = status;
		this.warehouseId = warehouseId;
		this.articleId = articleId;
		this.articleName = articleName;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}


	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public void setArticleName(String articleName){
		this.articleName = articleName;
	}

	public String getArticleName(){
		return articleName;
	}

}
