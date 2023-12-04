package DevOps404.Inventura.DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import DevOps404.Inventura.DTOI.StatisticsDTOI;
import DevOps404.Inventura.entity.StatisticsUserInfo;
import DevOps404.Inventura.mapper.StatisticsUserInfoMapper;

@Repository
public class StatisticsDTO implements StatisticsDTOI {

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Integer getAllChanges() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		try {
			return jdbcTemplate.queryForObject(
					"select count(*) " 
					+ "from izmjena_stanja "
					+ "natural join korisnik "
					+ "natural join uloga "
					+ "where naziv_uloga = 'ROLE_WORKER'", parameters, Integer.class);
		} catch(EmptyResultDataAccessException ex) {
			System.out.println("No scans found");
		}
		return 0;
		
	}

	@Override
	public Integer getAllErrors() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		try {
		return jdbcTemplate.queryForObject(
				"select count(*) "
				+ "from pogreska p "
				+ "join korisnik k "
				+ "on p.id_skladistar = k.id_korisnik "
				+ "natural join uloga", parameters, Integer.class);
		} catch(EmptyResultDataAccessException ex) {
			System.out.println("No errors found");
		}
		return 0;
	}

	@Override
	public Integer getWorkerChanges(Long workerId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("workerId", workerId);
		
		try {
		return jdbcTemplate.queryForObject(
				"select count(*) " 
				+ "from izmjena_stanja "
				+ "natural join korisnik "
				+ "natural join uloga "
				+ "where naziv_uloga = 'ROLE_WORKER' "
				+ "and id_korisnik = :workerId", parameters, Integer.class);
		} catch(EmptyResultDataAccessException ex) {
			System.out.println("No errors found");
		}
		return 0;
	}

	@Override
	public Integer getWorkerErrors(Long workerId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("workerId", workerId);
		
		try {
			return jdbcTemplate.queryForObject(
					"select count(*) "
					+ "from pogreska p "
					+ "join korisnik k "
					+ "on p.id_skladistar = k.id_korisnik "
					+ "natural join uloga "
					+ "where id_korisnik = :workerId", parameters, Integer.class);
		} catch(EmptyResultDataAccessException ex) {
			System.out.println("No errors found");
		}
		return 0;
	}

	@Override
	public List<StatisticsUserInfo> getAllWorkers() {
		
		return jdbcTemplate.query(
				"select id_korisnik, naziv_korisnik from korisnik "
				+ "natural join uloga "
				+ "where naziv_uloga = 'ROLE_WORKER'", new StatisticsUserInfoMapper());
	}
	


}
