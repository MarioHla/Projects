package DevOps404.Inventura.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import DevOps404.Inventura.entity.Warehouse;
import DevOps404.Inventura.extractor.WarehouseExtractor;

public class WarehouseMapper implements RowMapper<Warehouse> {

	@Override
	public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
		WarehouseExtractor extractor = new WarehouseExtractor();
		return extractor.extractData(rs);
	}

}
