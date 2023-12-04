package DevOps404.Inventura.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import DevOps404.Inventura.entity.StatisticsUserInfo;

public class StatisticsUserInfoExtractor implements ResultSetExtractor<StatisticsUserInfo> {

	@Override
	public StatisticsUserInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		StatisticsUserInfo user = new StatisticsUserInfo();
		user.setUserId(rs.getLong("id_korisnik"));
		user.setUserName(rs.getString("naziv_korisnik"));
		
		return user;
	}

}
