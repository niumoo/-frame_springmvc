package net.codingme.ssm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.codingme.ssm.po.ItemsCustom;
import net.codingme.ssm.po.User;

/**
 * Description：
 * 			JSON数据交互的测试
 * 
 * @author  Darcy
 * @date    2017年10月15日上午10:32:58
 * @version 1.0
 */
@Controller
public class JsonController {
	
	
	/**
	 * JSON的交互测试
	 * 		请求：JSON串，商品信息
	 * 		输出：JSON串，商品信息
	 * @RequestBody：将请求的json串转换成pojo
	 * @ResponseBody：将输出的pojo转换成json
	 * 
	 * @param itemsCustom
	 * @return
	 */
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom requstJson(@RequestBody ItemsCustom itemsCustom) {
		
		return itemsCustom;
	}
	
	/**
	 * JSON的交互测试
	 * 		请求：key/value，商品信息
	 * 		输出：JSON串，商品信息
	 * @param itemsCustom
	 * @return
	 */
	@RequestMapping("/responseJson")
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom) {
		return itemsCustom;
	}
	
//	@RequestMapping("/queryJson")
	@RequestMapping(value="/queryJson",method= {RequestMethod.POST})
	public @ResponseBody Map queryJson() {
		User user = new User();
		user.setId(1);
		user.setBirthday(new Date());
		user.setSex("男");
		user.setUsername("Niu");
		user.setAddress("HFUU");
		
		User user2 = new User();
		user2.setId(2);
		user2.setBirthday(new Date());
		user2.setSex("女");
		user2.setUsername("Zxy");
		user2.setAddress("HFUU");
		ArrayList<User> arrayList = new ArrayList<User>();
		arrayList.add(user);
		arrayList.add(user2);
		
		Map<String, Serializable> map = new HashMap();
		map.put("data", arrayList);
		map.put("message", "查询成功");
		map.put("code", 1);
//		return user;
		return map;
	}
	
	
}	
