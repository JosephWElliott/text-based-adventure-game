
Game Adventure Project

Overview
This project is a Java-based text adventure game where players explore various rooms, interact with non-player characters (NPCs), and make decisions that affect the game's outcome. The game is built with a focus on object-oriented principles, utilizing classes such as Room, NPC, Player, and Game to structure the game logic and functionality.

Project Structure
Room.java: Defines the Room class, which represents different locations within the game. Each room has a description, possible exits, and can contain items or NPCs.

NPC.java: Defines the NPC (Non-Player Character) class. NPCs can interact with the player, provide information, and may have their own behaviors or dialogue options.

Player.java: Defines the Player class, representing the user of the game. The player can interact with the environment, collect items, and make decisions that influence the game's progress.

Game.java: Contains the core logic of the game. It handles the game's flow, initializes rooms, NPCs, and the player, and manages interactions between these elements.

Features
Exploration: Players can move between different rooms, each with unique descriptions and possible actions.

Interaction: Players can interact with NPCs, who may provide hints, quests, or items that aid in the game.

Decision-Making: The game involves making choices that can affect the outcome of the story, making each playthrough unique.

How to Run
To run the game, ensure you have a Java Development Kit (JDK) installed. Compile and execute the Game.java file using the following commands:

bash
Copy code
javac Game.java
java Game
Future Enhancements
Inventory System: Add a feature that allows the player to collect and use items.

Combat Mechanics: Introduce a combat system where players can fight NPCs or other entities.

Save/Load Feature: Implement a system to save and load game progress.

Contributing
Contributions are welcome! Please fork the repository, create a new branch for your feature or bugfix, and submit a pull request.
