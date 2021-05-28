package ui;

/*
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
 */

import app.*;
import utils.Tools;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class UI {

    private Hotel hotel;
    private User loggedUser=null;
    public static Scanner sc = new Scanner(System.in);

    public UI(Hotel hotel)
    {
        this.hotel = hotel;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


    public void startingMenu() throws Exception {
        while(true) {
            System.out.println(" ");
            System.out.println("Digital reception");
            System.out.print("Welcome ");
            System.out.println(LocalDate.now());
            System.out.println("");
            System.out.println("Press (1) to sign in");
            System.out.println("Press (2) to sign up");
            System.out.println("Press (3) to edit");
            System.out.println("Press (0) to quit");
            System.out.print("Option: ");

            int option = sc.nextInt(); // options selection
            switch (option) {
                case 1:
                    if (signIn(option)) {
                        customer();
                    }
                    break;
                case 2:
                    try {
                        if (signUp()) {
                            startingMenu();
                        }
                    } catch (Exception e) {
                        startingMenu();
                    }
                    break;
                case 3:
                    if (signIn(option)) {
                        edit();
                    }
                    break;
                case 0:
                    setLoggedUser(null);
                    System.exit(0);
                default:
                    System.out.println("Non-existing option");
                    break;
            }
        }
    }


    public void customer() throws Exception {
                System.out.println(" ");
                System.out.println("***** Customer service *****");
                System.out.println(" ");
                System.out.println("Press (1) to book room");
                System.out.println("Press (2) user info");
                System.out.println("Press (0) to log out");
                System.out.print("Option: ");

                int customerOption = sc.nextInt();
                switch (customerOption) {
                    case 1:
                        book();
                        signOut();
                        break;
                    case 2:
                        System.out.println(loggedUser.toString());
                        customer();
                        break;
                    case 0:
                        signOut();
                        return;
                    default:
                        System.out.println("Non-existing option");
                        break;
                }
    }


    public void edit() throws Exception {
        while(true) {
            System.out.println(" ");
            System.out.println("***** Admin panel *****");
            System.out.println(" ");
            System.out.println("Press (1) add room");
            System.out.println("Press (2) remove room");
            System.out.println("Press (3) room list - PDF print in progress");
            System.out.println("Press (0) to log out");
            System.out.print("Option: ");

            int customerOption = sc.nextInt();
            switch (customerOption) {
                case 1:
                    hotel.createRoom();
                    break;
                case 2:
                    Admin.removeRoom();
                    break;
                case 3:
                    printRooms();
                    break;
                case 0:
                    signOut();
                    return;
                default:
                    System.out.println("Non-existing option");
                    break;
            }
        }
    }


    public void welcomeRow(){
        System.out.println(" ");
        System.out.println("*********** WELCOME ***********");
        System.out.println("*** " + hotel.name + " ***");
        System.out.println("*******************************");
    }


    private boolean signIn(int option) throws Exception {
        System.out.println(" ");
        System.out.println("*******");
        System.out.println("Sign in");
        System.out.println("Enter username: ");
        String username = sc.next();
        System.out.println("Enter password: ");
        String password = sc.next();
        if(!loginCheck(option,username, password)){
            System.out.println("Try again");
            startingMenu();
        }
        User user = Tools.getUserByUsername(username);
        if(user != null) {
            setLoggedUser(user);
        }
        return true;
    }


    private boolean signUp() throws Exception {
        System.out.println(" ");
        System.out.println("*******");
        System.out.println("Sign up");
        System.out.println("Enter username: ");
        String username = sc.next();
        if(usernameCheck(username)){
            System.out.print("Existing username");
            startingMenu();
        }
        System.out.println("Enter password: ");
        String password = sc.next();
        sc.nextLine();
        String name = Tools.stringCheck("Name");
        String surname = Tools.stringCheck("Surname");
        int phone = Tools.intCheck("Number");
        //ArrayList<Customer> users = new ArrayList<Customer>();
        Customer customer = new Customer(username, password, name, surname, phone);
        setLoggedUser(customer);
        hotel.addUser(customer);
        return true;
    }


    private boolean usernameCheck(String username) {
        if(Tools.userCheck("data/customers.csv", username, 0)){
            return true;
        }
        return false;
    }


    private boolean loginCheck(int option, String username, String password) throws IOException {
        if(option == 1){
            if(Tools.userCheck("data/customers.csv", username, 0) && Tools.userCheck("data/customers.csv", password, 1)){
                return true;
            }
            else{
                return false;
            }
        }
        else if(option == 3){
            if(Tools.userCheck("data/admin.csv", username, 0) && Tools.userCheck("data/admin.csv", password, 1)){
                System.out.println("Logged as admin");
                return true;
            }
            else{
                return false;
            }
        }
        throw new IOException("Error");
    }

    public void book() throws Exception {
        ArrayList<Room> availableRooms = Tools.getStringRoom();
        int counter = 1;
        int chosenRoomNum;
        for (Room room : availableRooms) {
            System.out.println(counter + ". " + room.toString());
            counter++;
        }
        System.out.println("---------");
        while (true) {
            chosenRoomNum = Tools.intCheck(" your room");
            if (chosenRoomNum >= 1 && chosenRoomNum <= availableRooms.size()) {
                break;
            }
        }
        Room room = availableRooms.get(chosenRoomNum-1);
        if(room.getAvailability()) {
            room.setUser(loggedUser);
            room.setAvailability(false);
            Tools.writeRooms();
        }
        else
        {
            System.out.println("Room is already occupied");
        }
    }


    public void printRooms() throws Exception {
        ArrayList<Room> availableRooms = Tools.getStringRoom();
        int counter = 1;
        int chosenRoomNum;
        for (Room room : availableRooms) {
            System.out.println(counter + ". " + room.toString());
            counter++;
        }

        /*
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("data\\PDF\\ - RoomList.pdf"));

            document.open();
            document.add(new Paragraph(" "));
            document.close();
            return true;

        } catch (FileNotFoundException e) {
            return false;
        } catch (DocumentException ex) {
            return false;
        }
        */
    }


    public void roomsToPdf(){
        ArrayList<Room> availableRooms = Tools.getStringRoom();
        int counter = 1;
        int chosenRoomNum;
        for (Room room : availableRooms) {
            System.out.println(counter + ". " + room.toString());
            counter++;
        }
    }


    public void signOut() throws Exception {
        setLoggedUser(null);
        startingMenu();
    }

}
