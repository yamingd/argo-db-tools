package com.argo.db.tools;

import org.apache.commons.cli.CommandLine;

/**
 * Created by yamingd on 9/17/15.
 */
public class ExportParams {

    private String db;
    private String table;
    private String output;

    public ExportParams(CommandLine cl){
        if (cl.hasOption("d")){
            db = cl.getOptionValue("d");
        }
        if (cl.hasOption("t")){
            db = cl.getOptionValue("t");
        }
        if (cl.hasOption("o")){
            output = cl.getOptionValue("o");
        }
    }


    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
