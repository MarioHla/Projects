package DevOps404.Inventura.entity;

import java.util.Date;

public class ErrorInInventory {

	private Long notificationId;
	private Long badAmount;
	private Long realAmount;
	private Date time;
	private Long werehouseId;
	private Long articleId;
	private Long workerId;
	private Integer status;
	private String articleName;
	private String username;

	public ErrorInInventory() {}

	public ErrorInInventory(Long badAmount, Long realAmount, Date time, Long werehouseId, Long articleId, Long workerId,
			Integer status) {
		this.badAmount = badAmount;
		this.realAmount = realAmount;
		this.time = time;
		this.werehouseId = werehouseId;
		this.articleId = articleId;
		this.workerId = workerId;
		this.status = status;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Long getBadAmount() {
		return badAmount;
	}

	public void setBadAmount(Long badAmount) {
		this.badAmount = badAmount;
	}

	public Long getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Long realAmount) {
		this.realAmount = realAmount;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getWerehouseId() {
		return werehouseId;
	}

	public void setWerehouseId(Long werehouseId) {
		this.werehouseId = werehouseId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
