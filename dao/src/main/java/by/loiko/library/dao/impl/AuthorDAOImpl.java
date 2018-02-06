package by.loiko.library.dao.impl;

import by.loiko.library.dao.AuthorDAO;
import by.loiko.library.entity.Author;
import by.loiko.library.exception.DAOException;
import by.loiko.library.pool.ConnectionPool;
import by.loiko.library.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public class AuthorDAOImpl implements AuthorDAO {
    private static Logger logger = LogManager.getLogger();

    private static final String FIND_ALL_AUTHORS = "SELECT * FROM author WHERE deleted = 0 ORDER BY name";
    private static final String FIND_ALL_AUTHORS_ABS = "SELECT * FROM author ORDER BY name";
    private static final String FIND_AUTHOR_BY_ID = "SELECT * FROM author WHERE id = ?";
    private final static String ADD_NEW_AUTHOR = "INSERT INTO author (name) VALUE (?)";
    private final static String UPDATE_AUTHOR = "UPDATE author SET name = ? , deleted = ? WHERE id = ?";
    private final static String DELETE_AUTHOR = "UPDATE author SET deleted = '1' WHERE id = ?";

    private final static String FIND_AUTHORS_BY_BOOK_ID = "SELECT a.id, a.name, a.deleted FROM book_authors ba INNER JOIN author a ON ba.author_id = a.id WHERE book_id = ? AND deleted = 0";


    @Override
    public void deleteEntityById(long id) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(DELETE_AUTHOR);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in delAuthor method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    @Override
    public void addNewEntity(Author author) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(ADD_NEW_AUTHOR);
            statement.setString(1, author.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in AddAuthor method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    @Override
    public void updateEntity(Author author) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(UPDATE_AUTHOR);
            statement.setString(1, author.getName());
            statement.setBoolean(2, author.getIsDeleted());
            statement.setLong(3, author.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in updateAuthor method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    @Override
    public Author findEntityById(long id) throws DAOException {
        Author author = new Author();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_AUTHOR_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                author = buildAuthor(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findGenreById method: ", e);
        }
        return author;
    }

    @Override
    public List<Author> findAllEntities() throws DAOException {
        List<Author> authorList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        Statement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_AUTHORS);

            while (resultSet.next()) {
                authorList.add(buildAuthor(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllAuthors method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
        return authorList;
    }

    @Override
    public List<Author> findEntitiesByArrayOfId(List<Long> idList) throws DAOException {
        return null;
    }

    @Override
    public List<Author> findAuthorsByBookId(long id) throws DAOException {
        List<Author> authorList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_AUTHORS_BY_BOOK_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                authorList.add(buildAuthor(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in findAuthorByBookId method: ", e);
        }

        return authorList;
    }

    @Override
    public List<Author> findAllAuthorsAbs() throws DAOException {
        List<Author> authorList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        Statement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_AUTHORS_ABS);

            while (resultSet.next()) {
                authorList.add(buildAuthor(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllAuthors method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
        return authorList;
    }

    private Author buildAuthor(ResultSet resultSet) throws SQLException {
        Author author = new Author();

        author.setId(resultSet.getLong("id"));
        author.setName(resultSet.getString("name"));
        author.setIsDeleted(resultSet.getBoolean("deleted"));

        return author;
    }
}
