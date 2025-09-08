package com.Chitkara.util;

import com.Chitkara.dto.EmployeeDTO;
import com.Chitkara.enums.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class InputUtil {
    private InputUtil() {

    }

    public static int acceptMenuOption(Scanner scanner) {
        System.out.println("Press 1 to add new Employee.");
        System.out.println("Press 2 to fetch Employee details.");
        System.out.println("Press 3 to update Employee details.");
        System.out.println("Press 4 to delete Employee details.");
        System.out.println("Press 5 to fetch all Employee.");
        int menuOption = scanner.nextInt();
        if (menuOption == 1 || menuOption == 2 || menuOption == 3 || menuOption == 4 || menuOption == 5) {
            return menuOption;
        } else {
            return acceptMenuOption(scanner);
        }
    }

    public static boolean wantToContinue(Scanner scanner) {
        System.out.println("Press Y to continue and N to exit.");
        char choice = scanner.next().toUpperCase().charAt(0);
        return 'Y' == choice;
    }

    public static EmployeeDTO acceptEmployeeDetailsToSave(Scanner scanner) {
        System.out.println("Enter id of employee:");
        int id = scanner.nextInt();
        System.out.println("Enter first name of employee:");
        String firstName = scanner.next();
        System.out.println("Enter last name of employee:");
        String lastName = scanner.next();
        System.out.println("Enter gender of employee:" + Arrays.asList(Gender.values()).toString());
        String gender = scanner.next().toUpperCase();
        System.out.println("Enter city of employee:");
        String city = scanner.next();
        System.out.println("Enter state of employee:");
        String state = scanner.next();
        System.out.println("Enter mobile number of employee:");
        String mobileNumber = scanner.next();
        System.out.println("Enter email id of employee:");
        String emailId = scanner.next();
        System.out.println("Enter id of employee:");
        int employeeId = scanner.nextInt();
        System.out.println("Enter birth date of employee: (dd-MM-yyyy)");
        String employeeBirthDate = scanner.next();

        try{
            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setId(id);
            employeeDTO.setFirstName(firstName);
            employeeDTO.setLastName(lastName);
            employeeDTO.setGender(Gender.valueOf(gender));
            employeeDTO.setCity(city);
            employeeDTO.setState(state);
            employeeDTO.setMobileNumber(mobileNumber);
            employeeDTO.setEmailId(emailId);
            employeeDTO.setEmployeeId(employeeId);
            employeeDTO.setEmployeeBirthDate(convertStringToDate(employeeBirthDate));

            return employeeDTO;
        } catch (Exception exception){
            System.out.println("Error: "+exception.getMessage());
            return acceptEmployeeDetailsToSave(scanner);
        }
    }
    public static String acceptEmployeeIdToUpdate(Scanner scanner) {
        System.out.println("Enter updated email of employee:");
        return scanner.next();
    }

    public static int acceptEmployeeIdToOperate(Scanner scanner) {
        System.out.println("Enter id of employee:");
        return scanner.nextInt();
    }

    public static LocalDate convertStringToDate(String stringDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(stringDate, format);
    }
}
