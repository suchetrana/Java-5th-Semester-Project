package com.Chitkara;

import com.Chitkara.dto.EmployeeDTO;
import com.Chitkara.dto.AdminDTO;
import com.Chitkara.exceptions.AdminNotFoundException;
import com.Chitkara.exceptions.DuplicateEmployeeException;
import com.Chitkara.exceptions.EmployeeNotFoundException;
import com.Chitkara.impl.EmployeeServiceImpl;
import com.Chitkara.impl.AdminServiceImpl;
import com.Chitkara.services.EmployeeService;
import com.Chitkara.services.AdminService;
import com.Chitkara.util.InputUtil;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    private EmployeeService employeeService;
    private AdminService adminService;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            adminService = new AdminServiceImpl();

            System.out.println("==== Admin Login ====");
            String username = InputUtil.acceptAdminUsername(scanner);
            String password = InputUtil.acceptAdminPassword(scanner);

            Optional<AdminDTO> admin = adminService.login(username, password);
            if (admin.isEmpty()) {
                System.out.println("Invalid admin credentials. Exiting.");
                return;
            }
            AdminDTO currentAdmin = admin.get();
            System.out.println("Admin login successful. Welcome, " + currentAdmin.getUsername() + "!");

            employeeService = new EmployeeServiceImpl();

            boolean running = true;

            while (running) {
                System.out.println("\nMain Menu:");
                System.out.println("1. Manage Employees");
                System.out.println("2. Manage Admins");
                System.out.println("0. Exit");
                System.out.print("Select option: ");
                int mainOption = scanner.nextInt();
                scanner.nextLine();

                switch (mainOption) {
                    case 1:
                        runEmployeeMenu(scanner);
                        break;
                    case 2:
                        runAdminMenu(scanner, currentAdmin);
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option entered.");
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void runEmployeeMenu(Scanner scanner) throws DuplicateEmployeeException, EmployeeNotFoundException {
        boolean empRunning = true;
        while (empRunning) {
            int menuOption = InputUtil.acceptMenuOption(scanner);

            switch (menuOption) {
                case 1:
                    EmployeeDTO employeeDTO = InputUtil.acceptEmployeeDetailsToSave(scanner);
                    employeeService.saveEmployee(employeeDTO);
                    System.out.println("Employee has been created successfully.");
                    System.out.println("Employee Unique ID (employee_id): " + employeeDTO.getEmployeeId());
                    break;
                case 2:
                    int employeeId = InputUtil.acceptEmployeeIdToOperate(scanner);
                    employeeDTO = employeeService.findEmployee(employeeId);
                    System.out.println("Employee has been fetched successfully.");
                    System.out.println(employeeDTO);
                    break;
                case 3:
                    employeeId = InputUtil.acceptEmployeeIdToOperate(scanner);
                    String email = InputUtil.acceptEmployeeEmailToUpdate(scanner);
                    employeeService.updateEmployeeDetails(employeeId, email);
                    System.out.println("Employee details have been updated successfully.");
                    break;
                case 4:
                    employeeId = InputUtil.acceptEmployeeIdToOperate(scanner);
                    employeeService.deleteEmployee(employeeId);
                    System.out.println("Employee has been deleted successfully.");
                    break;
                case 5:
                    List<EmployeeDTO> employeeDTOList = employeeService.findAllEmployee();
                    System.out.println("There are " + employeeDTOList.size() + " employees.");
                    employeeDTOList.forEach(System.out::println);
                    break;
                case 0:
                    empRunning = false;
                    break;
                default:
                    System.out.println("Invalid option entered.");
            }
        }
    }

    private void runAdminMenu(Scanner scanner, AdminDTO currentAdmin) throws AdminNotFoundException {
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\nAdmin Management Menu:");
            System.out.println("1. Add Admin");
            System.out.println("2. Update Admin");
            System.out.println("3. Delete Admin");
            System.out.println("4. Show Current Admin");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select option: ");
            int adminOption = scanner.nextInt();
            scanner.nextLine();

            switch (adminOption) {
                case 1:
                    AdminDTO newAdmin = InputUtil.acceptAdminDetailsToCreate(scanner);
                    adminService.createAdmin(newAdmin);
                    System.out.println("Admin added successfully.");
                    break;
                case 2:
                    System.out.println("Update current admin only.");
                    String newUsername = InputUtil.acceptAdminUsernameToUpdate(scanner);
                    String newPassword = InputUtil.acceptAdminPasswordToUpdate(scanner);
                    currentAdmin.setUsername(newUsername);
                    currentAdmin.setPassword(newPassword);
                    adminService.updateAdmin(currentAdmin);
                    System.out.println("Admin updated successfully.");
                    break;
                case 3:
                    System.out.println("Delete current admin only.");
                    adminService.deleteAdmin(currentAdmin.getAdminId());
                    System.out.println("Admin deleted successfully. Exiting...");
                    adminRunning = false;
                    System.exit(0); // terminate program after deleting current admin
                    break;
                case 4:
                    System.out.println("Current Admin: " + currentAdmin);
                    break;
                case 0:
                    adminRunning = false;
                    break;
                default:
                    System.out.println("Invalid option entered.");
            }
        }
    }
}