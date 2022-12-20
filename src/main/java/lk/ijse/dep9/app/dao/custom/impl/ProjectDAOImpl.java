package lk.ijse.dep9.app.dao.custom.impl;

import lk.ijse.dep9.app.dao.custom.ProjectDAO;
import lk.ijse.dep9.app.entity.Project;
import lk.ijse.dep9.app.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectDAOImpl implements ProjectDAO {
    private final Connection connection;

    public ProjectDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Project save(Project project) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Project (name, username) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,project.getName());
            statement.setString(2,project.getUsername());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            project.setId(generatedKeys.getInt(1));
            return project;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Project project) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Project SET name=?, username=? WHERE id=?");
            statement.setString(1,project.getName());
            statement.setString(2,project.getUsername());
            statement.setInt(3,project.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer pk) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Project WHERE id=?");
            statement.setInt(1,pk);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Project> findById(Integer pk) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Project WHERE id=?");
            statement.setInt(1,pk);
            ResultSet rst = statement.executeQuery();
            if (rst.next()){
                return Optional.of(new Project(pk, rst.getString("name"),rst.getString("username")));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> findAll() {
        List<Project> projectList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Project");
            ResultSet rst = statement.executeQuery();
            while (rst.next()){
                projectList.add(new Project(rst.getInt("id"),rst.getString("name"),rst.getString("username")));
            }
            return projectList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(id) FROM Project");
            ResultSet rst = statement.executeQuery();
            rst.next();
            return rst.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existById(Integer pk) {
        return findById(pk).isPresent();
    }

    @Override
    public List<Project> findAllProjectByUsername(String username) {
        List<Project> projectList = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Project WHERE username=?");
            stm.setString(1,username);
            ResultSet rst = stm.executeQuery();
            while (rst.next()){
                projectList.add(new Project(rst.getInt("id"),rst.getString("name"),rst.getString("username")));
            }
            return projectList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
