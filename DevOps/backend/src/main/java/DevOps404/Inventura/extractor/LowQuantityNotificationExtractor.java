package DevOps404.Inventura.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import DevOps404.Inventura.entity.LowQuantityNotification;

public class LowQuantityNotificationExtractor implements ResultSetExtractor<LowQuantityNotification>{

	@Override
	public LowQuantityNotification extractData(ResultSet rs) throws SQLException, DataAccessException {
		LowQuantityNotification notification = new LowQuantityNotification();

		notification.setNotificationId(rs.getLong("id_dojava"));
		notification.setArticleId(rs.getLong("id_artikl"));
		notification.setStatus(rs.getInt("status"));
		notification.setTime(rs.getDate("vrijeme"));
		notification.setWarehouseId(rs.getLong("id_skladiste"));
		notification.setArticleName(rs.getString("naziv_artikl"));

		return notification;
	}

}
