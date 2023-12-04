package DevOps404.Inventura.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DevOps404.Inventura.DAO.ArticleGroupDAO;
import DevOps404.Inventura.entity.ArticleGroup;
import DevOps404.Inventura.requests.AddArticleGroupRequest;
import DevOps404.Inventura.requests.UpdateArticleGroupRequest;

@RestController
@RequestMapping("/articleGroups")
@CrossOrigin
@PreAuthorize("hasRole('DIRECTOR')")
public class ArticleGroupController {

	private ArticleGroupDAO service;
	
	public ArticleGroupController(ArticleGroupDAO service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ArticleGroup>> getAllGroups(){
		return new ResponseEntity<List<ArticleGroup>> (service.getAllGroups(), HttpStatus.OK);
	}
	
	@GetMapping("/ById")
	public ResponseEntity<ArticleGroup> getGroupById(@RequestParam(value = "groupId") Long groupId){
		return new ResponseEntity<ArticleGroup> (service.getGroupById(groupId), HttpStatus.OK);
	}
	
	@GetMapping("/childGroups")
	public ResponseEntity<List<ArticleGroup>> getChildGroups(@RequestParam(value = "groupId") Long groupId) {
		return new ResponseEntity<List<ArticleGroup>> (service.getChildGroups(groupId), HttpStatus.OK);
	}
	
	@GetMapping("/parentLevelGroups")
	public ResponseEntity<List<ArticleGroup>> getParentLevelGroups(@RequestParam(value = "parentGroupId") Long parentGroupId) {
		return new ResponseEntity<List<ArticleGroup>> (service.getParentLevelGroups(parentGroupId), HttpStatus.OK);
	}
	
	@PostMapping("/addGroup")
	public ResponseEntity<String> addArticleGroup(@RequestBody AddArticleGroupRequest request) {
		service.addArticleGroup(request.getGroupName(), request.getParentGroupId());
		
		return new ResponseEntity<String>("Successfully added new group.", HttpStatus.OK);
	}
	
	@PutMapping("/updateGroup")
	public ResponseEntity<String> updateArticleGroup(@RequestBody UpdateArticleGroupRequest request) {
		service.updateArticleGroup(request.getGroupId(), request.getGroupName(), request.getParentGroupId());
		
		return new ResponseEntity<String>("Successfully updated group with id " + request.getGroupId() + ".", HttpStatus.OK);
	}
}
