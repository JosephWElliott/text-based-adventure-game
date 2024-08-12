package org.example;

import java.util.ArrayList;

public class Player {
    private Room currentRoom;   // Current room the player is in.
    private final ArrayList<String> inventory;  // List to hold items the player has collected.

    // Constructor to initialize the player with a starting room and an empty inventory.
    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
        this.inventory = new ArrayList<>();
    }

    // Method to get the current room the player is in.
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Method to set the current room the player is in.
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    // Method to add an item to the player's inventory.
    public void addItem(String item) {
        inventory.add(item);
    }

    // Method to remove an item from the player's inventory.
    public void removeItem(String item) {
        inventory.remove(item);
    }

    // Method to get the list of items in the player's inventory.
    public ArrayList<String> getInventory() {
        return inventory;
    }

    // Method to check if the player has a specific item.
    public boolean hasItem(String item) {
        return inventory.contains(item);
    }
}
