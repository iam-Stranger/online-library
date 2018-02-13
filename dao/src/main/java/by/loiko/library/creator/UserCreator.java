package by.loiko.library.creator;

import by.loiko.library.entity.User;
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
public class UserCreator {
    private static Logger logger = LogManager.getLogger();

    private static final String USER_ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String ROLE_ID = "role_id";
    private static final String DELETED = "deleted";

    public User createUser(ResultSet resultSet) throws DAOException {
        List<String> columnList = null;
        User user = new User();

        try {
            columnList = getColumnsNames(resultSet);

            for (String column : columnList) {

                switch (column) {
                    case USER_ID:
                        user.setId(resultSet.getLong(USER_ID));
                        break;
                    case LOGIN:
                        user.setLogin(resultSet.getString(LOGIN));
                        break;
                    case PASSWORD:
                       // user.setPassword(resultSet.getString(PASSWORD));
                        break;
                    case EMAIL:
                        user.setEmail(resultSet.getString(EMAIL));
                        break;
                    case FIRST_NAME:
                        user.setFirstName(resultSet.getString(FIRST_NAME));
                        break;
                    case LAST_NAME:
                        user.setLastName(resultSet.getString(LAST_NAME));
                        break;
                    case ROLE_ID:
                        user.setRoleId(resultSet.getInt(ROLE_ID));
                        break;
                    case DELETED:
                        user.setIsDeleted(resultSet.getBoolean(DELETED));
                        break;
                    default:
                        logger.log(Level.DEBUG, column);
                }

            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in createUserOrder method", e);
        }

        return user;
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
