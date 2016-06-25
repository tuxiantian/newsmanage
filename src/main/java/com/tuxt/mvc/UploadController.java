package com.tuxt.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller  
@RequestMapping("/file") 
public class UploadController {
	@RequestMapping("/upload1"   )  
	public String upload1(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){  

		for(int i = 0;i<files.length;i++){  
			System.out.println("fileName---------->" + files[i].getOriginalFilename());  

			if(!files[i].isEmpty()){  
				int pre = (int) System.currentTimeMillis();  
				try {  
					//拿到输出流，同时重命名上传的文件  
					FileOutputStream os = new FileOutputStream("J:/" + new Date().getTime() + files[i].getOriginalFilename());  
					//拿到上传文件的输入流  
					FileInputStream in = (FileInputStream) files[i].getInputStream();  

					//以写字节的方式写文件  
					int b = 0;  
					while((b=in.read()) != -1){  
						os.write(b);  
					}  
					os.flush();  
					os.close();  
					in.close();  
					int finaltime = (int) System.currentTimeMillis();  
					System.out.println(finaltime - pre);  

				} catch (Exception e) {  
					e.printStackTrace();  
					System.out.println("上传出错");  
				}  
			}  
		}  
		return "/file/success";  
	}  


	@RequestMapping(value="/upload2",method=RequestMethod.POST)  
	public String upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
		//创建一个通用的多部分解析器  
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		//判断 request 是否有文件上传,即多部分请求  
		if(multipartResolver.isMultipart(request)){  
			//转换成多部分request    
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
			//取得request中的所有文件名  
			Iterator<String> iter = multiRequest.getFileNames();  
			while(iter.hasNext()){  
				//记录上传过程起始时的时间，用来计算上传时间  
				int pre = (int) System.currentTimeMillis();  
				//取得上传文件  
				MultipartFile file = multiRequest.getFile(iter.next());  
				if(file != null){  
					//取得当前上传文件的文件名称  
					String myFileName = file.getOriginalFilename();  
					//如果名称不为“”,说明该文件存在，否则说明该文件不存在  
					if(myFileName.trim() !=""){  
						System.out.println(myFileName);  
						//重命名上传后的文件名  
						String fileName = file.getOriginalFilename(); 
						//当前日期 
						Date date = new Date(); 
						//格式化并转换String类型 
						String dir="J:/"+new SimpleDateFormat("yyyy/MM/dd").format(date); 
						//创建文件夹 
						File f = new File(dir); 
						if(!f.exists()) 
							f.mkdirs(); 
						//定义上传路径  
						String path = dir +"/"+ fileName;  
						File localFile = new File(path);  
						file.transferTo(localFile);  
					}  
				}  
				//记录上传该文件后的时间  
				int finaltime = (int) System.currentTimeMillis();  
				System.out.println(finaltime - pre);  
			}  

		}  
		return "/file/success";  
	}  

	@RequestMapping("/toUpload" )   
	public String toUpload() {  

		return "/file/upload";  
	}  
	@RequestMapping("/toUpload2" )   
	public String toUpload2() {  

		return "/file/upload2";  
	} 
}
