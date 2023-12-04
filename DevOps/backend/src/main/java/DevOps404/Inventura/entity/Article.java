package DevOps404.Inventura.entity;

public class Article {

	private Long articleId;
	private String name;
	private String description;
	private Long groupId;
	private String groupName;
	
	public Article() {}
	
	public Article(Long articleId, String name, String description, Long groupId, String groupName) {
		this.articleId = articleId;
		this.name = name;
		this.description = description;
		this.groupId = groupId;
		this.groupName = groupName;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long aricleId) {
		this.articleId = aricleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	
	
	
}
