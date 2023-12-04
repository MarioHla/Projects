package DevOps404.Inventura.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import DevOps404.Inventura.entity.User;
import DevOps404.Inventura.extractor.UserSetExtractor;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserSetExtractor extractor = new UserSetExtractor();
		return extractor.extractData(rs);
	}

}
