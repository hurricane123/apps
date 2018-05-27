package com.hurricane.app.cartoon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/video") 
public class VideoController {
	private static Logger logger = LoggerFactory.getLogger(VideoController.class);
	
	@Value("${baseDir}")
	private String baseDir;
	
	@RequestMapping("jumpToVideoView")
	public Object jumpToImgView(@RequestParam(defaultValue="") String fileName,Model model) throws Exception {
		logger.debug("点击的文件："+fileName);
		model.addAttribute("videoUrl", fileName);
		return "video";
	}
	
	@RequestMapping("view")
	public void view(@RequestParam(defaultValue="") String url,Model model,HttpServletResponse response) throws Exception {
		String filePath = baseDir + "/" + url;
		logger.trace("当前请求的视频路径为："+filePath);
		File file = new File(filePath);
		if (file.exists()&&file.isFile()) {
			InputStream inputStream = new FileInputStream(file);
			ServletOutputStream outputStream = response.getOutputStream();

			try {
				int length = -1;
				byte[] buffer = new byte[1024];
				while ((length=inputStream.read(buffer, 0, 1024))!=-1) {
					outputStream.write(buffer,0,length);
				}
				
				inputStream.close();
				outputStream.flush();
				outputStream.close();
				inputStream = null;
				outputStream = null;
				logger.info("视频写入完毕"); 
			} catch (Exception e) {
				logger.info("非正常写入视频资源");
				e.printStackTrace();
			}finally{
				logger.info("关闭视频资源");
				try {
					if(inputStream!=null){
						inputStream.close();
					}
					if (outputStream!=null) {
						outputStream.close();
					}
				}finally{
					inputStream = null;
					outputStream = null;
				}
			}
		}
	}
}
