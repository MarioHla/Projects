package DevOps404.Inventura.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import DevOps404.Inventura.entity.NumberOfArticleInWarehouse;
import DevOps404.Inventura.extractor.WarehouseCountSetExtractor;

public class WerehouseCountRowMapper implements RowMapper<NumberOfArticleInWarehouse>{

	@Override
	public NumberOfArticleInWarehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
		WarehouseCountSetExtractor extractor = new WarehouseCountSetExtractor();
		return extractor.extractData(rs);
	}

}
