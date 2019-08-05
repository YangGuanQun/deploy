package com.ygq.deploy.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ygq.deploy.bean.WarInfo;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Value("${jboss.deploys.dir}")
    private String jbossDeploysDir;

    @RequestMapping("/")
    public String home(ModelMap map) throws Exception {
        String cmd = "ls -1 " + jbossDeploysDir;
        logger.info("exec:{}", cmd);
        //String cmd = "F:\\mock_exec.bat";
        Process proc = Runtime.getRuntime().exec(cmd);
        InputStream inputStream = proc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<WarInfo> warList = new ArrayList<>();
        List<String> fileList = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            fileList.add(line);
            if (line.endsWith(".war")) {
                WarInfo war = new WarInfo();
                war.setName(line.substring(0, line.lastIndexOf(".war")));
                warList.add(war);
            }
        }
        warList.stream().forEach(e -> {
            String warName = e.getName() + ".war";
            Optional<String> find = fileList.stream().filter(f -> {
                return f.startsWith(warName) && !f.equals(warName);
            }).findFirst();
            if (find.isPresent()) {
                e.setDeployState(find.get().substring(warName.length() + 1));
            }
        });
        
        map.put("warList", warList);
        return "index";
    }

}
