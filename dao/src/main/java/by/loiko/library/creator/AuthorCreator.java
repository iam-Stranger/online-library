package by.loiko.library.creator;

import by.loiko.library.entity.Author;
import by.loiko.library.dao.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/***
 Author: Aliaksei Loika
 Date: 12.02.2018
 ***/
public class AuthorCreator implements ResultSetCreator {

    private static Logger logger = LogManager.getLogger();

    private static final String AUTHOR_ID = "id";
    private static final String NAME = "name";
    private static final String DELETED = "deleted";


    /**
     * Creates the author.
     *
     * @param resultSet the result set
     * @return the author
     * @throws DAOException the DAO exception
     */
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

}
