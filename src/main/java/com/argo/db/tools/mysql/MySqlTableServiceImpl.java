package com.argo.db.tools.mysql;

import com.argo.db.tools.*;
import net.sf.jxls.transformer.XLSTransformer;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yaming_deng on 2014/10/11.
 */
public class MySqlTableServiceImpl implements TableSchemaService {

    private Connection conn = null;

    @Override
    public void LoadDriver() {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
            System.err.println("Exception: " + ex.getMessage());
        }
    }

    @Override
    public Connection openConnection(String url) {
        try {
            LoadDriver();
            conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException ex) {
            // handle any errors
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

    /**
     * 查询
     * @param sql
     * @param setter
     * @param handler
     */
    protected void executeQuery(String sql, PreparedStatementSetter setter, ResultSetHandler handler){

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement(sql);
            setter.setValues(stmt);

            rs = stmt.executeQuery();
            handler.iterate(rs);
        }
        catch (SQLException ex){
            // handle any errors
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }


    @Override
    public List<Table> findAll(final String schemaName) {
        final List<Table> tableList = new ArrayList<Table>();
        String sql = "SELECT table_name, table_comment FROM INFORMATION_SCHEMA.tables t WHERE table_schema=?";

        this.executeQuery(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement) throws SQLException {
                statement.setString(1, schemaName);
            }
        }, new ResultSetHandler() {
            @Override
            public void iterate(ResultSet resultSet) throws SQLException {
                while (resultSet.next()){
                    tableList.add(new Table(resultSet));
                }
            }
        });

        return tableList;
    }

    @Override
    public List<TableColumn> findAllColumns(final String schemaName, final String tableName) {
        String sql = "select table_name,column_name,column_comment,column_type,column_key,data_type,IS_NULLABLE,COLUMN_DEFAULT,CHARACTER_MAXIMUM_LENGTH from INFORMATION_SCHEMA.COLUMNS where table_schema = ? and table_name = ?";
        final List<TableColumn> columnList = new ArrayList<TableColumn>();

        this.executeQuery(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement) throws SQLException {
                statement.setString(1, schemaName);
                statement.setString(2, tableName);
            }
        }, new ResultSetHandler() {
            @Override
            public void iterate(ResultSet resultSet) throws SQLException {
                while (resultSet.next()){
                    columnList.add(new TableColumn(resultSet));
                }
            }
        });

        return columnList;
    }

    @Override
    public void exportXls(String schemaName, String mode) throws Exception {
        String templateFileName = String.format("table-schema-%s.xls", mode);
        String destFileName = String.format("output/%s-%s.xls",schemaName, mode);

        List<Table> tableList = this.findAll(schemaName);
        for (Table table : tableList){
            List<TableColumn> columns = this.findAllColumns(schemaName, table.getTable_name());
            table.setColumnList(columns);
        }

        InputStream is = getClass().getClassLoader().getResourceAsStream(templateFileName);

        Map beans = new HashMap();
        beans.put("tables", tableList);
        XLSTransformer transformer = new XLSTransformer();
        org.apache.poi.ss.usermodel.Workbook workbook =  transformer.transformXLS(is, beans);
        OutputStream os = new BufferedOutputStream(new FileOutputStream(destFileName));
        workbook.write(os);
        is.close();
        os.flush();
        os.close();

    }

    @Override
    public void exportEntity(ExportParams params) throws Exception {



    }
}
