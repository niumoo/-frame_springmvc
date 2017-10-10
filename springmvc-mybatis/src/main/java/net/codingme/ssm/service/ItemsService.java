package net.codingme.ssm.service;

import java.util.List;

import net.codingme.ssm.po.ItemsCustom;
import net.codingme.ssm.po.ItemsQueryVo;

/**
 * Description：
 * 			商品的业务操作接口
 * 
 * @author  Darcy
 * @date    2017年10月4日下午4:15:13
 * @version 1.0
 */
public interface ItemsService {
	
	// 查询商品列表信息
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	/**
	 * 根据商品ID查询商品信息
	 * @param id 查询商品的id
	 * @return
	 * @throws Exception
	 */
	public ItemsCustom findItemsById(Integer id) throws Exception;
	
	/**
	 * 修改商品信息
	 * @param id 修改商品的ID
	 * @param itemsCustom 修改的商品信息
	 * @throws Exception
	 */
	public void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;
	
}
