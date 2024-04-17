import java.util.InputMismatchException;
import java.util.Scanner;

public class w2052792_PlaneManagement {
    public static void main(String[] args) {
        //Creating scanner object
        Scanner input = new Scanner(System.in);
        //Initializing variables
        boolean start = true;
        String opt = null;
        int option = 0;
        int count =0;

        //Creating an array
        Ticket[] ticketArray = new Ticket[100];
        Ticket[] updatedTicketArray = new Ticket[100];

        //Welcome message
        System.out.println("==============================================================");
        System.out.println("Welcome to the Plane Management application");
        System.out.println("==============================================================");

        //Create option object and call the in_seat() method
        System.out.println("You can select options according to the fallowing seating plan");
        Options option1 = new Options();
        option1.in_seat();
        System.out.println("==============================================================");

        //Requesting whether user likes to run the program
        while (start) {
            System.out.print("Would you like to start(Y/N): ");
            opt = input.next();

            //If user doesn't want to start quites the program
            if (opt.equalsIgnoreCase("n")) {
                System.out.println("See you again later!!");
                System.exit(0);
            } else if (opt.equalsIgnoreCase("y")) {
                break;

            } else {
                System.out.println("Enter a valid input");
            }
        }

        //Runs the program if user wants to start
        while (opt.equalsIgnoreCase("y")) {
            boolean valid_input = false;
            System.out.println("**************************************************");
            System.out.println("*                  MENU OPTIONS                  *");
            System.out.println("**************************************************");
            System.out.println("     1) Buy a seat");
            System.out.println("     2) Cancel a seat");
            System.out.println("     3) Find first available seat");
            System.out.println("     4) Show seating plan");
            System.out.println("     5) Print tickets information and total sales");
            System.out.println("     6) Search ticket");
            System.out.println("     0) Quit");
            System.out.println("**************************************************");
            System.out.println();

            //Validate the option selected by user
            while (!valid_input) {
                try {
                    //Asking the user to input an option
                    System.out.print("Please select an option: ");
                    option = input.nextInt();
                    valid_input = true;

                } catch (InputMismatchException e) {
                    System.out.println("Enter a valid option");
                    input.next();
                }
            }

            //After input validation switch cases will execute
            switch (option) {
                case 0:
                    System.out.println("See you again later!!");
                    System.exit(0);
                case 1:
                    option1.buy_seat(ticketArray,count);
                    break;
                case 2:
                    option1.cancel_seat(ticketArray);
                    break;
                case 3:
                    option1.find_first_available();
                    break;
                case 4:
                    option1.show_seating_plan();
                    break;
                case 5:
                    option1.print_ticket_info(ticketArray );
                    break;
                case 6:
                    option1.search_seat(ticketArray);
                    break;
                default:
                    System.out.println("Enter a number in the range 0-6");
            }
        }
    }
}