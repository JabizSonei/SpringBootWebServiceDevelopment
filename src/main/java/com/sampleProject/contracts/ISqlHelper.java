package com.sampleProject.contracts;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ISqlHelper {
    ResultSet ExecuteSqlQuery(String query) throws SQLException, ClassNotFoundException;
}
