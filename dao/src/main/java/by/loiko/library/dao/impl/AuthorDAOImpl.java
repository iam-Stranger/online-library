package by.loiko.library.dao.impl;

import by.loiko.library.creator.AuthorCreator;
import by.loiko.library.dao.AuthorDAO;
import by.loiko.library.entity.Author;
import by.loiko.library.dao.DAOException;
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
public class AuthorDAOImpl implements AuthorDAO {
    /* ResultSetCreator for Author objects */
    private AuthorCreator authorCreator = new AuthorCreator();

    private final static String FIND_ALL_NOT_EMPTY_AUTHORS = "SELECT author_id AS id, author.name, count(*) FROM book_authors INNER JOIN author WHERE author_id = id GROUP BY author_id ORDER BY author.name";
    private final static String FIND_AUTHORS_BY_BOOK_ID = "SELECT a.id, a.name, a.deleted FROM book_authors ba INNER JOIN author a ON ba.author_id = a.id WHERE book_id = ? AND deleted = 0 ORDER BY a.name";
    /* MySQL query find all authors which not deleted  */
    private static final String FIND_ALL_AUTHORS = "SELECT * FROM author WHERE deleted = 0 ORDER BY name";
    /* MySQL query find ALL authors (and deleted) */
    private static final String FIND_ALL_AUTHORS_ABS = "SELECT * FROM author ORDER BY name";
    private static final String FIND_AUTHORS_BY_ID = "SELECT * FROM author WHERE id = ? ORDER BY name";
    private final static String ADD_NEW_AUTHOR = "INSERT INTO author (name) VALUE (?)";
    private final static String UPDATE_AUTHOR = "UPDATE author SET name = ? , deleted = ? WHERE id = ?";
    /* MySQL query change status author to deleted */
    private final static String DELETE_AUTHOR = "UPDATE author SET deleted = '1' WHERE id = ?";


    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#deleteEntityById(long)
     */
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
            throw new DAOException("Error in delAuthor method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#addNewEntity(by.loiko.library.entity.Entity)
     */
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
            throw new DAOException("Error in AddAuthor method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#updateEntity(by.loiko.library.entity.Entity)
     */
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
            throw new DAOException("Error in updateAuthor method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#findEntityById(long)
     */
    @Override
    public Author findEntityById(long id) throws DAOException {
        Author author = new Author();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_AUTHORS_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                author = authorCreator.createAuthor(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findGenreById method: " + e.getMessage(), e);
        }
        return author;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#findAllEntities()
     */
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
                authorList.add(authorCreator.createAuthor(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllAuthors method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
        return authorList;
    }

    /* (non-Javadoc)
     *  Not used
     * @see by.loiko.library.dao.AbstractDAO#findEntitiesByArrayOfId(java.util.List)
     */
    @Override
    public List<Author> findEntitiesByArrayOfId(List<Long> idList) throws DAOException {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AuthorDAO#findAuthorsByBookId(long)
     */
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
                authorList.add(authorCreator.createAuthor(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAuthorByBookId method: " + e.getMessage(), e);
        }

        return authorList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AuthorDAO#findAllAuthorsAbs()
     */
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
                authorList.add(authorCreator.createAuthor(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllAuthors method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
        return authorList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AuthorDAO#findAllNotEmptyAuthors()
     */
    @Override
    public List<Author> findAllNotEmptyAuthors() throws DAOException {
        List<Author> authorList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        Statement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_NOT_EMPTY_AUTHORS);

            while (resultSet.next()) {
                authorList.add(authorCreator.createAuthor(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllNotEmptyAuthors method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
        return authorList;
    }
}
