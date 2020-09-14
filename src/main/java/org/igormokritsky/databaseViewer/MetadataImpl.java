package org.igormokritsky.databaseViewer;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.igormokritsky.databaseViewer.elements.ButtonElement;
import org.igormokritsky.databaseViewer.elements.FieldElement;
import org.igormokritsky.databaseViewer.db.DBConnection;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class MetadataImpl {
    private static final String TABLES = "SHOW tables FROM migrate_schema";
    private static final String FIELDS = "SHOW FIELDS FROM ";
    private static final String SELECT = "SELECT * FROM ";


    public List<ButtonElement> showTables() throws SQLException {
        ResultSetHandler<List<ButtonElement>> resultSetHandler = new BeanListHandler<>(ButtonElement.class);
        QueryRunner runner = new QueryRunner();
        List<ButtonElement> result = runner.query(DBConnection.getConnection(), TABLES, resultSetHandler);
        for (ButtonElement buttonElements : result) {
            System.out.println(buttonElements.getTables_in_migrate_schema());
        }
        return result;
    }

    public List<FieldElement> showFields(String tableName) throws SQLException {
        ResultSetHandler<List<FieldElement>> resultSetHandler = new BeanListHandler<>(FieldElement.class);
        QueryRunner runner = new QueryRunner();
        List<FieldElement> result = runner.query(DBConnection.getConnection(), FIELDS + tableName, resultSetHandler);
        for (FieldElement fields : result) {
            System.out.println(fields.getField());
        }
        return result;
    }


    public List<Map<String, Object>> selectAll(String tableName) throws SQLException{
        MapListHandler mapListHandler = new MapListHandler();
        QueryRunner queryRunner = new QueryRunner();
        List<Map<String, Object>> list = queryRunner.query(DBConnection.getConnection(), SELECT + tableName, mapListHandler);
        System.out.println(list);
        return list;
    }


}


