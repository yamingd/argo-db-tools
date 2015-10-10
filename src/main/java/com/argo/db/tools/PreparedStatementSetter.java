package com.argo.db.tools;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by yamingd on 9/17/15.
 */
public interface PreparedStatementSetter {

    /**
     * 设置参数
     * @param statement
     */
    void setValues(PreparedStatement statement) throws SQLException;
}
