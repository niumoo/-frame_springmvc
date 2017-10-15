package net.codingme.ssm.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.notification.StoppedByUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.codingme.ssm.controller.validation.ValiGroup1;
import net.codingme.ssm.exception.CustomException;
import net.codingme.ssm.po.ItemsCustom;
import net.codingme.ssm.po.ItemsQueryVo;
import net.codingme.ssm.service.ItemsService;

/**
 * Description：
 * 			Items的Controller
 * 
 * 数据回显：
 * 		比如输入有误需要再次回到表单页面重新填写，原来提交的数据仍然显示在页面上
 * 		简单类型：使用Model将传入的参数再放到request域
 * 		POJO类型：springmvc默认支持pojo数据回显，springmvc自动将形参中的pojo重新放回request域中，request的key为pojo的类名（首字母小写）
 * 		POJO自定义回显名字：@ModelAttribute完成数据回显，例如：@ModelAttribute("item") ItemsCustom itemsCustom，也可以再次model添加定义名字
 * @author  Darcy
 * @date    2017年10月4日下午4:33:08
 * @version 1.0
 */
//使用窄化请求映射，可以对url进行分类管理，这里定义根路径，最终访问的url路径为根路径+子路径
@Controller
@RequestMapping("/items")
public class ItemsController {
	
	@Autowired
	private ItemsService itemsService;
	
	/**
	  controller的返回值方式<1>：返回ModelAndView
	    	需要定义ModelAndView,将model和view分别设置
	    	
	  controller的返回值方式<2>：返回string
	  		A:返回逻辑视图名，真正视图(JSP路径) = 前缀+逻辑视图名+后缀
	  		B：返回redirect重定向，浏览器中的url会变化，重新请求，request无法共享
	  			return "redirect:queryItems.action";
	  		C:返回forward进行页面转发，url不变，request共享
	  			return "forward:queryItems.action";
	  			
	  controller的返回值方式<3>：返回void
			可以定义request和response，使用request或response指定响应结果：
			1、使用request转向页面，如下：
				request.getRequestDispatcher("页面路径").forward(request, response);
			2、也可以通过response页面重定向：
				response.sendRedirect("url")
			3、也可以通过response指定响应结果，例如响应json数据如下：
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write("json串");
	 */
	
	
	/**
	 * 自动绑定到pojo
	 * 查询商品列表信息，使用包装类来接收POJO，让sringMVC
	 * 
	 * @param itemsQueryVo 商品的包装类
	 * @return ModelAndView 视图
	 * @throws Exception
	 */
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(ItemsQueryVo itemsQueryVo)throws Exception{
		// 调用service查找数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		for (ItemsCustom itemsCustom : itemsList) {
			System.out.println("--------------"+itemsCustom.getName());
		}
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("items/itemsList");
		modelAndView.addObject("itemsList",itemsList);
		
		return modelAndView;
	}
	
	/**
	 * springMVC 对RESTFul的支持
	 * 		/itemsView/{id}： 中的id，是一个占位符，
	 * 		/@PathVariable：可以读取占位符中的参数
	 * 		如果占位符名称和形参名称一致，可以在@PathVariable中不指定名称
	 * 		
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id)throws Exception{
		// 根据ID查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		return itemsCustom;
	}
	
	
	/**
	 * 编辑商品			
	 * 		限制请求方式为POST OR GET：method= {RequestMethod.GET,RequestMethod.POST}
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	
	//	@RequestMapping(value="/editItems",method= {RequestMethod.GET,RequestMethod.POST})
	//	public String editItems(Model model)throws Exception{
	//		// 调用service根据商品id查询商品信息
	//		ItemsCustom itemsCustom = itemsService.findItemsById(1);
	//		model.addAttribute("itemsCustom",itemsCustom);
	//		return "items/editItems";
	//	}
	
	/**
	  //@RequestParam里边指定request传入参数名称和形参进行绑定。
    	 通过required属性指定参数是否必须要传入
		 通过defaultValue可以设置默认值，如果id参数没有传入，将默认值和形参绑定。
		 此处将id绑定到items_id
	 */
	@RequestMapping(value="/editItems",method= {RequestMethod.GET,RequestMethod.POST})
	public String editItems(Model model,@RequestParam(value="id",required=true) Integer items_id)throws Exception{
		// 调用service根据商品id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
		// 通过形参中的model将model数据传到页面
		// 相当于modelAndView.addObject方法
		model.addAttribute("itemsCustom",itemsCustom);
		return "items/editItems";
	}


	/**
	 * 商品信息修改提交,限制只接收Post请求
	 * 注意：ItemsCustom中存在时间类型参数，需要自定义日期类型绑定才可以！
	 * 这里注释了更新调用，只做空实现
	 * 对ItemsCustom添加校验
	 * 		在需要校验的pojo前边添加@Validated，在需要校验的pojo后边添加BindingResult bindingResult接收校验出错信息
	 *      注意：@Validated和BindingResult bindingResult是配对出现，并且形参顺序是固定的（一前一后）。
	 * 		value= {ValiGroup1.class}：说明只校验ValiGroup1的规则， 如果有多个规则需要使用,分割。在定义规则的地方可以指定所属分组
	 * 
	 * 添加文件上传
	 * 		MultipartFile itemsPic：文件上传，itemsPic对应页面的文件上传name
	 * 
	 * @since 2017.10.15  文件上传
	 * @since 2017.10.10 校验
	 * 
	 * @param request HttpServletRequest的请求信息
	 * @param id 要修改的商品ID
	 * @param itemsCustom 更新后的商品信息
	 * @return 重定向到信息列表页面
	 * @throws Exception
	 */
	@RequestMapping(value="/editItemsSubmit", method={RequestMethod.POST})
	public String editItemsSubmit(
			Model model,
			Integer id,
			@Validated(value= {ValiGroup1.class}) ItemsCustom itemsCustom ,BindingResult bindingResult,
			MultipartFile itemsPic)throws Exception {
		
		// 获取校验错误信息，如果有错误信息
		if(bindingResult.hasErrors()) {
			//输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError objectError : allErrors) {
				System.out.println("校验出的错误信息："+objectError.getDefaultMessage());
			}
			// 将错误信息传到页面
			model.addAttribute("allErrors",allErrors);
			// 出错重新到商品修改页面
			return "items/editItems";
		}
		/**
		 * 文件上传
		 */
		// 上传步骤1：原始文件名称
		String originalFilename = itemsPic.getOriginalFilename();
		// 上传步骤2：上传图片
		if(itemsPic!=null && originalFilename.length()>0) {
			// 上传步骤2.1：设置存储图片的物理路径
			String storePath = "D:\\webserver\\apache-tomcat-8.5.15\\webapp\\upload\\";
			
			// 上传步骤2.2原始文件后缀
			String suf = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
			
			// 上传步骤2.3新的图片名称
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName =sdf.format(new Date()) + suf;
			
			// 上传步骤2.4新图片
			File newFile = new File(storePath + newFileName);
			// 上传步骤2.5从内存中写入磁盘
			itemsPic.transferTo(newFile);
			// 上传步骤2.6将新图片名称写到itemsCustom中
			itemsCustom.setPic(newFileName);
		}
		
		// 调用service更新商品信息，页面需要将商品信息传到此方法
		itemsService.updateItems(id, itemsCustom);
		// 页面转发
		// return "forward:queryItems.action";
		// 重定向到商品查询列表
		return "redirect:queryItems.action";
	}
	
	
	/**
	 * 数组绑定，接收传送过来的多个Id
	 * 参数绑定---
	 * 只做控制层实现
	 * 
	 * @param itemsIds
	 * @return
	 */
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] itemsId) {
		for (Integer integer : itemsId) {
			System.out.println("数组绑定--要删除的商品ID："+integer);
		}
		// 重定向到商品查询列表
		return "redirect:queryItems.action";
	}
	
	/**
	 * 自动绑定到pojo
	 * 查询商品列表信息，使用包装类来接收POJO，让sringMVC
	 * 
	 * @param itemsQueryVo 商品的包装类
	 * @return ModelAndView 视图
	 * @throws Exception
	 */
	@RequestMapping("/editAllItems")
	public ModelAndView editAllItems(ItemsQueryVo itemsQueryVo)throws Exception{
		// 调用service查找数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("items/editAllItems");
		modelAndView.addObject("itemsList",itemsList);
		return modelAndView;
	}
	
	/**
	 * 绑定参数到List中ItemsQueryVo的itemsList属性中
	 * 接收要更新的所有商品信息 -- 
	 * 只做控制层实现
	 * @param itemsQueryVo
	 * @return
	 */
	@RequestMapping("editAllItemsSubmit")
	public String editAllItemsSubmit(ItemsQueryVo itemsQueryVo) {
		List<ItemsCustom> itemslist = itemsQueryVo.getItemsList();
		for (ItemsCustom itemsCustom : itemslist) {
			System.out.println("List参数绑定---名称："+itemsCustom.getName());
		}
		// 重定向到商品查询列表
		return "redirect:queryItems.action";
	}
	
	/**
	 * 跳转到添加商品页面
	 * @return
	 */
	@RequestMapping("addItems")
	public String  addItems() {
		return "items/addItems";
	}
	/**
	 * Map绑定
	 * 绑定到商品的包装类
	 * @param itemsQueryVo
	 * @return
	 */
	@RequestMapping("addItemsSubmit")
	public String  addItemsSubmit(ItemsQueryVo itemsQueryVo) {
		Map<String, Object> itemsMap = itemsQueryVo.getItemsMap();
		Set<String> keySet = itemsMap.keySet();
		for (String key : keySet) {
			String value = (String) itemsMap.get(key);
			System.out.println("Map绑定----+"+key+"："+value);
		}
		// 重定向到商品查询列表
		return "redirect:queryItems.action";
	}
	
	@Test
	public void test() {
		String originalFilename = "adasasd.dadsa.d.jpg";
		// 原始文件后缀
		String suf = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
		// 新的图片名称
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName =sdf.format(new Date()) + suf;
		System.out.println(newFileName);
	}
	
}
