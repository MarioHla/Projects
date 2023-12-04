package DevOps404.Inventura.DAO;

import java.util.List;

import org.springframework.stereotype.Service;

import DevOps404.Inventura.DTOI.ArticleGroupDTOI;
import DevOps404.Inventura.entity.ArticleGroup;

@Service
public class ArticleGroupDAO {

	private ArticleGroupDTOI articleGroupDTOI;
	
	public ArticleGroupDAO(ArticleGroupDTOI articleGroupDTOI) {
		this.articleGroupDTOI = articleGroupDTOI;
	}

	public void updateArticleGroup(Long groupId, String groupName, Long parentGroupId) {
		articleGroupDTOI.updateGroup(groupId, groupName, parentGroupId);
	}
	
	public void addArticleGroup(String groupName, Long parentGroupId) {
		articleGroupDTOI.addGroup(groupName, parentGroupId);
	}
	
	public List<ArticleGroup> getChildGroups(Long groupId) {
		return articleGroupDTOI.getChildGroups(groupId);
	}

	public List<ArticleGroup> getAllGroups() {
		return articleGroupDTOI.getAllGroups();
	}
	
	public ArticleGroup getGroupById(Long groupId) {
		return articleGroupDTOI.getGroupById(groupId);
	}
	
	public List<ArticleGroup> getParentLevelGroups(Long parentGroupId) {
		return articleGroupDTOI.getParentLevelGroups(parentGroupId);
	}

	
	
}
