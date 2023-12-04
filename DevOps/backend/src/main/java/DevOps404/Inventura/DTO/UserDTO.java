package DevOps404.Inventura.DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import DevOps404.Inventura.DTOI.UserDTOI;
import DevOps404.Inventura.entity.User;
import DevOps404.Inventura.mapper.UserMapper;

@Repository
public class UserDTO implements UserDTOI{

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    @Override
    public boolean checkUsername(String username) {
    	
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("username", username);
    	
        List<User> userList = jdbcTemplate.query("select * from korisnik where naziv_korisnik = :username",parameters,new UserMapper());
        
        return userList.size() > 0;
    }

	@Override
	public int registerUser(String username, String password, Integer roleId, Integer werehouseId) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("password", password);
		parameters.addValue("roleId", roleId);
		parameters.addValue("werehouseId", werehouseId);
		
		return jdbcTemplate.update("insert into korisnik (naziv_korisnik, lozinka, id_uloga, id_skladiste) values (:username,:password,:roleId,:werehouseId)", parameters);
	}
	
	@Override
    public List<User> checkUser(String username, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("username", username);
    	parameters.addValue("password", password);
		
        List<User> user = jdbcTemplate.query("select * from korisnik where naziv_korisnik = :username and lozinka = :password",parameters,new UserMapper());
                
        return user;
    }

	@Override
	public List<User> getUserByName(String username) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("username", username);
		
        List<User> user = jdbcTemplate.query("select * from korisnik where naziv_korisnik = :username",parameters,new UserMapper());
                
        return user;
	}

	@Override
	public Integer getRoleByUserId(Long userId) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("userId", userId);
    			
		return jdbcTemplate.queryForObject("select u.id_uloga from uloga u left join korisnik k on k.id_uloga = u.id_uloga where k.id_korisnik = :userId", parameters, Integer.class);
	}
	
	
 
}
