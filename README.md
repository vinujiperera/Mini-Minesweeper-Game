# 💣 Mini Minesweeper Game

A classic Minesweeper game implementation built with **Java**, featuring an intuitive GUI powered by Swing and AWT libraries. Enjoy the timeless puzzle experience with a modern desktop application interface.

---

## 🎮 Features

- **8x8 Game Board** - Balanced difficulty with 10 randomly placed mines
- **Interactive GUI** - User-friendly interface built with Java Swing
- **Classic Gameplay Mechanics**:
  - Left-click to reveal cells
  - Right-click to place/remove flags
  - Auto-reveal adjacent empty cells (zero mines nearby)
  - Mine detection with adjacent mine count display
- **Game States** - Win/lose detection with clear win condition
- **Safe First Click** - First click is always guaranteed to be safe
- **Main Menu** - Easy navigation with a stylized start screen

---

## 📋 Requirements

- **Java 25** or higher
- Maven (for building the project)

---

## 🚀 Getting Started

### Build the Project

```bash
mvn clean install
```

### Run the Game

Using Maven:
```bash
mvn exec:java
```

Or compile and run directly:
```bash
javac -d target/classes src/main/java/com/railwaysystem/miniminesweeper/*.java
java -cp target/classes com.railwaysystem.miniminesweeper.MiniMinesweeper
```

---

## 🎯 How to Play

1. **Start the Game** - Click the "PLAY" button on the main menu
2. **First Click** - Click any cell to start the game (mines are placed after your first click)
3. **Reveal Cells** - Left-click to reveal a cell:
   - If it's a mine, game over!
   - If adjacent mines exist, the number appears
   - If no adjacent mines, all adjacent cells auto-reveal
4. **Flag Mines** - Right-click to mark suspected mines with flags
5. **Win** - Reveal all non-mine cells to win the game!

---

## 📁 Project Structure

```
Mini-Minesweeper-Game/
├── src/
│   └── main/
│       └── java/
│           └── com/railwaysystem/miniminesweeper/
│               ├── MiniMinesweeper.java      # Entry point
│               ├── MainMenuFrame.java        # Main menu GUI
│               ├── MineSweeperFrame.java     # Game board GUI
│               ├── GameBoard.java            # Game logic & state
│               └── Cell.java                 # Individual cell data
├── pom.xml                                   # Maven configuration
└── README.md                                 # This file
```

---

## 🏗️ Architecture Overview

### **MiniMinesweeper.java**
The entry point that initializes and displays the main menu using Swing's EDT.

### **MainMenuFrame.java**
A stylized main menu frame with:
- Title display in purple/magenta theme
- "PLAY" button to launch the game
- Transition to the game board

### **MineSweeperFrame.java**
The game board GUI that:
- Renders the 8x8 grid of clickable cells
- Handles user interactions (left/right clicks)
- Displays game status (win/lose)
- Updates cell visuals based on game state

### **GameBoard.java**
Core game logic including:
- Board initialization and reset
- Mine placement (avoiding the first clicked cell)
- Adjacency calculation
- Cell reveal logic with recursive flood-fill
- Flag management
- Win/lose condition checking

### **Cell.java**
Data model for individual cells with properties:
- Position (row, column)
- Mine status
- Revealed/Flagged states
- Adjacent mine count

---

## 🎨 UI Customization

The game features customizable styling through the GUI classes:

- **Main Menu Colors**: Purple/Magenta theme
- **Font**: Algerian (menu), Serif (buttons)
- **Window Size**: 500x500 pixels (main menu), 800x600+ (game board)

Feel free to modify colors, fonts, and sizes in `MainMenuFrame.java` and `MineSweeperFrame.java`.

---

## 🔧 Technologies Used

- **Language**: Java
- **GUI Framework**: Swing & AWT
- **Build Tool**: Maven
- **Target Version**: Java 25

---

## 📝 Game Rules

| Rule | Details |
|------|---------|
| **Board** | 8x8 grid with 10 mines |
| **Left Click** | Reveal a cell |
| **Right Click** | Flag/unflag a cell |
| **Auto-Reveal** | Adjacent cells reveal if no nearby mines |
| **Win Condition** | All non-mine cells revealed |
| **Lose Condition** | Click on a mine |
| **Safe Start** | First click never contains a mine |

---

## 🐛 Known Limitations

- Board size and mine count are hardcoded (8x8, 10 mines)
- No difficulty settings or customizable board size
- No high score/statistics tracking
- Limited visual feedback animations

---

## 📈 Future Enhancements

Consider adding:
- [ ] Difficulty levels (Easy, Medium, Hard)
- [ ] Custom board size options
- [ ] Game timer and statistics
- [ ] Score/leaderboard system
- [ ] Sound effects and visual animations
- [ ] Keyboard controls
- [ ] Multiplayer mode

---

## 👨‍💻 Author

Created by **Vinu Jiperera**

---

## 📄 License

This project is open source and available for educational and personal use.

---

## 🤝 Contributing

Feel free to fork this repository, make improvements, and submit pull requests!

---

## 💬 Feedback & Issues

Have suggestions or found a bug? Please open an issue on GitHub!

---

Enjoy the game! 🎮✨
