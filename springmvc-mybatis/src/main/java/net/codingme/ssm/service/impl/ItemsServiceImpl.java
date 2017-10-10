package net.codingme.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import net.codingme.ssm.mapper.ItemsMapper;
import net.codingme.ssm.mapper.ItemsMapperCustom;
import net.codingme.ssm.po.Items;
import net.codingme.ssm.po.ItemsCustom;
import net.codingme.ssm.po.ItemsQueryVo;
import net.codingme.ssm.service.ItemsService;

/**
 * Description：
 * 			ItemsService的实现类
 * @author  Darcy
 * @date    2017年10月4日下午4:16:02
 * @version 1.0
 */
public class ItemsServiceImpl implements ItemsService {
	
	@Autowired
	private ItemsMapperCustom ItemsMapperCustom;

	@Autowired
	private ItemsMapper itemsMapper;

	/**
	 * 根据条件查询，这里在SQL中只实现了名字的模糊查询
	 */
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception{
		return ItemsMapperCustom.findItemsList(itemsQueryVo);
	}

	/**
	 * 根据ID查询信息
	 */
	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		ItemsCustom itemsCustom = new ItemsCustom();
		Items items = itemsMapper.selectByPrimaryKey(id);
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		if(items !=null ) {
			itemsCustom.setId(id);
			itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
		}
	}

}
