package com.argo.db.tools;

import com.google.common.base.Objects;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yaming_deng on 2014/10/11.
 */
public class TableColumn {

    private String table_name;
    private String column_name;
    private String column_comment;
    private String column_type;
    private String column_key;
    private String data_type;
    private boolean nullable = true;
    private String column_default;
    private Integer maxLength;

    public TableColumn(ResultSet resultSet) throws SQLException {
        this.table_name = resultSet.getString(1);
        this.column_name = resultSet.getString(2);
        this.column_comment = resultSet.getString(3);
        this.column_type = resultSet.getString(4);
        this.column_key = resultSet.getString(5);
        this.data_type = resultSet.getString(6);
        this.nullable = "YES".equalsIgnoreCase(resultSet.getString(7));
        this.column_default = resultSet.getString(8);
        this.maxLength = resultSet.getInt(9);
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getColumn_comment() {
        return column_comment;
    }

    public void setColumn_comment(String column_comment) {
        this.column_comment = column_comment;
    }

    public String getColumn_type() {
        return column_type;
    }

    public void setColumn_type(String column_type) {
        this.column_type = column_type;
    }

    public String getColumn_key() {
        return column_key;
    }

    public void setColumn_key(String column_key) {
        this.column_key = column_key;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getColumn_default() {
        return column_default;
    }

    public void setColumn_default(String column_default) {
        this.column_default = column_default;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("table_name", table_name)
                .add("column_name", column_name)
                .add("column_comment", column_comment)
                .add("column_type", column_type)
                .add("column_key", column_key)
                .add("data_type", data_type)
                .toString();
    }
}
