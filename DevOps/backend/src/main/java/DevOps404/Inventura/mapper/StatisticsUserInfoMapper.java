package DevOps404.Inventura.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import DevOps404.Inventura.entity.StatisticsUserInfo;
import DevOps404.Inventura.extractor.StatisticsUserInfoExtractor;

public class StatisticsUserInfoMapper implements RowMapper<StatisticsUserInfo> {

	@Override
	public StatisticsUserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		StatisticsUserInfoExtractor extractor = new StatisticsUserInfoExtractor();
		return extractor.extractData(rs);
	}

}
