package com.hurricane.app.cartoon.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hurricane.app.cartoon.pojo.Element;
import com.hurricane.app.cartoon.pojo.TreeElement;

@Controller
@RequestMapping("/file")
public class FileListController {
	
	private static Logger logger = LoggerFactory.getLogger(FileListController.class);
	@Value("${baseDir}")
	private String baseDir;
	
	@RequestMapping("listFile")
	@ResponseBody
	public Object getFileAndDir(@RequestParam(defaultValue="") String id) {
		logger.debug("获取到的扫描根路径为："+baseDir);
		
		//获取根路径下的文件和文件夹
		logger.trace("当前路径为："+(id.equals("")?baseDir:baseDir+"/"+id));
		File file = new File(id.equals("")?baseDir:baseDir+"/"+id);
		List<TreeElement> elements = new ArrayList<>();
		if (file.exists()&&file.isDirectory()) {
			String[] list = file.list();
			List<String> fds = new ArrayList<>();
			logger.trace("扫描到的元素为："+list);
			fds.addAll(Arrays.asList(list));
			Collections.sort(fds);
			for (String string : fds) {
				TreeElement element = new TreeElement();
				element.setText(string);
				element.setId(id.equals("")?string:id+"/"+string);
				File file2 = new File(baseDir+"/"+element.getId());
				if (file2.exists()&&file2.isDirectory()) {
					element.setState(TreeElement.STATE_CLOSED);
				}
				elements.add(element);
			}
		}
		return elements;
	}
}
