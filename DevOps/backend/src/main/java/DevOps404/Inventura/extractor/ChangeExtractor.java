package DevOps404.Inventura.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import DevOps404.Inventura.entity.Change;

public class ChangeExtractor implements ResultSetExtractor<Change> {

	@Override
	public Change extractData(ResultSet rs) throws SQLException, DataAccessException {
		Change change = new Change();
		
		change.setChangeId(rs.getLong("id_unos"));
		change.setArticleId(rs.getLong("id_artikl"));
		change.setLocation(rs.getString("lokacija"));
		change.setTime(rs.getDate("vrijeme"));
		change.setUserId(rs.getLong("id_korisnik"));
		change.setWarehouseId(rs.getLong("id_skladiste"));
		change.setAmount(rs.getLong("kolicina"));
		change.setUserName(rs.getString("naziv_korisnik"));
		change.setArticleName(rs.getString("naziv_artikl"));
		
		return change;
	}

}

