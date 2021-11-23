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
	private final AtomicLong counter = new AtomicLong(); // Long Ÿ�� ������ ���� thread-safe�ϰ� ó�����ִ� Ŭ����

	@RequestMapping(value = "/todo") // GETó��
	public Todo basic() {
		return new Todo(counter.incrementAndGet(), "�������");
	}

	@RequestMapping(value = "/todop", method = RequestMethod.POST) // POSTó��
	public Todo postBasic(@RequestParam(value = "todoTitle") String todoTitle) {
		return new Todo(counter.incrementAndGet(), todoTitle);
	}

	@RequestMapping(value = "/todor", method = RequestMethod.POST) // ResponseEntity ��ü �̿��غ���
	public ResponseEntity<Todo> postBasicResponseEntity(@RequestParam(value = "todoTitle") String todoTitle) {
		return new ResponseEntity(new Todo(counter.incrementAndGet(), todoTitle), HttpStatus.CREATED); // 201 �ڵ�� �Բ�
	}

	@RequestMapping(value = "/todos/{todoId}", method = RequestMethod.GET) // PathVariable �̿��ϱ�
	public Todo getPath(@PathVariable int todoId) {
		Todo todo1 = new Todo(1L, "��������");
		Todo todo2 = new Todo(2L, "��ȹ����");
		Todo todo3 = new Todo(3L, "�");
		
		Map<Integer, Todo> todoMap = new HashMap<>();
		todoMap.put(1, todo1);
		todoMap.put(2, todo2);
		todoMap.put(3, todo3);
		
		return todoMap.get(todoId);
	}
	
	@RequestMapping(value="/todoh", method=RequestMethod.GET) // HATEOAS �̿��ؼ� URL ���� ��� (���� ���ҽ� ��ġ Ȯ��)
	public ResponseEntity<TodoResource> geth(@RequestParam(value="todoTitle") String todoTitle) {
		TodoResource todoResource = new TodoResource(todoTitle);
		todoResource.add(linkTo(methodOn(BasicController.class).geth(todoTitle)).withSelfRel());
		
		return new ResponseEntity(todoResource, HttpStatus.OK);
	}

}
