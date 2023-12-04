package DevOps404.Inventura.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import DevOps404.Inventura.entity.WarehouseArticle;

public class WerehouseArticleSetExtractor implements ResultSetExtractor<WarehouseArticle> {

	@Override
	public WarehouseArticle extractData(ResultSet rs) throws SQLException, DataAccessException {
		WarehouseArticle werehouseArticle = new WarehouseArticle();
		
		werehouseArticle.setArticleId(rs.getLong("id_artikl"));
		werehouseArticle.setArticleName(rs.getString("naziv_artikl"));
		werehouseArticle.setDescription(rs.getString("opis_artikl"));
		werehouseArticle.setGroupName(rs.getString("naziv_skupina"));
		werehouseArticle.setAmount(rs.getLong("kolicina"));
		
		return werehouseArticle;
	}

}
