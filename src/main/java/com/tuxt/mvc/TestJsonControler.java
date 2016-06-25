package com.tuxt.mvc;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuxt.dao.ItemDao;
import com.tuxt.domain.ItemInfo;



@Controller
public class TestJsonControler {
	
	@Autowired
	ItemDao itemdao;
	@ResponseBody
	@RequestMapping(value="/testjson1")
	public List<ItemInfo> testjson(){
		
		return itemdao.getItemInfos();
	}
	@ResponseBody
	@RequestMapping(value="/testjson2",produces="text/plain;charset=UTF-8")
	public String testjson2(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return "当前时间："+df.format(new Date());
	}
	@ResponseBody
	@RequestMapping(value="/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
		byte[] body=null;
		ServletContext servletContext=session.getServletContext();
		InputStream inputStream=servletContext.getResourceAsStream("/files/1.jpg");
		body=new byte[inputStream.available()];
		inputStream.read(body);
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=1.jpg");
		HttpStatus httpStatus=HttpStatus.OK;
		ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(body, headers, httpStatus);
		return responseEntity;
	}

}
