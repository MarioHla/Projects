package DevOps404.Inventura.DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import DevOps404.Inventura.DTOI.ArticleGroupDTOI;
import DevOps404.Inventura.entity.ArticleGroup;
import DevOps404.Inventura.mapper.ArticleGroupMapper;

@Repository
public class ArticleGroupDTO implements ArticleGroupDTOI {

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public void updateGroup(Long groupId, String groupName, Long parentGroupId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("groupId", groupId);
		parameters.addValue("groupName", groupName);
		parameters.addValue("parentGroupId", parentGroupId);
		
		jdbcTemplate.update("update skupina set naziv_skupina = :groupName , "
				+ "id_nadskupina = :parentGroupId "
				+ "where id_skupina = :groupId", parameters);
	}

	@Override
	public void addGroup(String groupName, Long parentGroupId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("groupName", groupName);
		parameters.addValue("parentGroupId", parentGroupId);
		
		jdbcTemplate.update("insert into skupina (naziv_skupina, id_nadskupina) values (:groupName, :parentGroupId)", parameters);
	}

	@Override
	public List<ArticleGroup> getChildGroups(Long groupId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("groupId", groupId);
		
		if(groupId == 0) {
			return jdbcTemplate.query(
					"select s.*,"
					+ "(select count(id_skupina) from skupina where id_nadskupina = s.id_skupina) as broj_djece "
					+ "from skupina s where id_skupina = 1", new ArticleGroupMapper());
		}
		
		return jdbcTemplate.query(
				"select id_skupina, naziv_skupina, id_nadskupina,"
				+ "(select count(id_skupina) from skupina where id_nadskupina = s.id_skupina) as broj_djece "
				+ "from skupina s where id_nadskupina = :groupId", parameters, new ArticleGroupMapper());
	}

	@Override
	public List<ArticleGroup> getAllGroups() {
		return jdbcTemplate.query(
				"select id_skupina, naziv_skupina, id_nadskupina, "
				+ "(select count(id_skupina) from skupina where id_nadskupina = s.id_skupina) as broj_djece "
				+ "from skupina s", new ArticleGroupMapper());
	}
	

	@Override
	public ArticleGroup getGroupById(Long groupId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("groupId", groupId);
		
		return jdbcTemplate.queryForObject(
				"select id_skupina, naziv_skupina, id_nadskupina, "
				+ "(select count(id_skupina) from skupina where id_nadskupina = s.id_skupina) as broj_djece "
				+ "from skupina s "
				+ "where id_skupina = :groupId",parameters, new ArticleGroupMapper());
	}

	@Override
	public List<ArticleGroup> getParentLevelGroups(Long parentGroupId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("parentGroupId", parentGroupId);
		
		if(parentGroupId == 1) {
			return jdbcTemplate.query(
					"select id_skupina, naziv_skupina, id_nadskupina, "
					+ "(select count(id_skupina) from skupina where id_nadskupina = 1) as broj_djece "
					+ "from skupina where id_skupina = 1", new ArticleGroupMapper());
		}
//		
//		if(parentGroupId == null) {
//			return jdbcTemplate.query("select id_skupina, naziv_skupina, id_nadskupina, "
//					+ "(select count(id_skupina) from skupina where id_nadskupina = s.id_skupina) as broj_djece "
//					+ "from skupina s where id_skupina = 1", new ArticleGroupMapper());
//		}
		
		return jdbcTemplate.query("select id_skupina, naziv_skupina, id_nadskupina, "
				+ "(select count(id_skupina) from skupina where id_nadskupina = s.id_skupina) as broj_djece "
				+ "from skupina s where id_nadskupina = "
				+ "(select id_nadskupina "
				+ "from skupina "
				+ "where id_skupina = :parentGroupId)", parameters, new ArticleGroupMapper());
	}

	
	


}
