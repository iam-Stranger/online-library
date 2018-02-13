package by.loiko.library.creator;

import by.loiko.library.entity.Book;
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
public class BookCreator {
    private static Logger logger = LogManager.getLogger();

    private static final String BOOK_ID = "id";
    private static final String TITLE = "title";
    private static final String PUBLISH_YEAR = "publish_year";
    private static final String TOTAL_AMOUNT = "total_amount";
    private static final String REAL_AMOUNT = "real_amount";
    private static final String DELETED = "deleted";


    public Book createBook(ResultSet resultSet) throws DAOException {
        List<String> columnList = null;
        Book book = new Book();

        try {
            columnList = getColumnsNames(resultSet);

            for (String column : columnList) {

                switch (column) {
                    case BOOK_ID:
                        book.setId(resultSet.getLong(BOOK_ID));
                        break;
                    case TITLE:
                        book.setTitle(resultSet.getString(TITLE));
                        break;
                    case PUBLISH_YEAR:
                        book.setPublishYear(resultSet.getInt(PUBLISH_YEAR));
                        break;
                    case TOTAL_AMOUNT:
                        book.setTotalAmount(resultSet.getInt(TOTAL_AMOUNT));
                        break;
                    case REAL_AMOUNT:
                        book.setRealAmount(resultSet.getInt(REAL_AMOUNT));
                        break;
                    case DELETED:
                        book.setIsDeleted(resultSet.getBoolean(DELETED));
                        break;
                    default:
                        logger.log(Level.DEBUG, column);
                }

            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in createBookOrder method", e);
        }

        return book;
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
