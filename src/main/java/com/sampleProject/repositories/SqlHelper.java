package com.sampleProject.repositories;

import com.sampleProject.contracts.ISqlHelper;
import org.springframework.stereotype.Repository;

import java.sql.*;

import static java.lang.Class.forName;

@Repository
final class SqlHelper implements ISqlHelper {

    private static Connection GetConnection() throws ClassNotFoundException, SQLException {
        final String sqlUserName = "MySqlAuthentication";
        final String sqlPassword = "123456";
        final String sqlServerHostName = "localHost";
        final String sqlServerPort = "1433";
        final String sqlServerDatabaseName = "SpringProject";

        String connectionString = "jdbc:sqlserver://" + sqlServerHostName + ":" + sqlServerPort +
                    ";databaseName=" + sqlServerDatabaseName + ";user=" + sqlUserName +
                    ";password=" + sqlPassword;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection ret = DriverManager.getConnection(connectionString);
        return ret;
    }

    @Override
    public ResultSet ExecuteSqlQuery(String query) throws SQLException, ClassNotFoundException {
        Connection connection = SqlHelper.GetConnection();
        Statement stmt = connection.createStatement();
        ResultSet ret = stmt.executeQuery(query);
        return ret;
    }
}