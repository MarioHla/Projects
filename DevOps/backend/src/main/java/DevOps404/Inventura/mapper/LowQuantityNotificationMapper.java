package DevOps404.Inventura.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import DevOps404.Inventura.entity.LowQuantityNotification;
import DevOps404.Inventura.extractor.LowQuantityNotificationExtractor;

public class LowQuantityNotificationMapper implements RowMapper<LowQuantityNotification> {

	@Override
	public LowQuantityNotification mapRow(ResultSet rs, int rowNum) throws SQLException {
		LowQuantityNotificationExtractor extractor = new LowQuantityNotificationExtractor();
		return extractor.extractData(rs);
	}

}
