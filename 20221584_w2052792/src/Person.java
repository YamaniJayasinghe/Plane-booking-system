public class Person {
    private String name;
    private String surname;
    private String email;

    // Constructor
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    //Name
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    //Surname
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }

    //Email
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public void print_person_info(){
        System.out.println("Name: "+name);
        System.out.println("Surname: "+surname);
        System.out.println("Email: "+email);
    }
}