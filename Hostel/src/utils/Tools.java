package utils;

import app.Admin;
import app.Customer;
import app.Room;
import app.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Tools {

    public static ArrayList<User> stringCustomer = new ArrayList<User>();
    public static ArrayList<Room> stringRoom = new ArrayList<Room>();

    public static ArrayList<Room> getStringRoom()
    {
        return stringRoom;
    }


    public static boolean userCheck(String file, String input, int index) {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(file));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (data[index].matches(input)) {
                    return true;
                }
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void loadUsers(String fileName) throws IOException {
        File file = new File("data/" + fileName);
        boolean exists = file.exists();
        if(!file.exists()) {
            file.createNewFile();
        }
        else {
            BufferedReader csvReader = new BufferedReader(new FileReader("data/" + fileName));
            String row = "";
            while((row= csvReader.readLine()) != null){
                String[] line = row.split(",");
                Customer customer = new Customer(line[0],line[1],line[2],line[3],Integer.parseInt(line[4]));
                stringCustomer.add(customer);
            }
        }
        System.out.println("Data loaded");
    }

    public static void loadAdmin(String fileName) throws IOException {
        File file = new File("data/" + fileName);
        boolean exists = file.exists();
        if(!file.exists()) {
            file.createNewFile();
        }
        else {
            BufferedReader csvReader = new BufferedReader(new FileReader("data/" + fileName));
            String row = "";
            while((row= csvReader.readLine()) != null){
                String[] line = row.split(",");
                Admin admin = new Admin(line[0],line[1]);
                stringCustomer.add(admin);
            }
        }
        System.out.println("Data loaded");
    }

    public static void writeRooms() throws IOException {
        FileWriter writer = new FileWriter("data/rooms.csv" , false);
        for(Room room:stringRoom)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(room.toString());
            sb.append("\n");
            writer.write(sb.toString());
        }
        writer.close();
    }

    public static void writeCustomers() throws IOException{
        FileWriter writer = new FileWriter("data/customers.csv" , false);
        for(User user:stringCustomer)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(user.toString());
            sb.append("\n");
            writer.write(sb.toString());
        }
        writer.close();
    }

    public static void loadRooms(String fileName) throws IOException {
        File file = new File("data/" + fileName);
        boolean exists = file.exists();
        if(!file.exists()) {
            file.createNewFile();
        }
        else {
            BufferedReader csvReader = new BufferedReader(new FileReader("data/" + fileName));
            String row = "";
            while((row = csvReader.readLine()) != null){
                String[] line = row.split(",");
                Room room = new Room(Integer.parseInt(line[0]),Double.parseDouble(line[1]),Boolean.parseBoolean(line[2]));
                stringRoom.add(room);
            }
        }
        System.out.println("Rooms loaded");
    }


    public static ArrayList<User> getStringCustomer() {
        return stringCustomer;
    }


    public static User getUserByUsername(String username)
    {
        for(User user:stringCustomer)
        {
            //System.out.println(user.getUsername());
            //System.out.println(username);
            if(user.getUsername().equals(username))
            {
                return user;
            }
        }
        return null;
    }

    public static User getUserByUsernamePDF(String username)
    {
        for(User user:stringCustomer)
        {
            System.out.println(user.getUsername());
            //System.out.println(username);
            return user;
        }
        return null;
    }


    public static void appendToFile(String fileName, String toString) throws IOException {
        //File file = new File(fileName);
            FileWriter writer = new FileWriter("data/" + fileName, true);
            writer.write(toString);
            writer.write("\n");
            writer.close();
    }


    public static void appendToFile2(String fileName, String toString) throws IOException {
        File fileTest = new File("data/" + fileName);
        try{
            if (fileTest.createNewFile()){
                FileWriter writer = new FileWriter("data/" + fileName, true);
                writer.write(toString);
                writer.write("\n");
                writer.close();
            }
            else{

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void makeFolder(String folderName) {
        File dir = new File(folderName);
        if (!dir.exists()) {
            //System.out.println("Folder " + folderName + " created");
            dir.mkdirs();
        }
    }


    public static int intCheck(String input) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + input);
        int output = sc.nextInt();
        //if (sc.hasNextInt()) {
        if(isDigit(String.valueOf(output))){
            return output;
        } else {
            System.out.println("Wrong format");
            stringCheck(input);
        }
        return output;
    }

    public static int intCheck2(String input) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter:" + input);
        int output = sc.nextInt();
        return output;
    }

    public static double doubleCheck(String input) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + input);
        double output = sc.nextDouble();
        //if (sc.hasNextInt()) {
        if(isDigit(String.valueOf(output))){
            return output;
        } else {
            System.out.println("Wrong format");
            stringCheck(input);
        }
        return output;
    }

    public static double doubleCheck2(String input) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + input);
        double output = sc.nextDouble();
        return output;
    }

    public static String stringCheck(String input) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + input);
        String output = sc.next();
        //if (sc.hasNext("[A-Za-z]*")) {
        if(isString(output)){
            return output;
        } else {
            throw new Exception("Wrong format");
        }
    }

    public static boolean isString(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDigit(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void newUpload() throws IOException {
        makeFolder("data");
        makeFolder("money");
        loadUsers("customers.csv");
        appendToFile2("admin.csv","admin,admin");
        loadAdmin("admin.csv");
        loadRooms("rooms.csv");
    }

}