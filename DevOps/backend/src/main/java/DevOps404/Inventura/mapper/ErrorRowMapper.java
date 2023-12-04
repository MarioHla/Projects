package DevOps404.Inventura.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import DevOps404.Inventura.entity.ErrorInInventory;
import DevOps404.Inventura.extractor.ErrorInInventorySetExtractor;

public class ErrorRowMapper implements RowMapper<ErrorInInventory>{

	@Override
	public ErrorInInventory mapRow(ResultSet rs, int rowNum) throws SQLException {
		ErrorInInventorySetExtractor extractor = new ErrorInInventorySetExtractor();
		return extractor.extractData(rs);
	}

}
