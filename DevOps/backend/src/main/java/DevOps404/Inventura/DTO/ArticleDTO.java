package DevOps404.Inventura.DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import DevOps404.Inventura.DTOI.ArticleDTOI;
import DevOps404.Inventura.entity.Article;
import DevOps404.Inventura.entity.NumberOfArticleInWarehouse;
import DevOps404.Inventura.entity.WarehouseArticle;
import DevOps404.Inventura.mapper.ArticleMapper;
import DevOps404.Inventura.mapper.WerehouseArticleRowMapper;
import DevOps404.Inventura.mapper.WerehouseCountRowMapper;

@Repository
public class ArticleDTO implements ArticleDTOI{

	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Article> getAllArticles() {
		return jdbcTemplate.query(
				"select "
				+ "a.id_artikl, "
				+ "a.naziv_artikl , "
				+ "a.opis_artikl , "
				+ "a.id_skupina , "
				+ "s.naziv_skupina "
				+ "from artikl a join skupina s on a.id_skupina = s.id_skupina", new ArticleMapper());
	}

	@Override
	public Article getArticleById(Long id) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", id);
				
		return jdbcTemplate.queryForObject(
				"select "
				+ "a.id_artikl, "
				+ "a.naziv_artikl, "
				+ "a.opis_artikl, "
				+ "a.id_skupina, "
				+ "s.naziv_skupina "
				+ "from artikl a join skupina s on a.id_skupina = s.id_skupina  where id_artikl = :id",parameters,new ArticleMapper());
	}

	@Override
	public void addArticle(String articleName, String articleDescription, Integer groupId) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("articleName", articleName);
		parameters.addValue("articleDescription", articleDescription);
		parameters.addValue("groupId", groupId);
		
		jdbcTemplate.update("insert into artikl (naziv_artikl, opis_artikl, id_skupina) "
				+ "values (:articleName, :articleDescription, :groupId)", parameters);
	}

	@Override
	public void deleteArticleById(Long id) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);;
		
		jdbcTemplate.update("delete from skladiste_artikl where id_artikl = :id", parameters);
		jdbcTemplate.update("delete from artikl where id_artikl = :id", parameters);
		
	}

	@Override
	public WarehouseArticle getWarehouseArticle(Long userId, Long articleId) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		parameters.addValue("articleId", articleId);
		try {
			return jdbcTemplate.queryForObject(
					"select "
					+ "a.id_artikl, "
					+ "a.naziv_artikl , "
					+ "a.opis_artikl ,"
					+ "s.naziv_skupina,"
					+ "sa.kolicina "
					+ "from artikl a "
					+ "left join skladiste_artikl sa on a.id_artikl = sa.id_artikl "
					+ "left join korisnik k on sa.id_skladiste = k.id_skladiste "
					+ "left join skupina s on a.id_skupina = s.id_skupina "
					+ "where k.id_korisnik = :userId "
					+ "and a.id_artikl = :articleId",parameters, new WerehouseArticleRowMapper());
		}catch(EmptyResultDataAccessException ex) {
			//tu ce ic log za error
			System.out.println("No article in given werehouse");
		}
		
		return null;
		
	}

	@Override
	public void setWarehouseArticle(Long userId, Long articleId, Long newAmount) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		parameters.addValue("articleId", articleId);
		parameters.addValue("newAmount", newAmount);
		
		jdbcTemplate.update(
				"update skladiste_artikl s set kolicina = :newAmount "
				+ "where s.id_skladiste = "
				+ "("
				+ "	select k.id_skladiste "
				+ "	from korisnik k "
				+ "	where k.id_korisnik = :userId"
				+ ") "
				+ "and s.id_artikl = :articleId", parameters);
		
	}

	@Override
	public List<WarehouseArticle> getAllArticlesInWerehouseByUserId(Long userId) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		
		return jdbcTemplate.query(
				"select "
				+ "a.id_artikl, "
				+ "a.naziv_artikl , "
				+ "a.opis_artikl ,"
				+ "s.naziv_skupina,"
				+ "sa.kolicina "
				+ "from artikl a "
				+ "left join skladiste_artikl sa on a.id_artikl = sa.id_artikl "
				+ "left join korisnik k on sa.id_skladiste = k.id_skladiste "
				+ "left join skupina s on a.id_skupina = s.id_skupina "
				+ "where k.id_korisnik = :userId",parameters, new WerehouseArticleRowMapper());
	}

	@Override
	public void updateArticle(Long articleId, String articleName, String articleDescription, Integer groupId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("articleId", articleId);
		parameters.addValue("articleName", articleName);
		parameters.addValue("articleDescription", articleDescription);
		parameters.addValue("groupId", groupId);
		
		jdbcTemplate.update("update artikl set naziv_artikl = :articleName, "
				+ "opis_artikl = :articleDescription, "
				+ "id_skupina = :groupId "
				+ "where id_artikl = :articleId", parameters);
		
	}

	@Override
	public List<NumberOfArticleInWarehouse> getAllWarehouseArticlesCounts(Long aricleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("aricleId", aricleId);
		
		return jdbcTemplate.query(
				"select s.id_skladiste, s.naziv_skladiste, sa.kolicina "
				+ "from skladiste s "
				+ "join skladiste_artikl sa on s.id_skladiste = sa.id_skladiste "
				+ "join artikl a on sa.id_artikl = a.id_artikl "
				+ "where a.id_artikl = :aricleId",parameters, new WerehouseCountRowMapper());
	}

}
