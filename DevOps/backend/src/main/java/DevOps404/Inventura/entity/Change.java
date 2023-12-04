package DevOps404.Inventura.entity;

import java.util.Date;

public class Change {

	private Long changeId;
	private Date time;
	private String location;
	private Long warehouseId;
	private Long articleId;
	private Long userId;
	private Long amount;
	private String userName;
	private String articleName;
	
	public Change() {}

	public Change(Long changeId, Date time, String location, Long warehouseId, Long articleId, Long userId, Long amount, String userName, String articleName) {
		this.changeId = changeId;
		this.time = time;
		this.location = location;
		this.warehouseId = warehouseId;
		this.articleId = articleId;
		this.userId = userId;
		this.amount = amount;
		this.userName = userName;
		this.articleName = articleName;
	}

	public Long getChangeId() {
		return changeId;
	}

	public void setChangeId(Long changeId) {
		this.changeId = changeId;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
