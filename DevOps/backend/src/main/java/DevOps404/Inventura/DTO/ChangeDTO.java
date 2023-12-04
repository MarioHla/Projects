package DevOps404.Inventura.DTO;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import DevOps404.Inventura.DTOI.ChangeDTOI;
import DevOps404.Inventura.entity.Change;
import DevOps404.Inventura.mapper.ChangeMapper;

@Repository
public class ChangeDTO implements ChangeDTOI {

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Change> getChangesForWorker(Long workerId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", workerId);
		
		return jdbcTemplate.query("select * from izmjena_stanja i "
				+ "join korisnik k "
				+ "on i.id_korisnik = k.id_korisnik "
				+ "join artikl a "
				+ "on i.id_artikl = a.id_artikl "
				+ "where i.id_korisnik = :id", parameters, new ChangeMapper());
	}

	@Override
	public void addChange(String location, Long amount, Long warehouseId, Long articleId, Long userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("time", new Date());
		parameters.addValue("location", location);
		parameters.addValue("warehouseId", warehouseId);
		parameters.addValue("articleId", articleId);
		parameters.addValue("userId", userId);
		parameters.addValue("amount", amount);
		jdbcTemplate.update("insert into izmjena_stanja (vrijeme, lokacija, kolicina, id_skladiste, id_artikl, id_korisnik) "
				+ "values (:time, :location, :amount, :warehouseId, :articleId, :userId)", parameters);

	}

	@Override
	public Change getChangesByDateFromWorkers(Date time, Long articleId, Long warehouseId) {

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("time", time);
		parameters.addValue("articleId", articleId);
		parameters.addValue("warehouseId", warehouseId);
		
		try {
			return jdbcTemplate.queryForObject(
					"select iz.id_unos, iz.vrijeme, iz.lokacija, iz.id_skladiste, iz.id_artikl, iz.id_korisnik, iz.kolicina, k.naziv_korisnik, a.naziv_artikl "
					+ "from izmjena_stanja iz "
					+ "left join artikl a on iz.id_artikl = a.id_artikl "
					+ "left join korisnik k on iz.id_korisnik = k.id_korisnik "
					+ "where iz.vrijeme = :time "
					+ "and iz.id_artikl = :articleId "
					+ "and k.id_uloga = 1 "
					+ "and iz.id_skladiste = :warehouseId "
					+ "order by iz.id_unos desc "
					+ "limit 1", parameters, new ChangeMapper());
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
		
		
	}

}
