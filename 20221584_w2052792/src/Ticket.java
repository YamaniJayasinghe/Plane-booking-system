import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private String row;
    private int seat;
    private double price;
    private Person person;

    //Constructor
    public Ticket(String row , int seat,double price,Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    //Getters and setters
    public String getRow(){
        return row;
    }
    public void setRow(String row){
        this.row = row;
    }

    public int getSeat(){
        return seat;
    }
    public void setSeat(int Seat){
        this.seat = seat;
    }

    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public String getPersonName(){
        return person.getName();
    }
    public String getPersonSurname(){
        return person.getSurname();
    }
    public String getPersonEmail(){
        return person.getEmail();
    }
    public void setPerson(Person person){
        this.person = person;
    }

    //Method to print the ticket information
    public void print_ticket(){
        System.out.println("Name: "+person.getName());
        System.out.println("Surname: "+person.getSurname());
        System.out.println("Email: "+person.getEmail());
        System.out.println("Row number: "+row);
        System.out.println("Seat number: "+seat);
        System.out.println("Price: "+price);
    }

    //Method to create a text file and save the ticket information
    public void save(Ticket ticket){
        //Run txt file save
        try {
            String fileName =  ticket.getRow().toUpperCase() + "_" + ticket.getSeat() + ".txt";
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("Name: " + ticket.getPersonName() + "\n");
            bufferedWriter.write("Surname: " + ticket.getPersonSurname() + "\n");
            bufferedWriter.write("Email: " + ticket.getPersonEmail() + "\n");
            bufferedWriter.write("Row number: " + ticket.getRow() + "\n");
            bufferedWriter.write("Seat number: " + ticket.getSeat() + "\n");
            bufferedWriter.write("Price: " + ticket.getPrice() + "\n");

            bufferedWriter.close();
            System.out.println("Ticket information updated to text file: "+fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
