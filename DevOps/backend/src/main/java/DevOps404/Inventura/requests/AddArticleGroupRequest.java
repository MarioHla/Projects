package DevOps404.Inventura.requests;

public class AddArticleGroupRequest {

	private String groupName;
	private Long parentGroupId;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getParentGroupId() {
		return parentGroupId;
	}
	public void setParentGroupId(Long parentGroupId) {
		this.parentGroupId = parentGroupId;
	}
	
	
	
}
