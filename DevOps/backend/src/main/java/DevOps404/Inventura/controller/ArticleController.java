package DevOps404.Inventura.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DevOps404.Inventura.DAO.ArticleDAO;
import DevOps404.Inventura.entity.Article;
import DevOps404.Inventura.entity.ArticleInAllWarehouses;
import DevOps404.Inventura.entity.WarehouseArticle;
import DevOps404.Inventura.requests.AddArticleRequest;
import DevOps404.Inventura.requests.UpdateArticleRequest;
import DevOps404.Inventura.requests.WerehouseArticleRequest;

@RestController
@RequestMapping("/articles")
@CrossOrigin
@PreAuthorize("hasRole('WORKER') or hasRole('BOSS') or hasRole('DIRECTOR')")
public class ArticleController {

	private ArticleDAO articleService;
	
	public ArticleController(ArticleDAO articleService) {
		this.articleService = articleService;
	}
	
	@GetMapping
	public ResponseEntity<List<Article>> getAllArticles(){		
		return new ResponseEntity<List<Article>>(articleService.getAllArticles(),HttpStatus.OK);
	}
	
	@GetMapping("/byId")
	public ResponseEntity<Article> getArticleById(@RequestParam(value = "id") Long id){
		return new ResponseEntity<Article>(articleService.getArticleById(id),HttpStatus.OK);
	}
	
	@PostMapping("addArticle")
	public ResponseEntity<String> addNewArticle(@RequestBody AddArticleRequest addArticleRequest) {
		articleService.addNewArticle(addArticleRequest.getArticleName(), addArticleRequest.getArticleDescription(), addArticleRequest.getGroupId());
		
		return new ResponseEntity<String>("Added new article " + addArticleRequest.getArticleName(),HttpStatus.OK);
	}
	
	@PutMapping("updateArticle")
	public ResponseEntity<String> updateArticle(@RequestBody UpdateArticleRequest updateArticleRequest) {
		articleService.updateArticle(updateArticleRequest.getArticleId(), updateArticleRequest.getArticleName(),
				updateArticleRequest.getArticleDescription(), updateArticleRequest.getGroupId());
		
		return new ResponseEntity<String>("Updated article with id " + updateArticleRequest.getArticleId() + ".",HttpStatus.OK);
	}
	
	@DeleteMapping("deleteArticle")
	public ResponseEntity<String> deleteArticle(@RequestParam(value = "id" )Long id) {
		articleService.deleteArticle(id);
		return new ResponseEntity<String>("Deleted article with ID:" + id,HttpStatus.OK);
	}
	
	//dohvat svih articla u werehouse-u
	@GetMapping("viewArticlesInWerehouse")
	ResponseEntity<List<WarehouseArticle>> getWarehouseArticles(@RequestParam(value = "userId" ) Long id){
		return new ResponseEntity<List<WarehouseArticle>>(articleService.getWarehouseArticles(id),HttpStatus.OK);
	}
	
	@GetMapping("viewArticleInWerehouse")
	ResponseEntity<WarehouseArticle> getWarehouseArticle(@RequestParam(value = "userId" ) Long userId, @RequestParam(value = "articleId" ) Long articleId){
		WarehouseArticle article = articleService.getWarehouseArticle(userId,articleId);
		if(article == null)
			return new ResponseEntity<WarehouseArticle>(article,HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<WarehouseArticle>(article,HttpStatus.OK);
	}
	
	@PutMapping("setArticleInWerehouse")
	ResponseEntity<String> setWarehouseArticle(@RequestBody WerehouseArticleRequest request){
		articleService.setWarehouseArticle(request.getxLocation(),request.getyLocation(), request.getUserId(), request.getArticleId(), request.getNewAmount());
		
		return new ResponseEntity<String>("Set new amount to " + request.getNewAmount(),HttpStatus.OK);
	}
	
	@GetMapping("articlesInWarehousesCount")
	ResponseEntity<List<ArticleInAllWarehouses>> getArticlesInAllWarehouses(){
		return new ResponseEntity<List<ArticleInAllWarehouses>>(articleService.getAllWarehousesAndArticleNumbers(), HttpStatus.OK);
	}
	
	
}
