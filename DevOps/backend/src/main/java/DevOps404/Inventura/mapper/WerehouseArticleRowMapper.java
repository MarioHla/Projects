package DevOps404.Inventura.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import DevOps404.Inventura.entity.WarehouseArticle;
import DevOps404.Inventura.extractor.WerehouseArticleSetExtractor;

public class WerehouseArticleRowMapper implements RowMapper<WarehouseArticle> {

	@Override
	public WarehouseArticle mapRow(ResultSet rs, int rowNum) throws SQLException {
		WerehouseArticleSetExtractor extractor = new WerehouseArticleSetExtractor();
		return extractor.extractData(rs);
	}

}
