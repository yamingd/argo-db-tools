package com.argo.db.tools;

import java.util.List;

/**
 * Created by yamingd on 9/17/15.
 */
public class ProjectModuleInfo {

    private String ns;
    private String db;
    private List<String> tables;
    private List<String> ref;

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public List<String> getRef() {
        return ref;
    }

    public void setRef(List<String> ref) {
        this.ref = ref;
    }
}
