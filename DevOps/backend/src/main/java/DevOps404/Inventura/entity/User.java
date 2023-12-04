package DevOps404.Inventura.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	
	private static final long serialVersionUID = 1L;
		
	private Long userId;
	private String username;
	private String password;
	private Integer roleId;
	private Integer werehouseId;
	
	public User() {}
	
	public User(Long userId, String username, String password, Integer roleId, Integer werehouseId) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.roleId = roleId;
		this.werehouseId = werehouseId;
	}	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getWerehouseId() {
		return werehouseId;
	}

	public void setWerehouseId(Integer werehouseId) {
		this.werehouseId = werehouseId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", roleId=" + roleId
				+ ", werehouseId=" + werehouseId + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		String role;
		
		if(roleId == 1)
			role="ROLE_WORKER";
		else if(roleId == 2)
			role="ROLE_BOSS";
		else
			role="ROLE_DIRECTOR";
	
		
		ArrayList<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
		result.add(new SimpleGrantedAuthority(role));
		return result;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(userId, user.userId);
	}
	
}
