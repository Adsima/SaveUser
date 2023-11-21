package dao;

import exception.DaoException;
import model.User;

import java.sql.*;

public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
    }

    private static String SAVE_USER_SQL = """
            INSERT INTO users_servlet (first_name, last_name, date_birth, country, gender)
            VALUES (?, ?, ?, ?, ?);
            """;

    private Connection getConnection() throws SQLException {
        return ConnectionManager.getConnection();
    }

    public void saveUser(User user) {
        try (var connection = getConnection();
        var ps = connection.prepareStatement(SAVE_USER_SQL)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setDate(3, Date.valueOf(user.getDateOfBirth()));
            ps.setString(4, user.getCountry());
            ps.setString(5, user.getGender());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
