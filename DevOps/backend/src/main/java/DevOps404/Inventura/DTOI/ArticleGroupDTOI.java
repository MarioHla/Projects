package DevOps404.Inventura.DTOI;

import java.util.List;

import DevOps404.Inventura.entity.ArticleGroup;

public interface ArticleGroupDTOI {

	public void updateGroup(Long groupId, String groupName, Long parentGroupId);
	public void addGroup(String groupName, Long parentGroupId);
	public List<ArticleGroup> getChildGroups(Long groupId);
	public List<ArticleGroup> getAllGroups();
	public List<ArticleGroup> getParentLevelGroups(Long parentGroupId);
	public ArticleGroup getGroupById(Long groupId);
	
	
}
