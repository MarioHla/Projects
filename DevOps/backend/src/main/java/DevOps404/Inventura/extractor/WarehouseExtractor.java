package DevOps404.Inventura.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import DevOps404.Inventura.entity.Warehouse;

public class WarehouseExtractor implements ResultSetExtractor<Warehouse>{

	@Override
	public Warehouse extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Warehouse warehouse = new Warehouse();
		
		warehouse.setWarehouseId(rs.getLong("id_skladiste"));
		warehouse.setAddress(rs.getString("adresa"));
		warehouse.setBossId(rs.getLong("id_sef_skladiste"));
		warehouse.setName(rs.getString("naziv_skladiste"));
		
		return warehouse;
	}

}
