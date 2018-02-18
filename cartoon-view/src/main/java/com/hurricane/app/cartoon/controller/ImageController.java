package com.hurricane.app.cartoon.controller;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/image")
public class ImageController {

	private static Logger logger = LoggerFactory.getLogger(ImageController.class);
	@Value("${baseDir}")
	private String baseDir;
	
	@RequestMapping(value="view")
//	@ResponseBody
	public void viewImage(@RequestParam String url,HttpServletResponse response) throws Exception {
		String filePath = baseDir + "/" + url;
		File file = new File(filePath);
		if (file.exists()&&file.isFile()) {
			InputStream inputStream = new FileInputStream(file);
			response.setContentType("image/jpeg");
//			response.setCharacterEncoding("UTF-8");
			ServletOutputStream outputStream = response.getOutputStream();
			
			
//			int length = -1;
//			byte[] buffer = new byte[1024];
//			while ((length=inputStream.read(buffer, 0, 1024))!=-1) {
//				buffer = Base64Utils.encode(buffer);
//				outputStream.write(buffer,0,length);
//			}
			
//			BufferedImage image = ImageIO.read(file);
//			ImageIO.write(image,"JPEG",response.getOutputStream());
			
			
			byte[] bytes = new byte[(int) file.length()];
			inputStream.read(bytes);
			outputStream.write(Base64Utils.encode(bytes));
			
			inputStream.close();
			outputStream.flush();
			outputStream.close();
		}
//		return null;
	}
	@RequestMapping("jumpToImgView")
	public Object jumpToImgView(@RequestParam(defaultValue="") String fileName,Model model) throws Exception {
		logger.debug("点击的文件："+fileName);
		model.addAttribute("imgId", fileName);
		return "image";
	}
	
	
	@RequestMapping("getImgs")
	@ResponseBody
	public Object getImgs(@RequestParam(name="id",defaultValue="") String fileName) {
		logger.debug("传递过来的文件名为："+fileName);
		List<String> fileNameList = new ArrayList<>();
		if (fileName!=null&&(!fileName.equals(""))) {
			String absoluteFilePath = baseDir+"/"+fileName;
			logger.debug("点击的文件绝对路径为:"+absoluteFilePath);
			absoluteFilePath = absoluteFilePath.substring(0, absoluteFilePath.lastIndexOf("/"));
			logger.debug("截取文件名之后的路径为:{}",absoluteFilePath);
			File file = new File(absoluteFilePath);
			if (file.exists()&&file.isDirectory()) {
				String[] fileNames = file.list(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						if (name.endsWith(".jpg")) {
							return true;
						}
						return false;
					}
				});
				String prefix = fileName.substring(0, fileName.lastIndexOf("/"));
				for (int i = 0; i < fileNames.length; i++) {
					fileNames[i] = prefix+"/"+fileNames[i];
				}
				logger.info("读取到的文件数量为："+fileNames.length);
				fileNameList.addAll(Arrays.asList(fileNames));
			}
		}
		Collections.sort(fileNameList);
		fileNameList.add(fileNameList.indexOf(fileName)+"");
		return fileNameList;
	}
}
