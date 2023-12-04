package DevOps404.Inventura.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import DevOps404.Inventura.entity.ArticleGroup;

public class ArticleGroupExtractor implements ResultSetExtractor<ArticleGroup> {

	@Override
	public ArticleGroup extractData(ResultSet rs) throws SQLException, DataAccessException {
		ArticleGroup group = new ArticleGroup();
		
		group.setGroupId(rs.getLong("id_skupina"));
		group.setGroupName(rs.getString("naziv_skupina"));
		group.setParentGroupId(rs.getLong("id_nadskupina"));
		group.setChildGroupCount(rs.getInt("broj_djece"));
		return group;
	}

}
