package DevOps404.Inventura.requests;

public class UpdateArticleGroupRequest {

	private Long groupId;
	private String groupName;
	private Long parentGroupId;
	
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
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
