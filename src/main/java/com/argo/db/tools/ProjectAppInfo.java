package com.argo.db.tools;

import java.util.List;

/**
 * Created by yamingd on 9/17/15.
 */
public class ProjectAppInfo {

    private String name;
    private List<String> modules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }

}
