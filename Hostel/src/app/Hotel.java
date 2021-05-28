package app;

import utils.Tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Hotel {
    public String name;
    protected ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<User> users = new ArrayList<User>();

    public Hotel(String name) {
        this.name = name;
    }

    public ArrayList<Room> getAvailableRooms()
    {
        ArrayList<Room> availableRooms = new ArrayList<Room>();
        for(Room room : rooms)
        {
            if(room.getAvailability())
            {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void addRoom(Room room)
    {
        rooms.add(room);
    }

    public void createRoom() throws Exception {
        int number = Tools.intCheck2("room number");
        double price = Tools.doubleCheck2("price");
        Room room = new Room(number,price);
        rooms.add(room);
        Tools.appendToFile("rooms.csv", room.toString());
        Tools.loadRooms("rooms.csv");
    }


    public void addUser(User user) throws IOException {
        users.add(user);
        Tools.appendToFile("customers.csv", user.toString());
    }


}
