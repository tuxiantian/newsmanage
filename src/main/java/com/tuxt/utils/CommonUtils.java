package com.tuxt.utils;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class CommonUtils {
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	public static <T> T toBean(Map<String,Object> map,Class<T> clazz) {
		try {
			T bean=clazz.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static String GetRepeatSign(String strReplaceSign, int intTreeNum)
    {
		StringBuffer stringBuffer=new StringBuffer();
		if (intTreeNum != 0) {
			 for (int i = 0; i < intTreeNum; i++)
             {
				 stringBuffer.append(strReplaceSign);
             }
		}
		return stringBuffer.toString();
    }
}
