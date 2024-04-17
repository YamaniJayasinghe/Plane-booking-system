import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Options {
    //Initializing instance variables 
    private int row_num;
    private int count = 0;
    double t_price = 0;

    //Initializing the array
    String[][] nestedArray = new String [4][];

    /*Initializing the seats
    creating nested arrays for relevant row sizes and appending zeros' to display seating plan
     */
    public  void in_seat() {
        //Initializing array
        nestedArray[0] = new String[14];
        nestedArray[1] = new String[12];
        nestedArray[2] = new String[12];
        nestedArray[3] = new String[14];

        //Appending array to zero
        for (int i = 0; i < nestedArray.length; i++) {
            for (int j = 0; j < nestedArray[i].length; j++) {
                nestedArray[i][j] = "O";
            }
        }
        for (String[] strings : nestedArray) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println(" ");
        }
    }

//==============================================================================================================

    //Tracking the length of the array
    public void updateCount() {
        count++;
    }
    public void decreaseCount(){
        count--;
    }
    public int getCount() {
        return count;
    }

//==============================================================================================================

    /*Creating method for buying seats
    @param valid_letter is to check the validity of the letter,
    valid_seat is to check the validity of seat number,
    row_letter to take the row letter user inputs,
    seat_num is to take the seat number user inputs.
     */
    public void buy_seat(Ticket[] ticketArray , int count){
        boolean valid_letter = false;
        boolean valid_seat = false;
        String row_letter = "";
        int seat_num = 0;

        //Create scanner object
        Scanner input = new Scanner(System.in);

        //Requesting personal information
        System.out.println("Enter your name: ");
        String Name = input.next();
        System.out.println("Enter your surname: ");
        String Surname = input.next();
        System.out.println("Enter your email: ");
        String Email = input.next();

        //Checking validation of the row letter user inputs
        while(!valid_letter){
            try{
                System.out.println("Enter the row letter you prefer: ");
                row_letter = input.next();
                char firstChar = row_letter.charAt(0);
                if (!((firstChar >= 'a' && firstChar <= 'd') || (firstChar >= 'A' && firstChar <= 'D'))){
                    System.out.println("Enter a valid row letter between 'A' and 'D'");
                }
                else{
                    valid_letter = true;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid row letter");
                input.next();
            }
        }

        //Checking the validation of the seat number user inputs
        while(!valid_seat){
            try{
                System.out.println("Enter the seat number you prefer: ");
                seat_num = input.nextInt();
                if (row_letter.equalsIgnoreCase("A") || row_letter.equalsIgnoreCase("D")) {
                    if (seat_num < 1 || seat_num > 14) {
                        System.out.println("Enter a valid seat number between 1-14");
                    } else {
                        valid_seat = true;
                    }
                } else if (row_letter.equalsIgnoreCase("B") || row_letter.equalsIgnoreCase("C")) {
                    if (seat_num < 1 || seat_num > 12) {
                        System.out.println("Enter a valid seat number between 1-12");
                    } else {
                        valid_seat = true;
                    }
                } else {
                    System.out.println("Enter a valid row letter (A, B, C, or D)");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Enter a valid seat number");
                input.next();
            }
        }

        //Calculating the total price for the relevant seats
        if(seat_num<6){
            t_price = 200.00;
        } else if (seat_num<10) {
            t_price = 150.00;
        } else if (seat_num<14) {
            t_price = 180.00;
        }

        int Aseat_num = seat_num-1;

        System.out.println("-------------------------------------------------");
        System.out.println("You selected: "+row_letter+seat_num);
        System.out.println("-------------------------------------------------");
        if (row_letter.equalsIgnoreCase("A")){
            row_num = 0;
        }else if (row_letter.equalsIgnoreCase("B")){
            row_num = 1;
        }else if (row_letter.equalsIgnoreCase("C")){
            row_num = 2;
        }else if (row_letter.equalsIgnoreCase("D")){
            row_num = 3;
            }
        //Printing X instead of 0 for the booked seat to display
        if (nestedArray[row_num][Aseat_num].equals("X")){
            System.out.println("Seat is already booked");
        }
        else{
            boolean seat_confirm = true;
            //Validating the confirmation for booking the seat
            while(seat_confirm){
                System.out.println("Would you like to confirm your booking:");
                String confirm = input.next() ;
                try{
                    if(confirm.equalsIgnoreCase("Yes")){
                        System.out.println("-------------------------------------------------");
                        System.out.println("Your booking is confirm");
                        System.out.println("Have a safe flight!");
                        System.out.println("-------------------------------------------------");
                        nestedArray[row_num][Aseat_num] = "X";

                        Person person = new Person(Name, Surname, Email);
                        Ticket ticket = new Ticket(row_letter,  seat_num,t_price,person);

                        ticketArray[getCount()] = ticket;
                        updateCount();
                        seat_confirm = false;
                        //Calls the save method
                        ticket.save(ticket);
                    }
                    else if (confirm.equalsIgnoreCase("No")) {
                        System.out.println("-------------------------------------------------");
                        System.out.println("Your booking is cancelled");
                        System.out.println("-------------------------------------------------");
                        return;
                    }
                    else{
                        System.out.println("Enter a valid option(Yes/No)");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Enter a valid option");
                }
            }
        }
        //Displaying the seat position user booked
        for (int i=0; i<nestedArray.length ; i++){
            for(int j =0;j<nestedArray[i].length;j++){
                System.out.print(nestedArray[i][j]+" ");
            }
            System.out.println(" ");
        }
    }



//======================================================================================================================

    /*Creating method to cancel the seat
    @param valid_cancel_letter to check the validation of the row letter user inputs
    valid_cancel_num to check the validation of the seat number user inputs
    cancel_letter to store the row letter user inputs
    cancel_number to store the seat number user inputs
     */
    public void cancel_seat(Ticket[] ticketArray ){
        boolean valid_cancel_letter = false;
        boolean valid_cancel_num = false;
        String cancel_letter = "";
        int cancel_number = 0;

        //Create scanner object
        Scanner input = new Scanner(System.in);

        //Check the validation of the row letter user inputs
        while(!valid_cancel_letter){
            try{
                System.out.println("Enter the row letter: ");
                cancel_letter = input.next();
                char firstChar = cancel_letter.charAt(0);
                if (!((firstChar >= 'a' && firstChar <= 'd') || (firstChar >= 'A' && firstChar <= 'D'))){
                    System.out.println("Enter a valid row letter between 'A' and 'D'");
                }
                else{
                    valid_cancel_letter = true;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid row letter");
                input.next();
            }
        }

        //Check the validation of the seat number user inputs
        while(!valid_cancel_num){
            try{
                System.out.println("Enter the seat number: ");
                cancel_number = input.nextInt();

                if (cancel_letter.equalsIgnoreCase("A") || cancel_letter.equalsIgnoreCase("D")) {
                    if (cancel_number < 1 || cancel_number > 14) {
                        System.out.println("Enter a valid seat number between 1-14");
                    } else {
                        valid_cancel_num = true;
                    }
                } else if (cancel_letter.equalsIgnoreCase("B") || cancel_letter.equalsIgnoreCase("C")) {
                    if (cancel_number < 1 || cancel_number > 12) {
                        System.out.println("Enter a valid seat number between 1-12");
                    } else {
                        valid_cancel_num = true;
                    }
                } else {
                    System.out.println("Enter a valid row letter (A, B, C, or D)");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Enter a valid seat number");
                input.next();
            }
        }
        int cancel_num = cancel_number-1;


        if (cancel_letter.equalsIgnoreCase("A")){
            row_num = 0;
        } else if (cancel_letter.equalsIgnoreCase("B")) {
            row_num = 1;
        }else if (cancel_letter.equalsIgnoreCase("C")) {
            row_num = 2;
        }else if (cancel_letter.equalsIgnoreCase("D")) {
            row_num = 3;
        }

        //Calculating the total price when a booking is canceled
        if(cancel_number<6){
            t_price = -200.00;
        } else if (cancel_number<10) {
            t_price = -150.00;
        } else if (cancel_number<14) {
            t_price = -180.00;}


        if (nestedArray[row_num][cancel_num].equals("X")){
            boolean ticketFound = false;
            for (int i = 0; i < getCount(); i++) {
                if (ticketArray[i] == null) {
                    continue;
                }
                if (ticketArray[i].getRow().equalsIgnoreCase(cancel_letter) && ticketArray[i].getSeat() == cancel_number) {
                    ticketFound = true;
                    for (int j = i; j < ticketArray.length - 1; j++) {
                        ticketArray[j] = ticketArray[j + 1];
                    }
                    ticketArray[ticketArray.length - 1] = null;
                    decreaseCount();
                    //Printing the text file has been deleted for the cancelled booking
                    String fileName = cancel_letter + "_" + cancel_number + ".txt";

                    File fileToDelete = new File(fileName);
                    if (fileToDelete.delete()) {
                        System.out.println(fileName + " has been deleted successfully.");
                    } else {
                        System.out.println("Failed to delete file " + fileName);
                    }
                    break;
                }

            }

            if (!ticketFound) {
                System.out.println("Ticket not found.");
                return; // Exit method if ticket not found
            }
            nestedArray[row_num][cancel_num] = "O"; // Mark the seat as available
            System.out.println("Your booking is cancelled.");
        } else {
            System.out.println("Seat is not booked.");
        }
    }

//======================================================================================================================

    /*Creates a method to find the first available seat
    Iterates through the loop and search if the seat is booked or not booked
    */
    public void find_first_available(){
        for (int i = 0; i < nestedArray.length; i++) {
            for (int j = 0; j < nestedArray[i].length; j++) {
                if (nestedArray[i][j].equals("O")){
                    int row=i+1;
                    int seat=j+1;
                    System.out.println("-------------------------------------------------");
                    System.out.println("Row letter  :"+row);
                    System.out.println("Seat number :"+seat);
                    System.out.println("-------------------------------------------------");
                    return;
                }
            }
        }
    }

//======================================================================================================================

    /*Creates a method to show the seating plan
    Here the booked seats are shown with X and available seats are shows with O
     */
    public void show_seating_plan(){
        for (int i = 0; i < nestedArray.length; i++) {
            for (int j = 0; j < nestedArray[i].length; j++) {
                System.out.print(nestedArray[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

//======================================================================================================================

    /*Creates a method to print the ticket information
    Print the ticket information and the total sales
     */
    public void print_ticket_info(Ticket[] ticketArray) {
        double totalSales = 0.0;
        for (int i = 0; i < ticketArray.length; i++) {
            if (ticketArray[i] != null) {
                System.out.println("Ticket " + (i + 1) + ":");
                ticketArray[i].print_ticket();
                System.out.println();
                totalSales += ticketArray[i].getPrice();
                System.out.println("-------------------------------------------------");
            }
        }
        System.out.println("Total sales: $" + totalSales);
    }
//======================================================================================================================

    /*Creates a method to search the ticket
    Search the ticket and print the information if the seat is booked
    If the seat is available print the relevant message
     */
    public void search_seat(Ticket[] ticketArray){
        boolean search_valid_letter = false;
        boolean search_valid_number = false;
        String search_letter = "";
        int search_number = 0;

        //Creates scanner object
        Scanner input = new Scanner(System.in);
        //Validates the row letter user inputs
        while(!search_valid_letter){
            try{
                System.out.println("Enter the row letter: ");
                search_letter = input.next();
                char firstChar = search_letter.charAt(0);
                if (!((firstChar >= 'a' && firstChar <= 'd') || (firstChar >= 'A' && firstChar <= 'D'))){
                    System.out.println("Enter a valid row letter between 'A' and 'D'");
                }
                else{
                    search_valid_letter = true;
                }
            }
            catch (InputMismatchException e){
                    System.out.println("Enter a valid seat letter");
                    input.next();
            }
        }

        //Validates the seat number user inputs
        while(!search_valid_number){
            try{
                System.out.println("Enter the seat number: ");
                search_number = input.nextInt();
                if (search_letter.equalsIgnoreCase("A") || search_letter.equalsIgnoreCase("D")) {
                    if (search_number < 1 || search_number > 14) {
                        System.out.println("Enter a valid seat number between 1-14");
                    } else {
                        search_valid_number = true;
                    }
                }
                else if (search_letter.equalsIgnoreCase("B") || search_letter.equalsIgnoreCase("C")) {
                    if (search_number < 1 || search_number > 12) {
                        System.out.println("Enter a valid seat number between 1-12");
                    } else {
                        search_valid_number = true;
                    }
                }
                else {
                    System.out.println("Enter a valid row letter (A, B, C, or D)");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Enter a valid seat number");
                input.next();
            }
        }
        //Checking for the availability of the seats
        for (int i = 0; i < ticketArray.length; i++) {
            if (ticketArray[i] != null) {
                if (ticketArray[i].getRow().equalsIgnoreCase(search_letter) && ticketArray[i].getSeat() == search_number) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("This seat is booked");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Ticket info ");
                    ticketArray[i].print_ticket();
                    System.out.println();
                    break;
                }
            }
            else{
                System.out.println("-------------------------------------------------");
                System.out.println("This seat is available");
                System.out.println("-------------------------------------------------");
                break;
            }
        }
    }
}

