package hrsystem;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

public class EmployeeTest {

    @Test
    public void testCapture() {
        // Arrange
        String input = "1\nJohn Doe\nBSc Computer Science\nDeveloper\n50000\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Act
        Employee employee = Employee.capture(scanner);

        // Assert
        assertNotNull(employee);
        assertEquals(1, employee.getEmployeeID());
        assertEquals("John Doe", employee.getEmployeeName());
        assertEquals("BSc Computer Science", employee.getQualification());
        assertEquals("Developer", employee.getPosition());
        assertEquals(50000, employee.getAnnualSalary(), 0.01);
    }

    @Test
    public void testSearchEmployee_Found() {
        // Arrange
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", "MBA", "Manager", 60000));

        String input = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Capture console output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Act
        Employee.searchEmployee(employees, scanner);

        // Assert
        String consoleOutput = output.toString();
        assertTrue(consoleOutput.contains("Alice"));
        assertTrue(consoleOutput.contains("MBA"));
        assertTrue(consoleOutput.contains("Manager"));
        assertTrue(consoleOutput.contains("60,000.00")); // ✅ Correct formatting
    }

    @Test
    public void testSearchEmployee_NotFound() {
        // Arrange
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", "MBA", "Manager", 60000));

        String input = "99\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Act
        Employee.searchEmployee(employees, scanner);

        // Assert
        String consoleOutput = output.toString();
        assertTrue(consoleOutput.contains("not found"));
    }

    @Test
    public void testUpdateSalary() {
        // Arrange
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Bob", "BCom", "Analyst", 30000));

        String input = "1\n45000\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Act
        Employee.updateSalary(employees, scanner);

        // Assert
        assertEquals(45000, employees.get(0).getAnnualSalary(), 0.01);
    }

    @Test
    public void testDeleteEmployee_Confirmed() {
        // Arrange
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Charlie", "PhD", "Scientist", 70000));

        String input = "1\ny\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Act
        Employee.deleteEmployee(employees, scanner);

        // Assert
        assertTrue(employees.isEmpty(), "Employee should be deleted");
    }

    @Test
    public void testDeleteEmployee_Cancelled() {
        // Arrange
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Charlie", "PhD", "Scientist", 70000));

        String input = "1\nn\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Act
        Employee.deleteEmployee(employees, scanner);

        // Assert
        assertFalse(employees.isEmpty(), "Employee should not be deleted when cancelled");
    }

    @Test
    public void testPrintEmployeeReport() {
        // Arrange
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", "MBA", "Manager", 60000));
        employees.add(new Employee(2, "Bob", "BCom", "Analyst", 40000));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Act
        Employee.printEmployeeReport(employees);

        // Assert
        String consoleOutput = output.toString();
        assertTrue(consoleOutput.contains("Alice"));
        assertTrue(consoleOutput.contains("Bob"));
        assertTrue(consoleOutput.contains("Total Employees: 2"));
        assertTrue(consoleOutput.contains("100,000.00"));
    }

    @Test
    public void testPrintEmployeeReport_Empty() {
        // Arrange
        ArrayList<Employee> employees = new ArrayList<>();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Act
        Employee.printEmployeeReport(employees);

        // Assert
        String consoleOutput = output.toString();
        assertTrue(consoleOutput.contains("No employees found"));
    }

    @Test
    public void testGetEmployeeID() {
        Employee e = new Employee(10, "Test", "BSc", "Dev", 50000);
        assertEquals(10, e.getEmployeeID());
    }

    @Test
    public void testGetEmployeeName() {
        Employee e = new Employee(10, "Test", "BSc", "Dev", 50000);
        assertEquals("Test", e.getEmployeeName());
    }

    @Test
    public void testGetQualification() {
        Employee e = new Employee(10, "Test", "BSc", "Dev", 50000);
        assertEquals("BSc", e.getQualification());
    }

    @Test
    public void testGetPosition() {
        Employee e = new Employee(10, "Test", "BSc", "Dev", 50000);
        assertEquals("Dev", e.getPosition());
    }

    @Test
    public void testGetAnnualSalary() {
        Employee e = new Employee(10, "Test", "BSc", "Dev", 50000);
        assertEquals(50000, e.getAnnualSalary(), 0.01);
    }
}
