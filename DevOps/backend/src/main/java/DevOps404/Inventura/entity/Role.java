package DevOps404.Inventura.entity;

public class Role {

	private Long userId;
	private String rolename;
	
	public Role() {}
	
	public Role(Long userId, String rolename) {
		this.userId = userId;
		this.rolename = rolename;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
	
	
}
