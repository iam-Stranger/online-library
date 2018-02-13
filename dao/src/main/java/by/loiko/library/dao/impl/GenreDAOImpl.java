package by.loiko.library.dao.impl;

import by.loiko.library.creator.GenreCreator;
import by.loiko.library.dao.GenreDAO;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.DAOException;
import by.loiko.library.pool.ConnectionPool;
import by.loiko.library.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/***
 Author: Aliaksei Loika
 Date: 31.01.2018
 ***/
public class GenreDAOImpl implements GenreDAO {
    /* ResultSetCreator for Genre objects */
    private GenreCreator genreCreator = new GenreCreator();

    /* MySQL query find all Genres which not deleted  */
    private final static String FIND_ALL_GENRES = "SELECT * FROM genre WHERE deleted = 0 ORDER BY type";
    /* MySQL query find ALL Genres (and deleted) */
    private final static String FIND_ALL_GENRES_ABS = "SELECT * FROM genre ORDER BY type";
    private final static String FIND_GENRE_BY_ID = "SELECT * FROM genre WHERE id = ?";
    private final static String ADD_NEW_GENRE = "INSERT INTO genre (type) VALUE (?)";
    private final static String UPDATE_GENRE = "UPDATE genre SET type = ? , deleted = ? WHERE id = ?";
    /* MySQL query change status Genre to deleted */
    private final static String DELETE_GENRE = "UPDATE genre SET deleted = '1' WHERE id = ?";
    private final static String FIND_ALL_NOT_EMPTY_GENRES = "SELECT genre_id as id, genre.type, count(*) FROM book_genres INNER JOIN genre WHERE genre_id =id GROUP BY genre_id ";
    private final static String FIND_GENRES_BY_BOOK_ID = "SELECT g.id, g.type, g.deleted FROM book_genres bg INNER JOIN genre g ON bg.genre_id = g.id WHERE book_id = ? AND deleted = 0";

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#deleteEntityById(long)
     */
    @Override
    public void deleteEntityById(long id) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(DELETE_GENRE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in delGenre method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#addNewEntity(by.loiko.library.entity.Entity)
     */
    @Override
    public void addNewEntity(Genre genre) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(ADD_NEW_GENRE);
            statement.setString(1, genre.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in AddGenre method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#updateEntity(by.loiko.library.entity.Entity)
     */
    @Override
    public void updateEntity(Genre genre) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(UPDATE_GENRE);
            statement.setString(1, genre.getType());
            statement.setBoolean(2, genre.getIsDeleted());
            statement.setLong(3, genre.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in updateGenre method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#findEntityById(long)
     */
    @Override
    public Genre findEntityById(long id) throws DAOException {
        Genre genre = new Genre();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_GENRE_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                genre = genreCreator.createGenre(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findGenreById method: " + e.getMessage(), e);
        }
        return genre;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#findAllEntities()
     */
    @Override
    public List<Genre> findAllEntities() throws DAOException {
        List<Genre> genreList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        Statement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_GENRES);

            while (resultSet.next()) {
                genreList.add(genreCreator.createGenre(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllGenres method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
        return genreList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#findEntitiesByArrayOfId(java.util.List)
     */
    @Override
    public List<Genre> findEntitiesByArrayOfId(List<Long> idList) throws DAOException {

        return null;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.GenreDAO#findGenresByBookId(long)
     */
    @Override
    public List<Genre> findGenresByBookId(long id) throws DAOException {
        List<Genre> genreList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_GENRES_BY_BOOK_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                genreList.add(genreCreator.createGenre(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findGenresByBookId method: " + e.getMessage(), e);
        }
        return genreList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.GenreDAO#findAllGenresAbs()
     */
    @Override
    public List<Genre> findAllGenresAbs() throws DAOException {
        List<Genre> genreList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        Statement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_GENRES_ABS);

            while (resultSet.next()) {
                genreList.add(genreCreator.createGenre(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllGenres method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
        return genreList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.GenreDAO#findAllNotEmptyGenres()
     */
    @Override
    public List<Genre> findAllNotEmptyGenres() throws DAOException {
        List<Genre> genreList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        Statement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_NOT_EMPTY_GENRES);

            while (resultSet.next()) {
                genreList.add(genreCreator.createGenre(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllNotEmpty method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
        return genreList;
    }

}
