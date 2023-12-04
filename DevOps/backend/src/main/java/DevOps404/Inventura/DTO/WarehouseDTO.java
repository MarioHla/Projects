package DevOps404.Inventura.DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import DevOps404.Inventura.DTOI.WarehouseDTOI;
import DevOps404.Inventura.entity.Warehouse;
import DevOps404.Inventura.mapper.WarehouseMapper;

@Repository
public class WarehouseDTO implements WarehouseDTOI {

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Warehouse> getAllWarehouses() {
		return jdbcTemplate.query("select * from skladiste", new WarehouseMapper());
	}

	@Override
	public Warehouse getWarehouseById(Long warehouseId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", warehouseId);
		return jdbcTemplate.queryForObject("select * from skladiste where id_skladiste = :id", parameters, new WarehouseMapper());
		
		
	}

	@Override
	public Warehouse getWarehouseByBossId(Long bossId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", bossId);
		return jdbcTemplate.queryForObject("select * from skladiste where id_sef_skladiste = :id", parameters, new WarehouseMapper());
	}

	@Override
	public Long getWerehouseByUserId(Long userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", userId);
		return jdbcTemplate.queryForObject("select s.id_skladiste from skladiste s left join korisnik k on s.id_skladiste = k.id_skladiste where k.id_korisnik = :id", parameters, Long.class);
	}

}
