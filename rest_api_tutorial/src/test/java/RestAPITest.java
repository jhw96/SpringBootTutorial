import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import info.thecodinglive.RestApp;
import info.thecodinglive.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAPITest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetUserById() {
		String url = "http://localhost:8080/user/1";
		User user = restTemplate.getForObject(url, User.class);
		System.out.println("등록일:" + user.getRegDate() + "," + user.getUserId() + "," + user.getUname());
	}

	@Test
	public void testGetUsers() {
		String url = "http://localhost:8080/user";

		// user 정보를 map으로 변환하는 메소드
		ResponseEntity<Map<String, List<User>>> result = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<Map<String, List<User>>>() {
				});

		Map<String, List<User>> tempMap = (Map) result.getBody();

		ArrayList<User> resultArr = (ArrayList<User>) tempMap.get("result"); // "result" 키로 객체 받아오기

		for (User usr : resultArr) {
			System.out.println(usr.getUname());
		}
	}
}