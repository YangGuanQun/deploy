package com.ygq.deploy.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ygq.deploy.service.FileService;

@Controller
@RequestMapping("logs")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    
    @Value("${jboss.logs.dir}")
    private String jbosslogsDir;
    
    @Resource
    private FileService fileService;
    
    @RequestMapping("")
    public String logs(ModelMap map) throws Exception {
        String cmd = "ls -1 " + jbosslogsDir;
        logger.info("exec:{}", cmd);
        Process proc = Runtime.getRuntime().exec(cmd);
        InputStream inputStream = proc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> logList = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            logList.add(line);
        }
        map.put("logList", logList);
        return "logs";
    }
    
    @RequestMapping("download")
    public void downloadLogs(HttpServletResponse rsp, String name) throws Exception {
        rsp.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes(), "ISO-8859-1"));
        fileService.write(rsp.getOutputStream(), jbosslogsDir + name);
    }

}
