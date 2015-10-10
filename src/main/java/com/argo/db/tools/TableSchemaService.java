package com.argo.db.tools;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Administrator on 2014/10/11.
 */
public interface TableSchemaService {

    /**
     *
     */
    void LoadDriver();

    /**
     *
     * @return
     */
    Connection openConnection(String url);
    /**
     *
     * @param schemaName
     * @return
     */
    List<Table> findAll(String schemaName);

    /**
     *
     * @param schemaName
     * @param tableName
     * @return
     */
    List<TableColumn> findAllColumns(String schemaName, String tableName);

    /**
     *
     * @param schemaName
     * @param mode
     * @throws Exception
     */
    void exportXls(String schemaName, String mode) throws Exception;

    /**
     *
     * @param params
     * @throws Exception
     */
    void exportEntity(ExportParams params) throws Exception;
}
