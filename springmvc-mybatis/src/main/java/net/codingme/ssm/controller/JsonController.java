package net.codingme.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.codingme.ssm.po.ItemsCustom;

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
	
	
}	
