package DevOps404.Inventura.requests;

public class AddChangeRequest {

	private Long amount;
	private Long warehouseId;
	private Long articleId;
	private Long userId;
	private Double xLocation;
	private Double yLocation;
	
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
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

	public Double getxLocation() {
		return xLocation;
	}

	public void setxLocation(Double xLocation) {
		this.xLocation = xLocation;
	}

	public Double getyLocation() {
		return yLocation;
	}

	public void setyLocation(Double yLocation) {
		this.yLocation = yLocation;
	}
	
	
	
}
