package org.igormokritsky.DatabaseViewer;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.igormokritsky.DatabaseViewer.dao.ButtonElements;
import org.igormokritsky.DatabaseViewer.dao.Fields;
import org.igormokritsky.DatabaseViewer.db.DBConnection;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class DatabaseMetadata {
    private static final String TABLES = "SHOW tables FROM migrate_schema";
    private static final String FIELDS = "SHOW FIELDS FROM ";
    private static final String SELECT = "SELECT * FROM ";


    public List<ButtonElements> showTables() throws SQLException {
        ResultSetHandler<List<ButtonElements>> resultSetHandler = new BeanListHandler<>(ButtonElements.class);
        QueryRunner runner = new QueryRunner();
        List<ButtonElements> result = runner.query(DBConnection.getConnection(), TABLES, resultSetHandler);
        for (ButtonElements buttonElements : result) {
            System.out.println(buttonElements.getTables_in_migrate_schema());
        }
        return result;
    }

    public List<Fields> showFields(String tableName) throws SQLException {
        ResultSetHandler<List<Fields>> resultSetHandler = new BeanListHandler<>(Fields.class);
        QueryRunner runner = new QueryRunner();
        List<Fields> result = runner.query(DBConnection.getConnection(), FIELDS + tableName, resultSetHandler);
        for (Fields fields : result) {
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


