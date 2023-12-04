package DevOps404.Inventura.DTOI;

import java.util.List;

import DevOps404.Inventura.entity.User;

public interface UserDTOI {
	public boolean checkUsername(String username);
    public int registerUser(String username,String password,Integer roleId,Integer werehouseId);
    public List<User> checkUser(String username,String password);
    public List<User> getUserByName(String username);
    public Integer getRoleByUserId(Long userId);
}
