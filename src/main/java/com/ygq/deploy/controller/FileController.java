package com.ygq.deploy.controller;


import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ygq.deploy.service.FileService;

@Controller
@ResponseBody 
@RequestMapping("file")
public class FileController {
    
    @Resource
    private FileService fileService;
	
	private Logger logger = LoggerFactory.getLogger(FileController.class);
	
    @Value("${jboss.deploys.dir}")
    private String jbossDeploysDir;

	@RequestMapping("upload")
	public void upload(MultipartFile file) throws IllegalStateException, IOException {
		String fileName = file.getOriginalFilename();
		File dest = new File(jbossDeploysDir + fileName);
		logger.info("create file:{}", dest.getAbsolutePath());
		if (dest.exists()) {
		    logger.info("删除原文件：{}", dest.getAbsolutePath());
		    dest.delete();
		}
		file.transferTo(dest);
	}
	
	@RequestMapping("download")
	public void download(HttpServletResponse rsp, String file) throws IOException {
		rsp.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getBytes(), "ISO-8859-1"));
		fileService.write(rsp.getOutputStream(), jbossDeploysDir + file);
	}
}
