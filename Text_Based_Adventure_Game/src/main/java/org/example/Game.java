package org.example;

import java.util.Scanner;

public class Game {
   private Player player; // The player object representing the user
   private NPC gardener; // The gardener NPC

    // Method to create and initialize the rooms and set their exits.
    public void createRooms() {
        Room outside, hall, kitchen, bedroom, garden, library, basement, treasureRoom, hiddenRoom;

        // Create rooms with descriptions.
        outside = new Room("outside the main entrance of a stately manor.");
        hall = new Room("in the hall.");
        kitchen = new Room("in the kitchen.");
        bedroom = new Room("in the bedroom.");
        garden = new Room("in the garden.");
        library = new Room("in the library.");
        basement = new Room("in the basement, which is dark and scary.");
        treasureRoom = new Room("in the treasure room, which holds a mysterious chest.");
        hiddenRoom = new Room("in the hidden room, revealed by a magical plant.  " +
                "/nThe walls are covered in ancient runes, and in the center of the room, " +
                "/n there is a pedestal with a glowing artifact.");

        // Initialize room exits: specifying where each exit leads.
        outside.setExit("north", hall);
        hall.setExit("south", outside);
        hall.setExit("east", kitchen);
        hall.setExit("west", bedroom);
        hall.setExit("north", garden);
        kitchen.setExit("west", hall);
        kitchen.setExit("down", basement); // Kitchen has a door leading down to the basement
        bedroom.setExit("east", hall);
        garden.setExit("south", hall);
        garden.setExit("east", library);
        library.setExit("west", garden);
        basement.setExit("up", kitchen); // Exit from the basement back to the kitchen
        basement.setExit("north", treasureRoom);
        treasureRoom.setExit("south", basement);

        // Place items in specific rooms.
        kitchen.addItem("key");
        bedroom.addItem("flashlight");
        garden.addItem("shovel");
        library.addItem("book");
        basement.addItem("treasure");
        hiddenRoom.addItem("artifact");

        // Initialize NPC and place in the garden.
        gardener = new NPC("Gardener", "The key to the basement is hidden in the kitchen.", "magic seed");

        player = new Player(outside); // Set the player's starting room to 'outside'
    }

    // Method to start and manage the game loop.
    public void play() {
        // Create and set up the rooms.
        createRooms();

        // Initialize a scanner to read player input.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Joe's Adventure Game!");

        boolean playing = true; // Flag to control the game loop.
        boolean plantedSeed = false; // Flag to check if the magic seed has been planted.
        boolean solvedRiddle = false; // Flag to check if the riddle has been solved.
        boolean artifactAccessible = false; // Flag to check if the artifact is accessible.

        // Game loop that continues until the player decides to quit or wins.
        while (playing) {
            Room currentRoom = player.getCurrentRoom();
            // Display the current room description.
            if (!currentRoom.isVisited()) {
                System.out.println("You are " + currentRoom.getDescription());
                currentRoom.setVisited(true);
            } else {
                System.out.println("You are back " + currentRoom.getDescription());
            }

            // Display available exits from the current room.
            System.out.print("Available exits: ");
            for (String direction : currentRoom.getExits().keySet()) {
                System.out.print(direction + " ");
            }
            System.out.println();

            // Special message and interaction prompt if the player is in the garden.
            if (currentRoom.getDescription().equals("in the garden.")) {
                System.out.println("You see a gardener here.");
                System.out.print("Enter 'talk' to speak with the gardener or 'take item' to take the item from the gardener: ");
                if (plantedSeed) {
                    System.out.print("Enter 'climb vine' to climb the vine to a hidden room: ");
                }
            }

            // Special message if the player is in the hidden room.
            if (currentRoom.getDescription().equals("in the hidden room, revealed by a magical plant. " +
                    "The walls are covered in ancient runes, and in the center of the room, " +
                    "there is a pedestal with a glowing artifact.")) {
                System.out.println("You see ancient runes on the walls. They seem to form a riddle.");
                if (!solvedRiddle) {
                    System.out.print("Enter 'solve riddle' to attempt to solve the riddle: ");
                }else {
                    System.out.print("Enter 'take artifact' to take the artifact from the pedestal: ");
                }

            }

            // Read the player's command input.
            String command = scanner.nextLine();

            if (command.startsWith("go ")) {
                // Handle movement commands.
                String direction = command.substring(3); // Extract the direction.
                Room nextRoom = currentRoom.getExit(direction);
                if (nextRoom != null) {
                    if (nextRoom.getDescription().contains("basement") && !player.hasItem("key")) {
                        System.out.println("The basement is locked. You need a key to enter.");
                    } else {
                        player.setCurrentRoom(nextRoom); // Update the player's current room.
                    }
                } else {
                    System.out.println("You can't go that way!");
                }
            } else if (command.startsWith("take ")) {
                // Handle item pickup commands.
                String item = command.substring(5); // Extract the item name.
                if (currentRoom.getItems().contains(item)) {
                    player.addItem(item); // Add the item to the player's inventory.
                    currentRoom.removeItem(item); // Remove the item from the room.
                    System.out.println("You picked up the " + item);
                } else {
                    System.out.println("There is no " + item + " here!");
                }
            } else if (command.equals("inventory")) {
                // Display the player's inventory.
                System.out.println("You are carrying: " + player.getInventory());
            } else if (command.equals("talk") && currentRoom.getDescription().equals("in the garden.")) {
                // Handle interaction with the NPC in the garden.
                System.out.println("Gardener: " + gardener.getHint());
                if (!player.hasItem(gardener.getItem())) {
                    // Add the magic seed to the player's inventory.
                    player.addItem(gardener.getItem());
                    System.out.println("You received a " + gardener.getItem() + " from the gardener.");
                } else {
                    System.out.println("You already have the " + gardener.getItem() + ".");
                }
            } else if (command.equals("take item") && currentRoom.getDescription().equals("in the garden.")) {
                // Handle taking an item from the NPC.
                if (!player.hasItem(gardener.getItem())) {
                    player.addItem(gardener.getItem());
                    System.out.println("You received a " + gardener.getItem() + " from the gardener.");
                } else {
                    System.out.println("You already have the " + gardener.getItem() + ".");
                }
            } else if (command.equals("use shovel") && currentRoom.getDescription().equals("in the garden.") && player.hasItem("shovel")) {
                // Handle using the shovel in the garden.
                System.out.println("You dig a hole in the garden and find a hidden key!");
                player.addItem("hidden key");
            } else if (command.equals("plant seed") && currentRoom.getDescription().equals("in the garden.")
                    && player.hasItem("magic seed")) {
                // Handle planting the magic seed in the garden.
                System.out.println("You plant the magic seed in the garden. A magical vine starts growing!");
                plantedSeed = true;
                currentRoom.setExit("up", new Room("in the hidden room, revealed by a magical plant. " +
                        "The walls are covered in ancient runes, and in the center of the room, " +
                        "there is a pedestal with a glowing artifact.")); // Add a new exit to the hidden room.
            } else if (command.equals("climb vine") && currentRoom.getDescription().equals("in the garden.") && plantedSeed) {
                // Handle climbing the vine to the hidden room.
                Room hiddenRoom = currentRoom.getExit("up");
                player.setCurrentRoom(hiddenRoom);
                System.out.println("You climb the vine and enter the hidden room.");
            } else if (command.equals("solve riddle") && currentRoom.getDescription().equals("in the hidden room, revealed by a magical plant. The walls are covered in ancient runes, and in the center of the room, there is a pedestal with a glowing artifact.")) {
                // Handle solving the riddle in the hidden room.
                System.out.println("You decipher the ancient runes and solve the riddle! The artifact is now accessible.");
                solvedRiddle = true;
                artifactAccessible = true;
                currentRoom.addItem("artifact");    // Add the artifact to the room.
//                System.out.println("Current room items after solving riddle: " + currentRoom.getItems()); // Debug statement
            } else if (command.equals("take artifact") && currentRoom.getDescription().equals("in the hidden room, revealed by a magical plant. The walls are covered in ancient runes, and in the center of the room, there is a pedestal with a glowing artifact.") && artifactAccessible) {
                // Handle taking the artifact after solving the riddle.
                if (currentRoom.getItems().contains("artifact")) {
                    player.addItem("artifact"); // Add the artifact to the player's inventory.
                    currentRoom.removeItem("artifact"); // Remove the artifact from the room.
                    System.out.println("You picked up the glowing artifact.");
                } else {
                    System.out.println("There is no artifact here!");
                }
            } else if (command.equals("quit")) {
                // Handle the quit command to end the game.
                playing = false;
                System.out.println("Thanks for playing! Goodbye.");
            } else {
                // Handle unrecognized commands.
                System.out.println("I don't understand that command.");
            }

            // Check for victory condition: finding the treasure in the basement with the artifact.
            if (player.getCurrentRoom().getDescription().contains("treasure room") && player.hasItem("artifact")) {
                System.out.println("Congratulations! You've found the treasure and obtained the magical artifact! You win the game!");
                playing = false;
            }
        }

        // Close the scanner to free resources.
        scanner.close();
    }

    // Main method to start the game.
    public static void main(String[] args) {
        Game game = new Game();
        game.play(); // Start the game by calling the play method.
    }
}
