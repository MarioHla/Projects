package DevOps404.Inventura.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import DevOps404.Inventura.entity.ErrorInInventory;

public class ErrorInInventorySetExtractor implements ResultSetExtractor<ErrorInInventory>{

	@Override
	public ErrorInInventory extractData(ResultSet rs) throws SQLException, DataAccessException {

		ErrorInInventory errorInInventory = new ErrorInInventory();

		errorInInventory.setNotificationId(rs.getLong("id_pogreska"));
		errorInInventory.setBadAmount(rs.getLong("kolicina"));
		errorInInventory.setRealAmount(rs.getLong("stvarna_kolicina"));
		errorInInventory.setTime(rs.getDate("datum_vrijeme"));
		errorInInventory.setWerehouseId(rs.getLong("id_skladiste"));
		errorInInventory.setArticleId(rs.getLong("id_artikl"));
		errorInInventory.setWorkerId(rs.getLong("id_skladistar"));
		errorInInventory.setStatus(rs.getInt("status"));
		errorInInventory.setArticleName(rs.getString("naziv_artikl"));
		errorInInventory.setUsername(rs.getString("naziv_korisnik"));

		return errorInInventory;
	}

}
