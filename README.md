# Card Hand Simulator

This repository contains a Java application that simulates arranging and managing a hand of cards for a person, ensuring cards of the same suit are kept together. The simulator deals cards to four players and provides functionalities to add, play, and iterate over cards in hand.

## Task Description

The goal of this task is to implement an application that supports a person arranging a group of cards in their hand. The sequence of cards is represented using a positional list to ensure cards of the same suit are kept together. The application supports efficient operations for adding and playing cards in constant time O(1).

#### Requirements:

1. **Card Suits**: Hearts, Clubs, Spades, Diamonds
2. **Card Ranks**: 2 – 10, Jack, Queen, King, Ace
3. **Players**: 4 players, each dealt one card per iteration
4. **Operations**:
   - `addACard(r, s)`: Add a new card with rank `r` and suit `s` to the hand.
   - `playACard(s)`: Remove and return a card of suit `s` from the player’s hand; if no card of suit `s` is available, remove and return an arbitrary card from the hand.
   - `iterator()`: Return an iterator for all cards currently in the hand.
   - `suitIterator(s)`: Return an iterator for all cards of suit `s` that are currently in the hand.

#### Example Deal Sequence:

- **First Deal:**
  - Player 1: 2-Clubs
  - Player 2: King-Diamonds
  - Player 3: Jack-Clubs
  - Player 4: 10-Hearts

- **Second Deal:**
  - Player 1: 2-Clubs, King-Hearts
  - Player 2: King-Diamonds, Ace-Spades
  - Player 3: Jack-Clubs, 3-Diamonds
  - Player 4: 10-Hearts, 6-Clubs

The game continues until the deck is empty. After the final deal, each player's hand is sorted by the order of suits received and by rank within each suit.

## Project Structure

- **Main.java**: The main Java file containing the implementation of the card hand simulator, including functionalities for adding, playing, and iterating over cards.

## How to Run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/nisal2002/Card-Hand-Simulator.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd Card-Hand-Simulator
   ```

3. **Compile the Java program:**

   ```bash
   javac Main.java
   ```

4. **Run the program:**

   ```bash
   java Main
   ```

   The program will simulate the dealing of cards to four players, displaying each player's hand after each deal. You can interact with the program to add or play cards and iterate over the current hand.

## Technologies Used

- Java

## Author

- [Author](nisal2002) - Initial work

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
