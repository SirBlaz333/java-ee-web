package com.arsenii;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    public static final String ADD_STUDENT = "INSERT INTO student (firstname, lastname, age, email, `group`, faculty) VALUES (?, ?, ?, ?, ?, ?);";
    public static final String GET_ALL_STUDENTS = "SELECT * FROM student;";
    private final DBManager dbManager;

    public StudentRepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void addStudent(Student student) {
        try (Connection connection = dbManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_STUDENT);
            int index = 0;
            preparedStatement.setString(++index, student.getFirstname());
            preparedStatement.setString(++index, student.getLastname());
            preparedStatement.setInt(++index, student.getAge());
            preparedStatement.setString(++index, student.getEmail());
            preparedStatement.setString(++index, student.getGroup());
            preparedStatement.setString(++index, student.getFaculty());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAllStudents() {
        try (Connection connection = dbManager.getConnection()) {
            List<Student> students = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STUDENTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(getStudent(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Student getStudent(ResultSet resultSet) throws SQLException {
        int index = 1;
        return new Student(
                resultSet.getString(++index),
                resultSet.getString(++index),
                resultSet.getInt(++index),
                resultSet.getString(++index),
                resultSet.getString(++index),
                resultSet.getString(++index)
        );
    }
}
