package by.loiko.library.creator;

import by.loiko.library.entity.Author;
import by.loiko.library.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 12.02.2018
 ***/
public class AuthorCreator {
    private static Logger logger = LogManager.getLogger();

    private static final String AUTHOR_ID = "id";
    private static final String NAME = "name";
    private static final String DELETED = "deleted";


    public Author createAuthor(ResultSet resultSet) throws DAOException {
        List<String> columnList = null;
        Author author = new Author();

        try {
            columnList = getColumnsNames(resultSet);

            for (String column : columnList) {

                switch (column) {
                    case AUTHOR_ID:
                        author.setId(resultSet.getLong(AUTHOR_ID));
                        break;
                    case NAME:
                        author.setName(resultSet.getString(NAME));
                        break;
                    case DELETED:
                        author.setIsDeleted(resultSet.getBoolean(DELETED));
                        break;
                    default:
                        logger.log(Level.DEBUG, column);
                }

            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in createBookOrder method", e);
        }

        return author;
    }

    private List<String> getColumnsNames(ResultSet resultSet) throws SQLException {
        List<String> columnList = new ArrayList<>();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for (int i = 1; i < resultSetMetaData.getColumnCount() + 1; i++) {
            columnList.add(resultSetMetaData.getColumnLabel(i));
        }

        return columnList;
    }


}
