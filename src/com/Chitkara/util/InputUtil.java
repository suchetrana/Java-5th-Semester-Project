package com.Chitkara.util;

import com.Chitkara.dto.AdminDTO;
import com.Chitkara.dto.EmployeeDTO;
import com.Chitkara.exceptions.InvalidDetailsException;

import java.time.LocalDate;
import java.util.Scanner;

public class InputUtil {
    private InputUtil() {}

    // Admin input methods
    public static String acceptAdminUsername(Scanner scanner) {
        System.out.print("Enter admin username: ");
        return scanner.nextLine().trim();
    }

    public static String acceptAdminPassword(Scanner scanner) {
        System.out.print("Enter admin password: ");
        return scanner.nextLine().trim();
    }

    // Employee input methods
    public static int acceptMenuOption(Scanner scanner) {
        System.out.println("\nMenu:");
        System.out.println("1. Add Employee");
        System.out.println("2. Find Employee");
        System.out.println("3. Update Employee Email");
        System.out.println("4. Delete Employee");
        System.out.println("5. List All Employees");
        System.out.println("0. Return to main menu");
        System.out.print("Select option: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid option: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static EmployeeDTO acceptEmployeeDetailsToSave(Scanner scanner) {
        scanner.nextLine();
        EmployeeDTO employee = new EmployeeDTO();

        System.out.print("Enter First Name: ");
        employee.setFirstName(scanner.nextLine().trim());

        System.out.print("Enter Last Name: ");
        employee.setLastName(scanner.nextLine().trim());

        System.out.print("Enter Gender (M/F): ");
        employee.setGender(Enum.valueOf(com.Chitkara.enums.Gender.class, scanner.nextLine().trim().toUpperCase()));

        System.out.print("Enter City: ");
        employee.setCity(scanner.nextLine().trim());

        System.out.print("Enter State: ");
        employee.setState(scanner.nextLine().trim());

        System.out.print("Enter Mobile Number: ");
        employee.setMobileNumber(scanner.nextLine().trim());

        System.out.print("Enter Email ID: ");
        employee.setEmailId(scanner.nextLine().trim());

        System.out.print("Enter Employee Birth Date (YYYY-MM-DD): ");
        employee.setEmployeeBirthDate(LocalDate.parse(scanner.nextLine().trim()));

        System.out.print("Enter Salary: ");
        double inputSalary = Double.parseDouble(scanner.nextLine().trim());
        if (inputSalary < 0) {
            throw new InvalidDetailsException("Salary cannot be negative.");
        }
        employee.setSalary(inputSalary);

        return employee;
    }

    public static int acceptEmployeeIdToOperate(Scanner scanner) {
        System.out.print("Enter Employee Unique ID: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid Employee Unique ID: ");
            scanner.next();
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public static String acceptEmployeeEmailToUpdate(Scanner scanner) {
        System.out.print("Enter new Email ID: ");
        return scanner.nextLine().trim();
    }

    public static boolean wantToContinue(Scanner scanner) {
        System.out.print("Do you want to continue? (Y/N): ");
        String input = scanner.nextLine().trim().toUpperCase();
        return input.equals("Y");
    }
    public static AdminDTO acceptAdminDetailsToCreate(Scanner scanner) {
        scanner.nextLine();
        AdminDTO admin = new AdminDTO();
        System.out.print("Enter new admin username: ");
        admin.setUsername(scanner.nextLine().trim());
        System.out.print("Enter new admin password: ");
        admin.setPassword(scanner.nextLine().trim());
        return admin;
    }

    public static int acceptAdminIdToOperate(Scanner scanner) {
        System.out.print("Enter Admin ID: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid Admin ID: ");
            scanner.next();
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public static String acceptAdminUsernameToUpdate(Scanner scanner) {
        System.out.print("Enter new username: ");
        return scanner.nextLine().trim();
    }

    public static String acceptAdminPasswordToUpdate(Scanner scanner) {
        System.out.print("Enter new password: ");
        return scanner.nextLine().trim();
    }
}