package DevOps404.Inventura.DAO;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.jayway.jsonpath.JsonPath;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class ArticleScanningTests {
	
	private String token;
	private Integer userId;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void signIn() throws Exception {
		MvcResult result = mockMvc.perform(post("/signin")
				.content("{\"username\":\"Worker1\",\"password\":\"worker\"}")
			    .contentType(MediaType.APPLICATION_JSON)
			    .characterEncoding("UTF-8"))
			    .andExpect(status().isOk())
			    .andReturn();
		
		this.token = JsonPath.read(result.getResponse().getContentAsString(), "$.token");
		this.userId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
	}
	
	@Test
	@Order(1)
	void testUnauthorized() throws Exception {
		mockMvc.perform(get("/statistics/all")
				.header("Authorization", "Bearer " + this.token)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
			.andDo(print())
			.andExpect(status().isForbidden());

	}
		
	@Test
	@Order(2)
	void getArticleWithId1() throws Exception {
		MvcResult result = mockMvc.perform(get("/articles/byId")
				.param("id", "2")
				.header("Authorization", "Bearer " + this.token)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
		
		JSONObject tmp = new JSONObject(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
		this.mockMvc.perform(put("/articles/updateArticle")
				.content("{\"articleId\":2,\"articleName\":\"test\",\"articleDescription\":\"test\",\"groupId\":\"1\"}")
				.header("Authorization", "Bearer " + this.token)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
			.andDo(print())
			.andExpect(status().isOk());
		
		 mockMvc.perform(get("/articles/byId")
					.param("id", "2")
					.header("Authorization", "Bearer " + this.token)
					.contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(
						"{\"articleId\":2,"
						+ "\"name\":\"test\","
						+ "\"description\":\"test\","
						+ "\"groupId\":1,"
						+ "\"groupName\":\"proizvod\""
						+ "}"));
		 
		 String name = tmp.getString("name");
		 String desc = tmp.getString("description");
		 
		 this.mockMvc.perform(put("/articles/updateArticle")
					.content("{\"articleId\":" + tmp.getInt("articleId") 
							+",\"articleName\":\"" +name +"\""
							+ ",\"articleDescription\":\""+ desc +"\""
							+ ",\"groupId\":"+tmp.getInt("groupId") +"}")
					.header("Authorization", "Bearer " + this.token)
					.contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8"))
				.andDo(print())
				.andExpect(status().isOk());
		
	}

	@Test
	@Order(3)
	void postLowQuantityNotification() throws Exception {
		this.mockMvc.perform(post("/notifications/addNotification")
				.content("{\"userId\":\""+this.userId+"\",\"articleId\":\"1\"}")
				.header("Authorization", "Bearer " + this.token)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
			.andDo(print())
			.andExpect(status().isOk());
			
	}
	
	@Test
	@Order(4)
	void getLowQuantityNotification() throws Exception {
		
		MvcResult result = mockMvc.perform(post("/signin")
				.content("{\"username\":\"Boss1\",\"password\":\"boss\"}")
			    .contentType(MediaType.APPLICATION_JSON)
			    .characterEncoding("UTF-8"))
			    .andExpect(status().isOk())
			    .andReturn();
		
		this.token = JsonPath.read(result.getResponse().getContentAsString(), "$.token");
		this.userId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
		
		result = this.mockMvc.perform(get("/notifications/bossNotifications")
				.param("bossId",this.userId.toString())
				.header("Authorization", "Bearer " + this.token)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
			
		JSONArray obj = new JSONArray(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
		
		assertEquals(obj.getJSONObject(obj.length()-1).getInt("articleId"), 1);
		
		Integer notificationId = obj.getJSONObject(obj.length()-1).getInt("notificationId");
		
		this.mockMvc.perform(post("/notifications/rejectNotification")
				.content("{\"value\":"+notificationId+"}")
				.header("Authorization", "Bearer " + this.token)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
