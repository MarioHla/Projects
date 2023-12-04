package DevOps404.Inventura.requests;

public class WerehouseArticleRequest {

	private Long userId;
	private Long articleId;
	private Long newAmount;
	private Double xLocation;
	private Double yLocation;
	
	public Long getUserId() {
		return userId;
	}
	public Long getArticleId() {
		return articleId;
	}
	public Long getNewAmount() {
		return newAmount;
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
