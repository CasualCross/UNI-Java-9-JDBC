package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeData
{
    private static final String URL = "jdbc:postgresql://localhost:5432/employee_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void createEmployee(Employee employee)
    {
        String sql = "INSERT INTO employee (name, position, salary, hire_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setDate(4, new java.sql.Date(employee.getHireDate().getTime()));
            preparedStatement.executeUpdate();
            System.out.println("Employee created successfully.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeById(int id)
    {
        String sql = "SELECT * FROM employee WHERE id = " + id;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                return new Employee
                (
                        //resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salary"),
                        resultSet.getDate("hire_date")
                );
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getAllEmployees()
    {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            while (resultSet.next())
            {
                employees.add(new Employee
                (
                        //resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salary"),
                        resultSet.getDate("hire_date")
                ));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return employees;
    }

    public void updateEmployee(Employee employee)
    {
        String sql = "UPDATE employee SET name = ?, position = ?, salary = ?, hire_date = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setDate(4, new java.sql.Date(employee.getHireDate().getTime()));
            preparedStatement.setInt(5, employee.getId());
            preparedStatement.executeUpdate();
            System.out.println("Employee updated successfully.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id)
    {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Employee deleted successfully.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
