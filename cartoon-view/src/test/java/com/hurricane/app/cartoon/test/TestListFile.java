package com.hurricane.app.cartoon.test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hurricane.app.cartoon.controller.ImageController;

public class TestListFile {
	private static Logger logger = LoggerFactory.getLogger(TestListFile.class);
	@Test
	public void testListImgs() {
		String fileName = "Downloads/漫画/Hentai Manga/[Clone人間] ヨメホとツマホ/002.jpg";
		String baseDir = "E:/";
		List<String> fileNameList = new ArrayList<>();
		if (fileName!=null&&(!fileName.equals(""))) {
			String absoluteFilePath = baseDir+"/"+fileName;
			logger.debug("点击的文件绝对路径为:"+absoluteFilePath);
			absoluteFilePath = absoluteFilePath.substring(0, absoluteFilePath.lastIndexOf("/"));
			logger.debug("截取文件名之后的路径为:{}",absoluteFilePath);
			logger.info("应该为true: "+"aaa.jpg".endsWith(".jpg"));
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
				fileNameList.addAll(Arrays.asList(fileNames));
			}
		}
		Collections.sort(fileNameList);
		fileNameList.add(fileNameList.indexOf(fileName)+"");
//		logger.info("获取到的索引位置为："+fileNameList.get(fileNameList.size()-2));
//		logger.info("获取到的索引位置为："+fileNameList.get(fileNameList.size()-1));
		logger.info("获取到的文件总数为："+fileNameList.size()+"");
	}
	@Test
	public void testSubString() {
		String fileName = "aaa.jpg";
		logger.info(fileName.indexOf(".")+"");
		logger.info(fileName.substring(fileName.indexOf("."), fileName.length()));
	}
}
