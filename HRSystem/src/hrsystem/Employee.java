package hrsystem;
import java.util.*;

public class Employee extends HRSystem {
    
    // Data Members
    public int employeeID;
    public String employeeName;
    public String qualification;
    public String position;
    public double annualSalary;
    
    // Constructor
    public Employee() {
    }

    public Employee(int employeeID, String employeeName, String qualification, String position, double annualSalary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.qualification = qualification;
        this.position = position;
        this.annualSalary = annualSalary;
    }
    
    // Method to capture employee details
    public static Employee capture(Scanner input) {
        System.out.println("ADD NEW EMPLOYEE");
        System.out.println("*".repeat(20) + "\n");
        
        System.out.println("Enter the employee ID: ");
        int employeeID = Integer.parseInt(input.nextLine());
        System.out.println("Enter the employee name: ");
        String employeeName = input.nextLine();
        System.out.println("Enter the qualification: ");
        String qualification = input.nextLine();
        System.out.println("Enter the position: ");
        String position = input.nextLine();
        
        // Validate salary
        double annualSalary;
        while (true) {
            System.out.println("Enter the annual salary (ZAR): ");
            String salaryInput = input.nextLine();
            
            try {
                annualSalary = Double.parseDouble(salaryInput);
                if (annualSalary > 0) {
                    break;
                } else {
                    System.out.println("Salary must be greater than 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format! Please enter a valid number.");
            }
        }
        
        System.out.println("Employee added successfully!!!");
        
        // Create employee with the captured values
        Employee employee = new Employee(employeeID, employeeName, qualification, position, annualSalary);
        
        return employee;
    }
    
    // Method to search for an employee
    public static void searchEmployee(ArrayList<Employee> employees, Scanner input) {
        String employeeToSearch;
        System.out.println("Enter Employee ID to search: ");
        employeeToSearch = input.nextLine();
        System.out.println("*********EMPLOYEE INFORMATION******");
        
        boolean found = false;  
        
        for (Employee employee : employees) {
            if (employeeToSearch.equalsIgnoreCase(String.valueOf(employee.getEmployeeID()))) {
                System.out.println("Employee ID: " + employee.getEmployeeID());
                System.out.println("Employee Name: " + employee.getEmployeeName());
                System.out.println("Qualification: " + employee.getQualification()); 
                System.out.println("Position: " + employee.getPosition());
                System.out.println("Annual Salary (ZAR): " + String.format("%,.2f", employee.getAnnualSalary()));
                found = true;  
                break;  
            }
        }
        
        // If employee not found
        if (!found) {
            System.out.println("Employee with ID " + employeeToSearch + " not found.");
        }
    }
   
    // Method to update employee salary
    public static void updateSalary(ArrayList<Employee> employees, Scanner input) {
        System.out.println("Enter the employee ID to update salary: ");
        int employeeIdToUpdate = Integer.parseInt(input.nextLine());
        
        boolean found = false;
        
        for (Employee employee : employees) {
            if (employee.getEmployeeID() == employeeIdToUpdate) {
                // Validate new salary
                double newSalary;
                while (true) {
                    System.out.println("Enter the new annual salary (ZAR): ");
                    String salaryInput = input.nextLine();
                    
                    try {
                        newSalary = Double.parseDouble(salaryInput);
                        if (newSalary > 0) {
                            employee.annualSalary = newSalary;
                            System.out.println("Salary updated successfully!");
                            break;
                        } else {
                            System.out.println("Salary must be greater than 0!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid salary format! Please enter a valid number.");
                    }
                }
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Employee with ID " + employeeIdToUpdate + " not found.");
        }
    }
    
    // Method to delete an employee
    public static void deleteEmployee(ArrayList<Employee> employees, Scanner input) {
        System.out.println("Enter the employee ID to delete: ");
        int employeeIdToDelete = Integer.parseInt(input.nextLine());
        
        boolean found = false;
        
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if (employee.getEmployeeID() == employeeIdToDelete) {
                System.out.println("Are you sure you want to delete employee " + employeeIdToDelete + " from the system? Yes (y) to delete.");
                String confirmation = input.nextLine();
                
                if (confirmation.equalsIgnoreCase("y")) {
                    employees.remove(i);
                    System.out.println("---Employee with ID: " + employeeIdToDelete + " WAS deleted!");
                } else {
                    System.out.println("---Deletion cancelled.");
                }
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Employee with ID " + employeeIdToDelete + " not found.");
        }
    }
    
    // Method to print employee report
    public static void printEmployeeReport(ArrayList<Employee> employees) {
        System.out.println("Employee Report - 2025");
        System.out.println("*".repeat(30));
        
        if (employees.isEmpty()) {
            System.out.println("No employees found in the system.");
            return;
        }
        
        double totalSalary = 0;
        
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            System.out.println("Employee " + (i + 1));
            System.out.println("--- EMPLOYEE ID: " + employee.getEmployeeID());
            System.out.println("NAME: " + employee.getEmployeeName());
            System.out.println("QUALIFICATION: " + employee.getQualification());
            System.out.println("POSITION: " + employee.getPosition());
            System.out.println("ANNUAL SALARY (ZAR): " + String.format("%,.2f", employee.getAnnualSalary()));
            System.out.println("---");
            
            totalSalary += employee.getAnnualSalary();
        }
        
        // Display summary
        System.out.println("\nSUMMARY:");
        System.out.println("Total Employees: " + employees.size());
        System.out.println("Total Annual Salary Budget: ZAR " + String.format("%,.2f", totalSalary));
        System.out.println("Average Salary: ZAR " + String.format("%,.2f", (totalSalary / employees.size())));
    }
    
    // Getters
    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getQualification() {
        return qualification;
    }

    public String getPosition() {
        return position;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }
}