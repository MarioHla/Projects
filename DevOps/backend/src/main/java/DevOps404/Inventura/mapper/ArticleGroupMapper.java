package DevOps404.Inventura.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import DevOps404.Inventura.entity.ArticleGroup;
import DevOps404.Inventura.extractor.ArticleGroupExtractor;

public class ArticleGroupMapper implements RowMapper<ArticleGroup>{

	@Override
	public ArticleGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
		ArticleGroupExtractor extractor = new ArticleGroupExtractor();
		return extractor.extractData(rs);
	}

}
