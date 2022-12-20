package lk.ijse.dep9.app.dao.custom.impl;

import lk.ijse.dep9.app.dao.custom.UserDAO;
import lk.ijse.dep9.app.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private final Connection connection;
    public UserDAOImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public User save(User user)  {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO User (username, full_name, password) VALUES (?,?,?)");
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getFullName());
            statement.setString(3,user.getPassword());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE User SET full_name=?, password=? WHERE username=?");
            statement.setString(1,user.getFullName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String pk) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM User WHERE username=?");
            statement.setString(1,pk);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Optional<User> findById(String pk) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT full_name,username,password FROM User WHERE username=?");
            statement.setString(1,pk);
            ResultSet rst = statement.executeQuery();
            if (rst.next()){
                return Optional.of(new User(pk, rst.getString("full_name"),rst.getString("password")));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User");
            ResultSet rst = statement.executeQuery();
            while (rst.next()){
                userList.add(new User(rst.getString("username"),rst.getString("full_name"),rst.getString("password")));
            }
            return userList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(username) FROM User");
            ResultSet rst = statement.executeQuery();
            rst.next();
            return rst.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existById(String pk) {
        return findById(pk).isPresent();
    }
}
