public class Maze {
    // Has a grid object
    private Grid grid;
    // Has an array of walls
    private Wall[] walls;
    // Has a size n
    private int n;

    public Maze(int n) {
        grid = new Grid(n);
        this.n = n;
        Cell[][] cells = grid.getCells();

        // Make the vertical walls
        Wall[] vertWalls = new Wall[(n + 1) * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                Wall wall;
                if (j == 0 || j == n) {
                    wall = new Wall(10 * j - 5, 10 * i, true);
                }
                else {
                    wall = new Wall(10 * j - 5, 10 * i, true, cells[i][j - 1], cells[i][j]);
                }
                vertWalls[i * (n + 1) + j] = wall;
            }
        }
        // Make the horizontal walls
        Wall[] horWalls = new Wall[n * (n + 1)];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n; j++) {
                Wall wall;
                if (i == 0 || i == n) {
                    wall = new Wall(10 * j, 10 * i - 5, false);
                }
                else {
                    wall = new Wall(10 * j, 10 * i - 5, false, cells[i - 1][j], cells[i][j]);
                }
                horWalls[i * n + j] = wall;
            }
        }
        walls = new Wall[n * (n + 1) * 2];
        for (int i = 0; i < vertWalls.length; i++) {
            walls[i] = vertWalls[i];
            walls[i + n * (n + 1)] = horWalls[i];
        }
    }

    // Remove a wall from maze
    public void remove(int r, double diff) {
        // Check to ensure it is a central wall
        if (!(walls[r].getCell1() == null)) {
            Cell cell1 = walls[r].getCell1();
            Cell cell2 = walls[r].getCell2();
            if (!grid.isConnected(cell1, cell2)) {
                grid.connect(cell1, cell2);
                walls[r].remove();
            }
            else if (StdRandom.uniform() > diff) {
                walls[r].remove();
            }
        }
    }

    // Getter for walls
    public Wall[] getWalls() {
        return walls;
    }

    // Getter for grid
    public Grid getGrid() {
        return grid;
    }

    // Generates a maze
    public void generate(double diff) {
        while (grid.size() > 1) {
            int r = StdRandom.uniform(walls.length);
            remove(r, diff);
        }
    }

    // Draw the maze
    public void draw() {
        for (int i = 0; i < walls.length; i++) {
            walls[i].draw();
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double diff = Double.parseDouble(args[1]);
        diff = diff / 100.0;
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setScale(-30, 10 * n + 20);
        StdDraw.setPenRadius(0.005);
        Maze maze = new Maze(n);
        maze.remove(n - 1, 1);
        maze.draw();
        StdDraw.clear();
        maze.generate(diff);
        maze.draw();
    }
}
