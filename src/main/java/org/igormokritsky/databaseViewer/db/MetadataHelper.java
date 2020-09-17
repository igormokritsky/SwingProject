package org.igormokritsky.databaseViewer.db;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.igormokritsky.databaseViewer.elements.ButtonElement;
import org.igormokritsky.databaseViewer.elements.FieldElement;
import org.igormokritsky.databaseViewer.resultSet.TableResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class MetadataHelper {
    private static final String TABLES = "SHOW tables FROM migrate_schema";
    private static final String FIELDS = "SHOW FIELDS FROM ";
    private static final String SELECT = "SELECT * FROM ";
    private final QueryRunner runner = new QueryRunner();
    private final DBUtils dbUtils = new DBUtils();

    public List<ButtonElement> showTables() throws SQLException {
        try {
            ResultSetHandler<List<ButtonElement>> resultSetHandler = new TableResultSet();
            List<ButtonElement> result = runner.query(dbUtils.getConnection(), TABLES, resultSetHandler);
            for (ButtonElement buttonElements : result) {
                System.out.println(buttonElements.getTablesInMigrateSchema());
            }
            return result;

        } finally {
            dbUtils.close();
        }

    }

    public List<FieldElement> showFields(String tableName) throws SQLException {
        try {
            ResultSetHandler<List<FieldElement>> resultSetHandler = new BeanListHandler<>(FieldElement.class);
            List<FieldElement> result = runner.query(dbUtils.getConnection(), FIELDS + tableName, resultSetHandler);
            for (FieldElement fields : result) {
                System.out.println(fields);
            }
            return result;
        } finally {
            dbUtils.close();
        }
    }

    public List<Map<String, Object>> selectAll(String tableName) throws SQLException{
        try {
            MapListHandler mapListHandler = new MapListHandler();
            List<Map<String, Object>> list = runner.query(dbUtils.getConnection(), SELECT + tableName, mapListHandler);
            System.out.println(list);
            return list;
        } finally {
            dbUtils.close();
        }
    }

}


