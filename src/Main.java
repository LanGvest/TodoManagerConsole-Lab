import java.util.Scanner;

public class Main {
    public static TodoManager todoManager;

    public static void main(String[] args) {
        clearConsole();

        System.out.println("Welcome to the TODO Manager!");
        System.out.println("To continue, enter your email.");

        System.out.print("Email: ");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.next();
        Auth.getInstance().setEmail(email);

        clearConsole();

        System.out.println(Color.GREEN + "Successfully authorized!" + Color.RESET);

        todoManager = new TodoManager().init();
        showMenu();
    }

    public static void showMenu() {
        Scanner scanner;

        System.out.println("\nChoose what you want to do:");
        System.out.println(Color.CYAN + "1" + Color.RESET + " - display all TODO records");
        System.out.println(Color.CYAN + "2" + Color.RESET + " - display all done TODO records");
        System.out.println(Color.CYAN + "3" + Color.RESET + " - display all not done TODO records");
        System.out.println(Color.CYAN + "4" + Color.RESET + " - add new TODO record");
        System.out.println(Color.CYAN + "5" + Color.RESET + " - delete TODO record");
        System.out.println(Color.CYAN + "6" + Color.RESET + " - delete all TODO records");
        System.out.println(Color.CYAN + "7" + Color.RESET + " - mark TODO record as done");
        System.out.println(Color.CYAN + "8" + Color.RESET + " - mark TODO record as not done");
        System.out.println(Color.CYAN + "9" + Color.RESET + " - exit");

        System.out.print("Menu item: ");
        scanner = new Scanner(System.in);
        int menuItem = scanner.nextInt();

        clearConsole();

        if(menuItem == 1) {
            todoManager.displayAllRecords();
        } else if(menuItem == 2) {
            todoManager.displayDoneRecords();
        } else if(menuItem == 3) {
            todoManager.displayNotDoneRecords();
        } else  if(menuItem == 4) {
            System.out.print("TODO text: ");
            scanner = new Scanner(System.in, "Cp866");
            String text = scanner.nextLine();

            System.out.println("Choose TODO priority (or any another symbol to skip):");
            System.out.println(Color.CYAN + "h" + Color.RESET + " - " + Color.RED + "high" + Color.RESET);
            System.out.println(Color.CYAN + "s" + Color.RESET + " - " + Color.YELLOW + "standard" + Color.RESET);
            System.out.println(Color.CYAN + "l" + Color.RESET + " - " + Color.GREEN + "low" + Color.RESET);

            System.out.print("TODO priority: ");
            scanner = new Scanner(System.in);
            String priorityLabel = scanner.next();

            todoManager.addRecord(text, priorityLabel);
        } else if(menuItem == 5) {
            System.out.print("TODO id: ");
            scanner = new Scanner(System.in);
            int id = scanner.nextInt();

            todoManager.deleteRecord(id);
        } else if(menuItem == 6) {
            System.out.println("Please enter your email again to confirm this action.");
            System.out.print("Email: ");
            scanner = new Scanner(System.in);
            String confirmEmail = scanner.next();

            if(confirmEmail.equals(Auth.getInstance().getEmail())) todoManager.deleteAllRecords();
            else System.out.println(Color.RED + "Error:" + Color.RESET + " Access denied!");
        } else if(menuItem == 7) {
            System.out.print("TODO id: ");
            scanner = new Scanner(System.in);
            int id = scanner.nextInt();

            todoManager.markRecordAsDone(id);
        } else if(menuItem == 8) {
            System.out.print("TODO id: ");
            scanner = new Scanner(System.in);
            int id = scanner.nextInt();

            todoManager.markRecordAsNotDone(id);
        } else if(menuItem == 9) {
            todoManager.saveRecords();
            System.out.println("Exit the program.");
        } else {
            System.out.println(Color.RED + "Error:" + Color.RESET + " No such menu item!");
        }

        if(menuItem != 9) showMenu();
    }

    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}