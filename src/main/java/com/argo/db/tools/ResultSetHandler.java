package com.argo.db.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yamingd on 9/17/15.
 */
public interface ResultSetHandler {

    /**
     *
     * @param resultSet
     */
    void iterate(ResultSet resultSet) throws SQLException;

}
