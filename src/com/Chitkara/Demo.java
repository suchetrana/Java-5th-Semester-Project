package com.Chitkara;

import com.Chitkara.dto.EmployeeDTO;
import com.Chitkara.util.InputUtil;

import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.run(args);

    }

    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.println("Welcome to Employee Manage");
                int menuOption = InputUtil.acceptMenuOption(scanner);

                switch (menuOption) {
                    case 1:
                        EmployeeDTO employeeDTO = InputUtil.acceptEmployeeDetailsToSave(scanner);

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
