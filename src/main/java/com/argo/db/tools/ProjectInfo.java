package com.argo.db.tools;

import java.util.List;

/**
 * Created by yamingd on 9/17/15.
 */
public class ProjectInfo {

    private String name;
    private String company;
    private String title;
    private String output;
    private String dburl;
    private List<String> dbs;

    private List<ProjectModuleInfo> modules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<ProjectModuleInfo> getModules() {
        return modules;
    }

    public void setModules(List<ProjectModuleInfo> modules) {
        this.modules = modules;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

    public List<String> getDbs() {
        return dbs;
    }

    public void setDbs(List<String> dbs) {
        this.dbs = dbs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPackageName(){
        return String.format("com.%s.%s", company, name.toLowerCase());
    }

}
