package info.thecodinglive.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController {
	
	@RequestMapping(value="/th")
	public String templatePage(Model model) {
		model.addAttribute("message","boot template");
		
		return "th";
	}
	
	@RequestMapping(value="/th2")
	public String templatePage2(Model model) {
		Map<String,Object> pageMap = new HashMap();
		pageMap.put("color", "red");
		pageMap.put("name", "jam");
		pageMap.put("price", 3000);
		
		model.addAttribute("product", pageMap);
		
		return "th2";
	}

}