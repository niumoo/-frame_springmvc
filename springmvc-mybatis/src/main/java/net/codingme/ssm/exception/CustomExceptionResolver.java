package net.codingme.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description：
 * 			全局的异常处理器
 * 		思路：
 *			系统遇到异常，在程序中手动抛出，dao抛给service、service给controller、controller抛给前端控制器，前端控制器调用全局异常处理器。
 *			全局异常处理器处理思路：
 *				解析出异常类型
 *				如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示
 *				如果该 异常类型不是系统 自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）
 *	 
 * @author  Darcy
 * @date    2017年10月13日上午9:43:14
 * @version 1.0
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		CustomException customException = null;
		if(ex instanceof CustomException) {
			customException = (CustomException) ex;
		}else {
			customException = new CustomException("未知错误");
		}
		
		// 获取错误信息
		String message = customException.getMessage();
		// 放到request域
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("message",message);
		return modelAndView;
	}

}
