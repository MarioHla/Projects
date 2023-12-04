package DevOps404.Inventura.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import DevOps404.Inventura.entity.Article;
import DevOps404.Inventura.extractor.ArticleExtractor;

public class ArticleMapper implements RowMapper<Article>{

	@Override
	public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
		ArticleExtractor extractor = new ArticleExtractor();
		return extractor.extractData(rs);
	}

}
