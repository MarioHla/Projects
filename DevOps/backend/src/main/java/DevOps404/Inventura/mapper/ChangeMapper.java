package DevOps404.Inventura.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import DevOps404.Inventura.entity.Change;
import DevOps404.Inventura.extractor.ChangeExtractor;

public class ChangeMapper implements RowMapper<Change>{

	@Override
	public Change mapRow(ResultSet rs, int rowNum) throws SQLException {
		ChangeExtractor extractor = new ChangeExtractor();
		return extractor.extractData(rs);
	}

}
