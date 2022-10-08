package net.intelliboard.next.services.database;

import net.intelliboard.next.services.PropertiesGetValue;

import java.io.IOException;
import java.sql.*;

public class DataBaseConnectorService {

    PropertiesGetValue propertiesGetValue = new PropertiesGetValue();

    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String DB_USER = propertiesGetValue.getPropertyValue("db_user");
    private final String DB_PASSWORD = propertiesGetValue.getPropertyValue("db_user_password");
    private final String DB_HOST = propertiesGetValue.getPropertyValue("db_host");
    private final String DB_PORT = propertiesGetValue.getPropertyValue("db_port");

    private Statement statement;
    private Connection connection;

    public DataBaseConnectorService() throws IOException {
    }


    private Connection getConnection(String dataBaseName) {
        String DB_URL = String.format("jdbc:postgresql://%s:%s/%s", DB_HOST, DB_PORT, dataBaseName);
        Connection connection = null;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public ResultSet executeQuery(String sqlQuery, String dataBaseName) {

        connection = getConnection(dataBaseName);
        ResultSet rs = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void environmentCleanUp() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
