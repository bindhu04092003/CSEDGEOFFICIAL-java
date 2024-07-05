import java.util.Scanner;

public class chatbot {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome! I am your chatbot.");
        System.out.println("Type 'open <application>' to open an application,");
        System.out.println("or type 'search <query>' to search the web.");
        System.out.println("Type 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.startsWith("open ")) {
                String appName = input.substring(5).trim();
                openApplication(appName);
            } else if (input.startsWith("search ")) {
                String query = input.substring(7).trim();
                searchWeb(query);
            } else {
                System.out.println("I'm sorry, I didn't understand that.");
            }
        }

        scanner.close();
    }

    private static void openApplication(String appName) {
        try {
            // Example: Opening Notepad on Windows
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                Runtime.getRuntime().exec("notepad");
            } else {
                // Add support for other applications and OS here
                System.out.println("Unsupported operation: opening " + appName);
            }
            System.out.println("Opening " + appName + "...");
        } catch (Exception e) {
            System.out.println("Error opening application: " + e.getMessage());
        }
    }

    private static void searchWeb(String query) {
        try {
            // Open default web browser with search query
            java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://www.google.com/search?q=" + query));
            System.out.println("Searching the web for: " + query);
        } catch (Exception e) {
            System.out.println("Error searching the web: " + e.getMessage());
        }
    }
}