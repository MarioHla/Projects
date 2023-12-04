package DevOps404.Inventura.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import DevOps404.Inventura.entity.User;

public class UserSetExtractor implements ResultSetExtractor<User>{

	@Override
	public User extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		User user = new User();
		
		user.setUserId(rs.getLong("id_korisnik"));
		user.setUsername(rs.getString("naziv_korisnik"));
		user.setPassword(rs.getString("lozinka"));
		user.setRoleId(rs.getInt("id_uloga"));
		user.setWerehouseId(rs.getInt("id_skladiste"));
		
		return user;
	}

}
