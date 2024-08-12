package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private final String description;
    private Map<String, Room> exits;    // Exits map to hold directions and corresponding rooms.
    private final ArrayList<String> items;  // List to hold items in the room.
    private boolean visited;    // Track if the room has been visited.

    // Constructor to initialize the room with a description, an empty exits map, and an empty items list.
    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.visited = false;
    }

    // Method to set an exit for the room in a given direction leading to a neighboring room.
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    // Method to retrieve the neighboring room in a given direction.
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    // Method to get the description of the room.
    public String getDescription() {
        return description;
    }

    // Method to add an item to the room's list of items.
    public void addItem(String item) {
        items.add(item);
    }

    // Method to get the list of items in the room.
    public ArrayList<String> getItems() {
        return items;
    }

    // Method to remove an item from the room's list of items.
    public void removeItem(String item) {
        items.remove(item);
    }

    // Method to get the map of exits for the room.
    public Map<String, Room> getExits() {
        return exits;
    }

    // Method to set the map of exits for the room.
    public void setExits(Map<String, Room> exits) {
        this.exits = exits;
    }

    // Method to check if the room has been visited.
    public boolean isVisited() {
        return visited;
    }

    // Method to set the room as visited.
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}