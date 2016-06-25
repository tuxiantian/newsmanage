package com.tuxt.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuxt.domain.ItemCategorieInfo;
import com.tuxt.service.ItemCategoryService;

@RequestMapping("/itemcategory")
@Controller
public class ItemCategoryControler {
	@Autowired
	ItemCategoryService service;
	@RequestMapping(value="/list")
	public String list() {
		
		return "/itemcategory/list";
	}
	@RequestMapping(value="/save")
	public String save(HttpServletRequest request) {
		String []order=request.getParameterValues("orderby");
		service.saveOrder(order);
		return "redirect:/itemcategory/list";
	}
	@RequestMapping(value="/input/{pid}")
	public String input(@PathVariable("pid") Integer pid,Map<String, Object> map) {
		map.put("categorylist", service.GetCategoryOption(pid, 0));
		map.put("categorieInfo", new ItemCategorieInfo());
		return "/itemcategory/input";
	}
	@RequestMapping(value="/input2/{id}")
	public String input2(@PathVariable("id") Integer id,Map<String, Object> map) {
		map.put("categorylist", service.GetCategoryOption(0,id));
		map.put("categorieInfo", service.getItemCategorieInfo(id));
		return "/itemcategory/input";
	}
	@RequestMapping(value="/save2",method=RequestMethod.POST)
	public String save2(ItemCategorieInfo categorieInfo) {
		service.save(categorieInfo);
		return "redirect:/itemcategory/list";
	}
	@ModelAttribute
	public void get(@RequestParam(value="id",required=false) Integer id,
			Map<String, Object> map){
		if(id != null){//请求参数不为空，表示修改
			map.put("categorieInfo", service.getItemCategorieInfo(id));//此时的map键必须和update方法的入参保持一致
		}
	}
	@RequestMapping(value="/save2",method=RequestMethod.PUT)
	public String update(ItemCategorieInfo categorieInfo) {
		service.update(categorieInfo);
		return "redirect:/itemcategory/list";
	}
}
