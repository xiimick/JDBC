package org.example.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class EmployeeDAO {
    private DatabaseConnector dbConnector;

    public EmployeeDAO() {
        this.dbConnector = new DatabaseConnector();
    }

    public void addEmployee(String name, int age, String position, float salary) {
        String sql = "INSERT INTO employees (name, age, position, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, position);
            pstmt.setFloat(4, salary);
            pstmt.executeUpdate();
            System.out.println("Employee added: " + name + ", Age: " + age + ", Position: " + position + ", Salary: " + salary);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(int id, String name, int age, String position, float salary) {
        String sql = "UPDATE employees SET name = ?, age = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, position);
            pstmt.setFloat(4, salary);
            pstmt.setInt(5, id);
            int updatedRows = pstmt.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Employee updated: ID " + id);
            } else {
                System.out.println("Employee not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int deletedRows = pstmt.executeUpdate();
            if (deletedRows > 0) {
                System.out.println("Employee deleted with ID: " + id);
            } else {
                System.out.println("Employee not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getEmployee(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Position: " + rs.getString("position"));
                System.out.println("Salary: " + rs.getFloat("salary"));
            } else {
                System.out.println("Employee not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}