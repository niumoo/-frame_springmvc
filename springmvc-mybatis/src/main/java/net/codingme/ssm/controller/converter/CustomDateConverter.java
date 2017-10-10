package net.codingme.ssm.controller.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

/**
 * Description：
 * 			自定义日期类型绑定
 * @author  Darcy
 * @date    2017年10月6日上午12:09:36
 * @version 1.0
 */
public class CustomDateConverter implements Converter<String, Date> {
	
	/**
	 * 实现将日期字符串转换成日期类型，格式：yyyy-MM-dd HH:mm:ss
	 */
	@Override
	public Date convert(String source) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return simpleDateFormat.parse(source);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
