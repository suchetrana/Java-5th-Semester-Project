package com.Chitkara;

import com.Chitkara.dto.EmployeeDTO;
import com.Chitkara.impl.EmployeeServiceImpl;
import com.Chitkara.services.EmployeeService;
import com.Chitkara.util.InputUtil;

import java.util.List;
import java.util.Scanner;

public class Demo {
    private EmployeeService employeeService;

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.run(args);

    }

    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            employeeService = new EmployeeServiceImpl();
            do {
                System.out.println("Welcome to Employee Manage");
                int menuOption = InputUtil.acceptMenuOption(scanner);

                switch (menuOption) {
                    case 1:
                        EmployeeDTO employeeDTO = InputUtil.acceptEmployeeDetailsToSave(scanner);
                        employeeService.saveEmployee(employeeDTO);
                        System.out.println("Employee has been successfully.");
                        break;
                    case 2:
                        int employeeId = InputUtil.acceptEmployeeIdToOperate(scanner);
                        employeeDTO = employeeService.findEmployee(employeeId);
                        System.out.println("Employee has been fetched successfully.");
                        System.out.println(employeeDTO);
                        break;
                    case 3:
                        employeeId = InputUtil.acceptEmployeeIdToOperate(scanner);
                        String email = InputUtil.acceptEmployeeIdToUpdate(scanner);
                        employeeService.updateEmployeeDetails(employeeId, email);
                        System.out.println("Employee details  have been updated successfully.");
                        break;
                    case 4:
                        employeeId = InputUtil.acceptEmployeeIdToOperate(scanner);
                        employeeService.deleteEmployee(employeeId);
                        System.out.println("Employee has been deleted successfully.");
                        break;
                    case 5:
                        List<EmployeeDTO> employeeDTOList = employeeService.findAllEmployee();
                        System.out.println("There are " + employeeDTOList.size() + " emplooyees.");
                        employeeDTOList.forEach(System.out::println);
                        break;
                    default:
                        System.out.println("Invalid option entered.");
                }
            } while (InputUtil.wantToContinue(scanner));

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
