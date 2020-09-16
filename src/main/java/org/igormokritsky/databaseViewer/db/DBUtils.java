package org.igormokritsky.databaseViewer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private static final String userName = "root";
    private static final String password = "Jummetmokai2145";
    private static final String connectionURL =
            "jdbc:mysql://localhost:3306/migrate_schema?useUnicode=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private Connection connection;

    public Connection getConnection() throws SQLException {
        connection =  DriverManager.getConnection(connectionURL, userName, password);
        return connection;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
