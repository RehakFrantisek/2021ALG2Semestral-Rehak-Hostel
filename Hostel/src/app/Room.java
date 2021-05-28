package app;

public class Room {

    protected int numberOfRoom;
    protected double price;
    protected boolean availability = true;

    public Room(int numberOfRoom, double price, boolean availability) {
        this.numberOfRoom = numberOfRoom;
        this.price = price;
        this.availability = availability;
    }

    public Room(int numberOfRoom, double price) {
        this.numberOfRoom = numberOfRoom;
        this.price = price;
    }


    protected User user = null;

    public void setUser(User user) {
        if (getUser() != null) {
            this.user = user;
        } else {
            this.user = null;
        }
    }

    public User getUser() {
        return user;
    }

    public double getPrice() {
        return 0;
    }

    public void toggleAvailability() {
        if (getAvailability()) {
            setAvailability(false);
        } else {
            setAvailability(true);
        }
    }

    @Override
    public String toString() {
        return numberOfRoom + "," + price + "," + availability;
    }


    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean isAvailability() {
        return availability;
    }
}

