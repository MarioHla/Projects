package DevOps404.Inventura.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import DevOps404.Inventura.entity.Article;

public class ArticleExtractor implements ResultSetExtractor<Article>{

	@Override
	public Article extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Article article = new Article();
		
		article.setArticleId(rs.getLong("id_artikl"));
		article.setName(rs.getString("naziv_artikl"));
		article.setDescription(rs.getString("opis_artikl"));
		article.setGroupId(rs.getLong("id_skupina"));
		article.setGroupName(rs.getString("naziv_skupina"));
		
		return article;
	}

}
