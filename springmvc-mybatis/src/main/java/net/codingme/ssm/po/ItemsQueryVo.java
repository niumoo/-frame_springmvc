package net.codingme.ssm.po;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：
 * 			Items的扩展类ItemsCustom的组合类
 * 			方便扩展
 * 
 * @author  Darcy
 * @date    2017年10月4日下午4:05:35
 * @version 1.0
 */
public class ItemsQueryVo {
	
	// 为了系统的可扩展性，对原始生成的po进行扩展
	private ItemsCustom itemsCustom;

	// 为了可以接收POJO的List，定义进行测试
	private List<ItemsCustom> itemsList;
	
	// 为了可以接收Map，定义进行测试
	private Map<String, Object> itemsMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getItemsMap() {
		return itemsMap;
	}

	public void setItemsMap(Map<String, Object> itemsMap) {
		this.itemsMap = itemsMap;
	}

	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	
}
