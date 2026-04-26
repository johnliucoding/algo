package semantic.consoleio;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ScannerExample {
    static void main() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        try (scanner) {
            System.out.print("Enter your nationality: ");
            String nationality = scanner.nextLine();
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            System.out.println("Your nationality is " + nationality + ", and your age is " + age);
        }
    }
}
