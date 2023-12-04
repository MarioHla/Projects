package DevOps404.Inventura.security.entity;

public class JwtResponse {
	private String token;
	private Long id;
    private String username;
    private String role;

    public JwtResponse(String jwt, Long id, String username, String role) {
    
    	this.token = jwt;
        this.id = id;
        this.username = username;
        this.role = role;
    }

	public String getToken() {
		return token;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

	public Long getId() {
		return id;
	}

	
	
    
    
}
