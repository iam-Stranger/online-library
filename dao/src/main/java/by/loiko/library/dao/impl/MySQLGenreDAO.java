package by.loiko.library.dao.impl;

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
public class MySQLGenreDAO implements GenreDAO {
    private final static String FIND_ALL_GENRES = "SELECT * FROM genre";
    private final static String FIND_GENRE_BY_ID = "SELECT * FROM genre WHERE id = ?";
    private final static String ADD_NEW_GENRE = "INSERT INTO genre (type) VALUE (?)";
    private final static String UPDATE_GENRE = "UPDATE genre SET type = ? , deleted = ? WHERE id = ?";
    private final static String DELETE_GENRE = "UPDATE genre SET deleted = '1' WHERE id = ?";

    private final static String FIND_GENRES_BY_BOOK_ID = "SELECT g.id, g.type, g.deleted FROM book_genres bg INNER JOIN genre g ON bg.genre_id = g.id WHERE book_id = ? AND deleted = 0";

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
            throw new DAOException("Error in delGenre method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    @Override
    public void addNewEntity(Genre genre) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try{
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(ADD_NEW_GENRE);
            statement.setString(1, genre.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in AddGenre method: ", e);
        }finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

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
            throw new DAOException("Error in updateGenre method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

    }

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
                genre = buildGenre(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findGenreById method: ", e);
        }
        return genre;
    }

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
                genreList.add(buildGenre(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllGenres method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
        return genreList;
    }

    @Override
    public List<Genre> findEntitiesByArrayOfId(List<Long> idList) throws DAOException {

        return null;
    }


    private Genre buildGenre(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre();

        genre.setId(resultSet.getLong("id"));
        genre.setType(resultSet.getString("type"));
        genre.setIsDeleted(resultSet.getBoolean("deleted"));

        return genre;
    }

    @Override
    public List<Genre> findGenresByBookId(long id) throws DAOException {
        List<Genre> genreList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try{
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_GENRES_BY_BOOK_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                genreList.add(buildGenre(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findGenresByBookId method: ", e);
        }
        return genreList;
    }
}
