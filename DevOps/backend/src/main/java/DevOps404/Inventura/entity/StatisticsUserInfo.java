package DevOps404.Inventura.entity;

public class StatisticsUserInfo {

	private Long userId;
	private String userName;
	
	public StatisticsUserInfo() {
		
	}
	
	public StatisticsUserInfo(Long userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
	
}
