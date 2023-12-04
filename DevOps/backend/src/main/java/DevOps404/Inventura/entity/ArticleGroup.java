package DevOps404.Inventura.entity;

public class ArticleGroup {

	private Long groupId;
	private String groupName;
	private Long parentGroupId;
	private Integer childGroupCount;
	
	public ArticleGroup() { }
	
	public ArticleGroup(Long groupId, String groupName, Long parentGroupId, Integer childGroupCount) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.parentGroupId = parentGroupId;
		this.childGroupCount = childGroupCount;
	}

	public Integer getChildGroupCount() {
		return childGroupCount;
	}

	public void setChildGroupCount(Integer childGroupCount) {
		this.childGroupCount = childGroupCount;
	}

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
