package ui;

import app.Hotel;
import utils.Tools;


public class Main {

    public static final String hotelName = "Digital Homeless Hostel";

    public static void main(String[] args) throws Exception {
        Hotel hotel = new Hotel(hotelName);

        UI ui = new UI(hotel);
        Tools.newUpload();
        ui.welcomeRow();
        ui.startingMenu();
    }
}
