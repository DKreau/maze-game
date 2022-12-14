# maze-game

## Description

The game creates a random and solveable n by n maze and takes user input so they can guide a ball through the maze to solve it. The maze generation uses a randomized version of Kruskal's Algorithm and maintains a union-find data structure for connectivity between the nodes (cells) of the maze. The program takes two inputs: the size of the grid, and the difficulty of the maze.

### How to install and run

Download as a zip, open in your IDE of choice, and compile. Takes two command line arguments the first is the size of the grid (n by n) the second is a difficulty between 0-100 with 100 being the hardest. Arrow keys are input for moving around the maze and typing "n" gives you a new maze, good luck!

### Example inputs 
Here is n = 15 and difficulty = 100


<img width="631" alt="Screen Shot 2022-10-21 at 3 30 35 PM" src="https://user-images.githubusercontent.com/55841301/197275066-6277711f-80b0-4d38-a5d4-d22b0d6cda85.png">


Here is n = 12 and difficulty = 75

<img width="604" alt="Screen Shot 2022-10-21 at 3 32 42 PM" src="https://user-images.githubusercontent.com/55841301/197275374-5ca08291-c39d-4510-9039-f6d9f266b317.png">
