package com.argo.db.tools;

import com.google.common.base.Objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yaming_deng on 2014/10/11.
 */
public class Table{

    private String table_name;
    private String table_comment;

    private List<TableColumn> columnList;

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_comment() {
        return table_comment;
    }

    public void setTable_comment(String table_comment) {
        this.table_comment = table_comment;
    }

    public List<TableColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<TableColumn> columnList) {
        this.columnList = columnList;
    }

    public Table(ResultSet rs) throws SQLException {
        this.table_name = rs.getString(1);
        this.table_comment = rs.getString(2);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("table_name", table_name)
                .add("table_comment", table_comment)
                .toString();
    }
}
