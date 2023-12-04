package DevOps404.Inventura.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import DevOps404.Inventura.entity.NumberOfArticleInWarehouse;

public class WarehouseCountSetExtractor implements ResultSetExtractor<NumberOfArticleInWarehouse>{

	@Override
	public NumberOfArticleInWarehouse extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		NumberOfArticleInWarehouse result = new NumberOfArticleInWarehouse();
		
		result.setWarehouseId(rs.getLong("id_skladiste"));
		result.setName(rs.getString("naziv_skladiste"));
		result.setAmount(rs.getLong("kolicina"));
		
		return result;
	}

}
