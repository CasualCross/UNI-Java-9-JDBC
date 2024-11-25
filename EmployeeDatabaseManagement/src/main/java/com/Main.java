package com;

import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        //Create a new Employee object and insert it into the database using createEmployee()
        EmployeeData employeeData = new EmployeeData();
        Employee newEmployee = new Employee("John Doe", "Software Engineer", 75000.0, new Date());
        employeeData.createEmployee(newEmployee);

        //Retrieve and display an employee by ID using getEmployeeById().
        Employee employee = employeeData.getEmployeeById(12);
        System.out.println("Retrieved Employee: " + employee);

        //Retrieve and display all employees using getAllEmployees().
        System.out.println("All Employees:");
        for (Employee emp : employeeData.getAllEmployees())
        {
            System.out.println(emp);
        }

        //Update an employee’s details and save them using updateEmployee()
        if (employee != null)
        {
            employee.setName("John Smith");
            employeeData.updateEmployee(employee);
        }

        //Delete an employee from the database using deleteEmployee().
        employeeData.deleteEmployee(1);
    }
}
