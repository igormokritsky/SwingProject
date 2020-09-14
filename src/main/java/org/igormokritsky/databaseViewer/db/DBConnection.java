package org.igormokritsky.databaseViewer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String userName = "root";
    private static final String password = "Jummetmokai2145";
    private static final String connectionURL =
            "jdbc:mysql://localhost:3306/migrate_schema?useUnicode=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionURL, userName, password);
    }


}
