package by.loiko.library.creator;

import by.loiko.library.entity.Genre;
import by.loiko.library.exception.DAOException;
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
public class GenreCreator implements ResultSetCreator {
    private static Logger logger = LogManager.getLogger();

    private static final String GENRE_ID = "id";
    private static final String TYPE = "type";
    private static final String DELETED = "deleted";


    /**
     * Creates the genre.
     *
     * @param resultSet the result set
     * @return the genre
     * @throws DAOException the DAO exception
     */
    public Genre createGenre(ResultSet resultSet) throws DAOException {
        List<String> columnList = null;
        Genre genre = new Genre();

        try {
            columnList = getColumnsNames(resultSet);

            for (String column : columnList) {

                switch (column) {
                    case GENRE_ID:
                        genre.setId(resultSet.getLong(GENRE_ID));
                        break;
                    case TYPE:
                        genre.setType(resultSet.getString(TYPE));
                        break;
                    case DELETED:
                        genre.setIsDeleted(resultSet.getBoolean(DELETED));
                        break;
                    default:
                        logger.log(Level.DEBUG, column);
                }

            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in createBookOrder method", e);
        }

        return genre;
    }
}
