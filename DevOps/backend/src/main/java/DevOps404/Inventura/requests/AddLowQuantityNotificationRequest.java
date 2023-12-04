package DevOps404.Inventura.requests;

public class AddLowQuantityNotificationRequest {

	private Long userId;
	private Long articleId;
	
	public Long getUserId() {
		return userId;
	}

	public void setUsereId(Long userId) {
		this.userId = userId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
	
	
}
