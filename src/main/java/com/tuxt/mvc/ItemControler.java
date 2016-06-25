package com.tuxt.mvc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuxt.dao.ItemCategoryDao;
import com.tuxt.domain.ItemInfo;
import com.tuxt.service.ItemCategoryService;
import com.tuxt.service.ItemDaoService;

@RequestMapping(value="/item")
@Controller
public class ItemControler {
	@Autowired
	ItemCategoryService itemcategoryservice;
	@Autowired
	ItemDaoService itemdaoservice;
	@RequestMapping(value="/list")
	public String itemlist(Map<String, Object> map){
		map.put("items", itemdaoservice.getItemInfos());
		return "/item/list";
	}
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String searchitem(@RequestParam(value="condition",required=false) String condition,Map<String, Object> map){
		map.put("items", itemdaoservice.searchitems(condition.trim()));
		return "/item/list";
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		itemdaoservice.delete(id);
		return "redirect:/item/list";
	}
	@ModelAttribute
	public void get(@RequestParam(value="itemid",required=false) Integer id,
			Map<String, Object> map){
		if(id != null){//请求参数不为空，表示修改
			map.put("itemInfo", itemdaoservice.get(id));//此时的map键必须和update方法的入参保持一致
		}
	}
	@RequestMapping("/input")
	public String addnews(Map<String, Object> map) {
		map.put("strcategory", itemcategoryservice.GetCategoryOption(0, 0));
		map.put("itemInfo", new ItemInfo());
		return "/item/input";
	}
	@RequestMapping("/input/{id}")
	public String addnews(@PathVariable("id") Integer id,Map<String, Object> map) {
		map.put("strcategory", itemcategoryservice.GetCategoryOption(0, 0));
		map.put("itemInfo", itemdaoservice.get(id));
		return "/item/input";
	}
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id,Map<String, Object> map) {
		map.put("item", itemdaoservice.get(id));
		
		return "/item/detail";
	}
	@RequestMapping(value="/save",method=RequestMethod.PUT)
	public String update(ItemInfo itemInfo)
	{
		itemdaoservice.update(itemInfo);
		return "redirect:/item/list";
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(ItemInfo itemInfo) {
		
		itemInfo.setDeleteFlag(0);
		itemInfo.setStatus(0);
		itemdaoservice.save(itemInfo);
		return "redirect:/item/list";
	}
}
