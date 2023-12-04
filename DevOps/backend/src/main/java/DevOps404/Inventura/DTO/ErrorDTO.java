package DevOps404.Inventura.DTO;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import DevOps404.Inventura.DTOI.ErrorDTOI;

@Repository
public class ErrorDTO implements ErrorDTOI{

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void addError(Long badAmount, Long realAmount, Date time, Long werehouseId, Long articleId, Long workerId) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("badAmount", badAmount);
		parameters.addValue("realAmount", realAmount);
		parameters.addValue("time", time);
		parameters.addValue("werehouseId", werehouseId);
		parameters.addValue("articleId", articleId);
		parameters.addValue("workerId", workerId);
		
		jdbcTemplate.update(
				"insert into pogreska "
				+ "( kolicina, stvarna_kolicina, datum_vrijeme, id_skladiste, id_artikl, id_skladistar, status) "
				+ "values (:badAmount,:realAmount, :time::date,:werehouseId,:articleId,:workerId, 0)", parameters);
		
	}
	
}
