package DevOps404.Inventura.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import DevOps404.Inventura.DTOI.ArticleDTOI;
import DevOps404.Inventura.DTOI.ChangeDTOI;
import DevOps404.Inventura.DTOI.ErrorDTOI;
import DevOps404.Inventura.DTOI.UserDTOI;
import DevOps404.Inventura.DTOI.WarehouseDTOI;
import DevOps404.Inventura.entity.Article;
import DevOps404.Inventura.entity.ArticleInAllWarehouses;
import DevOps404.Inventura.entity.Change;
import DevOps404.Inventura.entity.NumberOfArticleInWarehouse;
import DevOps404.Inventura.entity.WarehouseArticle;
import DevOps404.Inventura.utills.Coordiante;
import DevOps404.Inventura.utills.LocationUtill;

@Service
public class ArticleDAO {

	private ArticleDTOI articleDTOI;
	private ChangeDTOI changeDTOI;
	private WarehouseDTOI warehouseDTOI;
	private UserDTOI userDTOI;
	private ErrorDTOI errorDTOI;
	private LocationUtill location;
	
	public ArticleDAO(ArticleDTOI articleDTOI,ChangeDTOI changeDTOI, WarehouseDTOI warehouseDTOI, UserDTOI userDTOI, ErrorDTOI errorDTOI, LocationUtill location) {
		this.articleDTOI = articleDTOI;
		this.changeDTOI = changeDTOI;
		this.warehouseDTOI = warehouseDTOI;
		this.userDTOI = userDTOI;
		this.errorDTOI = errorDTOI;
		this.location = location;
	}

	public List<Article> getAllArticles() {
		return articleDTOI.getAllArticles();
	}

	public Article getArticleById(Long id) {
		return articleDTOI.getArticleById(id);
	}

	public void addNewArticle(String articleName, String articleDescription, Integer groupId) {
		articleDTOI.addArticle(articleName, articleDescription, groupId);
	}
	
	public void updateArticle(Long articleId, String articleName, String articleDescription, Integer groupId) {
		articleDTOI.updateArticle(articleId, articleName, articleDescription, groupId);
	}

	public void deleteArticle(Long id) {
		articleDTOI.deleteArticleById(id);		
	}

	public WarehouseArticle getWarehouseArticle(Long userId, Long articleId) {		
		return articleDTOI.getWarehouseArticle(userId, articleId);
	}

	public void setWarehouseArticle(Double x, Double y,Long userId, Long articleId, Long newAmount) {
		articleDTOI.setWarehouseArticle(userId, articleId, newAmount);
		Long werehouseId = warehouseDTOI.getWerehouseByUserId(userId);
		changeDTOI.addChange(location.getWerehouseByCoordinate(new Coordiante(x, y)), newAmount, werehouseId, articleId, userId);
		
		if(this.userDTOI.getRoleByUserId(userId)==2) {
			System.out.println("šef skladista s id: " + userId +" je napravio izmjenu");
			
			Change lastChangeOnArticleFromToday = changeDTOI.getChangesByDateFromWorkers(new Date(),articleId,werehouseId);
			
			if(lastChangeOnArticleFromToday != null) {
				if(lastChangeOnArticleFromToday.getAmount() != newAmount) {
					System.out.println("Dogodila se greška u inventuri i prijavljuje se direktoru");
					errorDTOI.addError(lastChangeOnArticleFromToday.getAmount(), newAmount, new Date(), werehouseId, articleId, lastChangeOnArticleFromToday.getUserId());
				}
			}
			
		}
		
	}

	public List<WarehouseArticle> getWarehouseArticles(Long userId) {
		return articleDTOI.getAllArticlesInWerehouseByUserId(userId);
	}

	public List<ArticleInAllWarehouses> getAllWarehousesAndArticleNumbers() {
		
		List<ArticleInAllWarehouses> result = new ArrayList<>();
		
		List<Article> articles = articleDTOI.getAllArticles();
		
		articles.forEach(a -> {
			List<NumberOfArticleInWarehouse> warehouseCount = articleDTOI.getAllWarehouseArticlesCounts(a.getArticleId());
			result.add(new ArticleInAllWarehouses(a.getArticleId(),a.getName(),warehouseCount));
		});
		
		return result;
	}
	
	
	
}
