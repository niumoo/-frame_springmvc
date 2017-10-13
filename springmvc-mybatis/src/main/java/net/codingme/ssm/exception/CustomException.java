package net.codingme.ssm.exception;

/**
 * Description：
 * 			系统的自定义异常类，针对预期的异常，需要再程序中抛出此类的异常
 * 			如果与业务功能相关的异常，建议在service中抛出异常。
 *			与业务功能没有关系的异常，建议在controller中抛出。
 * @author  Darcy
 * @date    2017年10月13日上午9:37:53
 * @version 1.0
 */
public class CustomException extends Exception {
	
	//异常信息
	private String message;
	
	//构造方法
	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
