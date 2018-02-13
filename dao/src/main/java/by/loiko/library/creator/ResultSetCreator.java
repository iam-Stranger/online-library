package by.loiko.library.creator;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 13.02.2018
 ***/
public interface ResultSetCreator {
    /**
     * Gets the columns names.
     *
     * @param resultSet the result set
     * @return the array of columns name
     * @throws SQLException the SQL exception
     */
    default
        List<String> getColumnsNames(ResultSet resultSet) throws SQLException {
        List<String> columnList = new ArrayList<>();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for (int i = 1; i < resultSetMetaData.getColumnCount() + 1; i++) {
            columnList.add(resultSetMetaData.getColumnLabel(i));
        }

        return columnList;
    }
}
