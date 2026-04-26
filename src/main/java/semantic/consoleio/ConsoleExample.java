package semantic.consoleio;

import java.io.Console;
import java.util.Arrays;

public class ConsoleExample {
    static void main() {
        // printf(String fmt, Object... args)
        // readLine() -> String
        // readLine(String fmt, Object... args) -> String
        // readPassword() -> char[] // suppress echoing,
        // return a char array, so password can be overwritten, removing it from memory as soon as it is no longer needed
        // readPassword(String fmt, Object... args) -> char[]

        Console console = System.console();
        if (console == null) {
            System.out.println("No console: non-interactive mode!");
            System.exit(0);
        }
        System.out.print("Enter your username: ");
        String username = console.readLine();

        System.out.print("Enter your password: ");
        char[] password = console.readPassword();

        String passport = console.readLine("Enter your %d(th) passport number: ", 2);
        // system encoding
        System.out.println(System.getProperty("file.encoding"));

        System.out.println("Username: " + username + ", Password: " + Arrays.toString(password) + ", Passport: " + passport);
    }
}
