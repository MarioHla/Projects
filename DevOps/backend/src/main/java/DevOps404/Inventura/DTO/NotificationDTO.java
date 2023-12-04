package DevOps404.Inventura.DTO;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import DevOps404.Inventura.DTOI.NotificationDTOI;
import DevOps404.Inventura.entity.ErrorInInventory;
import DevOps404.Inventura.entity.LowQuantityNotification;
import DevOps404.Inventura.mapper.ErrorRowMapper;
import DevOps404.Inventura.mapper.LowQuantityNotificationMapper;

@Repository
public class NotificationDTO implements NotificationDTOI {

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<LowQuantityNotification> getAllLowQuantityNotificationsForBoss(Long bossId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", bossId);
		return jdbcTemplate.query("select * from dojava_nisko_stanje "
				+ "join skladiste on dojava_nisko_stanje.id_skladiste = skladiste.id_skladiste "
				+ "join artikl on dojava_nisko_stanje.id_artikl = artikl.id_artikl "
				+ "where status = 0 "
				+ "and id_sef_skladiste = :id", parameters, new LowQuantityNotificationMapper());
	}

	@Override
	public List<LowQuantityNotification> getAllActiveLowQuantityNotificationsForDirector() {
		return jdbcTemplate.query("select * from dojava_nisko_stanje "
				+ "join skladiste on dojava_nisko_stanje.id_skladiste = skladiste.id_skladiste "
				+ "join artikl on dojava_nisko_stanje.id_artikl = artikl.id_artikl "
				+ "where status = 1 "
				, new LowQuantityNotificationMapper());
	}

	@Override
	public List<LowQuantityNotification> getAllHistoryLowQuantityNotificationsForDirector() {
		return jdbcTemplate.query("select * from dojava_nisko_stanje "
				+ "join skladiste on dojava_nisko_stanje.id_skladiste = skladiste.id_skladiste "
				+ "join artikl on dojava_nisko_stanje.id_artikl = artikl.id_artikl "
				+ "where status = -2 or status = 2 "
				, new LowQuantityNotificationMapper());
	}

	@Override
	public void addNewLowQuantityNotification(Long articleId, Long warehouseId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("status", 0);
    	parameters.addValue("time", new Date());
    	parameters.addValue("articleId", articleId);
    	parameters.addValue("warehouseId", warehouseId);
    	jdbcTemplate.update("insert into dojava_nisko_stanje (status, vrijeme, id_artikl, id_skladiste) values (:status,:time,:articleId,:warehouseId)", parameters);

	}

	@Override
	public void passLowQuantityNotificationToDirector(Long notificationId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", notificationId);
    	jdbcTemplate.update("update dojava_nisko_stanje set status = 1 where id_dojava = :id", parameters);

	}

	@Override
	public void markLowQuantityNotificationAsCompleted(Long notificationId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", notificationId);
    	jdbcTemplate.update("update dojava_nisko_stanje set status = 2 where id_dojava = :id", parameters);

	}

	@Override
	public void markLowQuantityNotificationAsRejected(Long notificationId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", notificationId);
    	jdbcTemplate.update("update dojava_nisko_stanje set status = -1 where id_dojava = :id", parameters);

	}

	@Override
	public void markLowQuantityNotificationAsRejectedByDirector(Long notificationId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", notificationId);
		jdbcTemplate.update("update dojava_nisko_stanje set status = -2 where id_dojava = :id", parameters);

	}


	@Override
	public List<ErrorInInventory> getAllUnresolvedErrors() {
		return jdbcTemplate.query("select * from pogreska p " +
				"join korisnik k on k.id_korisnik = p.id_skladistar " +
				"join artikl s on s.id_artikl = p.id_artikl "+
				"where p.status = 0", new ErrorRowMapper());
	}

	@Override
	public void markErrorNotificationAsSeen(Long errorId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", errorId);
		 jdbcTemplate.update("update pogreska p set status = 1 where id_pogreska = :id", parameters);

	}

	@Override
	public void markErrorNotificationAsRejected(Long errorId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", errorId);
		jdbcTemplate.update("update pogreska p set status = -1 where id_pogreska = :id", parameters);

	}

	@Override
	public List<ErrorInInventory> getAllHistoryErrorNotificationsForDirector() {
		return jdbcTemplate.query("select * from pogreska p " +
				"join korisnik k on k.id_korisnik = p.id_skladistar " +
				"join artikl s on s.id_artikl = p.id_artikl "+
				"where p.status = 1 or p.status = -1", new ErrorRowMapper());
	}



}
