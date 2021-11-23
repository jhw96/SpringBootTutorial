package info.thecodinglive.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import info.thecodinglive.model.Todo;
import info.thecodinglive.model.TodoResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping(value = "/basic")
public class BasicController {
	private final AtomicLong counter = new AtomicLong(); // Long 타입 변수에 대해 thread-safe하게 처리해주는 클래스

	@RequestMapping(value = "/todo") // GET처리
	public Todo basic() {
		return new Todo(counter.incrementAndGet(), "라면사오기");
	}

	@RequestMapping(value = "/todop", method = RequestMethod.POST) // POST처리
	public Todo postBasic(@RequestParam(value = "todoTitle") String todoTitle) {
		return new Todo(counter.incrementAndGet(), todoTitle);
	}

	@RequestMapping(value = "/todor", method = RequestMethod.POST) // ResponseEntity 객체 이용해보기
	public ResponseEntity<Todo> postBasicResponseEntity(@RequestParam(value = "todoTitle") String todoTitle) {
		return new ResponseEntity(new Todo(counter.incrementAndGet(), todoTitle), HttpStatus.CREATED); // 201 코드와 함께
	}

	@RequestMapping(value = "/todos/{todoId}", method = RequestMethod.GET) // PathVariable 이용하기
	public Todo getPath(@PathVariable int todoId) {
		Todo todo1 = new Todo(1L, "문서쓰기");
		Todo todo2 = new Todo(2L, "기획쓰기");
		Todo todo3 = new Todo(3L, "운동");
		
		Map<Integer, Todo> todoMap = new HashMap<>();
		todoMap.put(1, todo1);
		todoMap.put(2, todo2);
		todoMap.put(3, todo3);
		
		return todoMap.get(todoId);
	}
	
	@RequestMapping(value="/todoh", method=RequestMethod.GET) // HATEOAS 이용해서 URL 정보 얻기 (실제 리소스 위치 확인)
	public ResponseEntity<TodoResource> geth(@RequestParam(value="todoTitle") String todoTitle) {
		TodoResource todoResource = new TodoResource(todoTitle);
		todoResource.add(linkTo(methodOn(BasicController.class).geth(todoTitle)).withSelfRel());
		
		return new ResponseEntity(todoResource, HttpStatus.OK);
	}

}
