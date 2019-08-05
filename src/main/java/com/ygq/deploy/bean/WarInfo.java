package com.ygq.deploy.bean;

import org.apache.commons.lang3.StringUtils;

public class WarInfo {

    private String name;
    
    private String deployState;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the deployState
     */
    public String getDeployState() {
        return deployState;
    }

    /**
     * @param deployState the deployState to set
     */
    public void setDeployState(String deployState) {
        this.deployState = deployState;
    }
    
    public String getFileName() {
        if (StringUtils.isNotEmpty(this.name)) {
            return name + ".war";
        }
        return null;
    }
    
}
