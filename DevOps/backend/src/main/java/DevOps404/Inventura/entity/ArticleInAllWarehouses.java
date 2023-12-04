package DevOps404.Inventura.entity;

import java.util.List;

public class ArticleInAllWarehouses {

	private Long articleId;
	private String name;
	private List<NumberOfArticleInWarehouse> warehouseCount;
	
	public ArticleInAllWarehouses() {}

	public ArticleInAllWarehouses(Long articleId, String name, List<NumberOfArticleInWarehouse> warehouseCount) {
		super();
		this.articleId = articleId;
		this.name = name;
		this.warehouseCount = warehouseCount;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NumberOfArticleInWarehouse> getWarehouseCount() {
		return warehouseCount;
	}

	public void setWarehouseCount(List<NumberOfArticleInWarehouse> warehouseCount) {
		this.warehouseCount = warehouseCount;
	}

	
	
}
