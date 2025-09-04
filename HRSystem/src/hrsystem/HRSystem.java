package hrsystem;


import java.util.*;

public class HRSystem {

    public static void main(String[] args) {
        // Declarations
        String menuInput;
        int choice = 0;
        ArrayList<Employee> employees = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        // Header
        System.out.println("Employee Management System - 2025");
        System.out.println("*".repeat(30) + "\n");
        
        // Menu loop
        while (true) {
            System.out.println("Enter 1 to launch menu or any other key to exit.");
            menuInput = input.nextLine();

            if (menuInput.equals("1")) {
                // Menu options
                System.out.println("\n Menu Options: ");
                System.out.println("(1) Add a new employee");
                System.out.println("(2) Search for an employee");
                System.out.println("(3) Update employee salary");
                System.out.println("(4) Delete an employee");
                System.out.println("(5) Print employee report");
                System.out.println("(6) Exit Application");

                System.out.println("\n Please select a valid option (1-6)");

                try {
                    choice = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please select a valid option (1-6)");
                    continue;
                }

                // Menu code
                switch (choice) { //W3 Schools, n.d
                    case 1:
                        employees.add(Employee.capture(input));
                        break;
                    case 2:
                        Employee.searchEmployee(employees, input);
                        break;
                    case 3:
                        Employee.updateSalary(employees, input);
                        break;
                    case 4:
                        Employee.deleteEmployee(employees, input);
                        break;
                    case 5:
                        Employee.printEmployeeReport(employees);
                        break;
                    case 6:
                        exitApplication();
                        break;
                }
            }
        }
    }

    public static void exitApplication() {
        System.out.println("Exiting application...");
        System.exit(0);
    }
}