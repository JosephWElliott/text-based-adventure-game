package org.example;

public class NPC {
    private final String name; // NPC's name
    private final String hint; // Hint that the NPC provides
    private final String item; // Item that the NPC can give to the player

    // Constructor to initialize the NPC with a name, a hint, and an item.
    public NPC(String name, String hint, String item) {
        this.name = name;
        this.hint = hint;
        this.item = item;
    }

    // Method to get the NPC's name.
    public String getName() {
        return name;
    }

    // Method to get the hint provided by the NPC.
    public String getHint() {
        return hint;
    }

    // Method to get the item provided by the NPC.
    public String getItem() {
        return item;
    }
}
