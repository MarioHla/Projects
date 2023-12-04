package DevOps404.Inventura.DTOI;

import java.util.List;

import DevOps404.Inventura.entity.Article;
import DevOps404.Inventura.entity.NumberOfArticleInWarehouse;
import DevOps404.Inventura.entity.WarehouseArticle;

public interface ArticleDTOI {

	public List<Article> getAllArticles();
	public Article getArticleById(Long id);
	public void addArticle(String articleName, String articleDescription, Integer groupId);
	public void updateArticle(Long articleId, String articleName, String articleDescription, Integer groupId);
	public void deleteArticleById(Long id);
	public WarehouseArticle getWarehouseArticle(Long userId, Long articleId);
	public void setWarehouseArticle(Long userId, Long articleId, Long newAmount);
	public List<WarehouseArticle> getAllArticlesInWerehouseByUserId(Long userId);
	public List<NumberOfArticleInWarehouse> getAllWarehouseArticlesCounts(Long aricleId);
}
