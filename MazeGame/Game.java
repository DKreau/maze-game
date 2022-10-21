import java.awt.Color;

public class Game {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double diff = Double.parseDouble(args[1]);
        diff = diff / 100.0;

        Maze maze = new Maze(n);
        // Start player in the top right cell of the maze
        Player player = new Player(0, (n - 1) * 10);
        maze.generate(diff);

        // Set up StdDraw
        StdDraw.setScale(-30, 10 * n + 20);
        StdDraw.setCanvasSize(800, 800);
        StdDraw.enableDoubleBuffering();

        boolean run = true;
        Cell prev = new Cell(0, 0, 800);
        double highscore = Double.MAX_VALUE;
        Stopwatch watch = new Stopwatch();
        double score = 0;

        // Main animation loop
        while (true) {
            // Feature to allow the player to press the n-key after they have competed
            // a maze to play the game again with the same command-lin arguments but
            // a different maze.
            if (StdDraw.isKeyPressed(78)) {
                if (score < highscore) {
                    highscore = score;
                }
                run = true;
                player = new Player(0, (n - 1) * 10);
                maze = new Maze(n);
                watch = new Stopwatch();
                maze.generate(diff);
            }
            // Get the walls which will be used in our collision detection
            Wall[] walls = maze.getWalls();

            // Animation loop for every individual maze game
            while (run) {
                StdDraw.setScale(-30, 10 * n + 20);

                // Stop the game if the player is in the red end square
                if (player.getLocation()[0] == 0 && player.getLocation()[1] == n - 1) {
                    run = false;
                    score = watch.elapsedTime();
                }

                // First we find the two closest walls to the player
                Wall[] closestWalls = { walls[0], walls[1] };
                for (int i = 0; i < walls.length; i++) {
                    if (walls[i].distanceTo(player) < closestWalls[0].distanceTo(player)) {
                        closestWalls[1] = closestWalls[0];
                        closestWalls[0] = walls[i];
                    }
                    else if (walls[i].distanceTo(player) < closestWalls[1].distanceTo(player)) {
                        closestWalls[1] = walls[i];
                    }
                }
                // Then check if the player is colliding with either one
                String direction1 = closestWalls[0].collide(player);
                String direction2 = closestWalls[1].collide(player);
                boolean left = true;
                boolean right = true;
                boolean up = true;
                boolean down = true;
                // Prevents the directions the player should not be able to move in
                // also moves the player away from the wall to prevent wall clipping
                // where the player gets stuck in a wall.
                if (direction1.equals("left") || direction2.equals("left")) {
                    left = false;
                    player.moveRight();
                }
                if (direction1.equals("right") || direction2.equals("right")) {
                    right = false;
                    player.moveLeft();
                }
                if (direction1.equals("up") || direction2.equals("up")) {
                    up = false;
                    player.moveDown();
                }
                if (direction1.equals("down") || direction2.equals("down")) {
                    down = false;
                    player.moveUp();
                }

                // Check for directional input from the player
                if (StdDraw.isKeyPressed(37) && left) player.moveLeft();
                if (StdDraw.isKeyPressed(38) && up) player.moveUp();
                if (StdDraw.isKeyPressed(39) && right) player.moveRight();
                if (StdDraw.isKeyPressed(40) && down) player.moveDown();

                // This section of code draws the green path which follows the player
                // through the maze, like the string in the tale of The Minotaur.
                int[] location = player.getLocation();
                Grid grid = maze.getGrid();
                Cell cell = grid.getCells()[location[0]][location[1]];
                if (!cell.equals(prev) && prev.getColor().equals(Color.WHITE)) {
                    prev.setColor(Color.GREEN);
                }
                prev = grid.getCells()[location[0]][location[1]];

                // Basic animation steps after updating all necessary elements
                StdDraw.clear();
                maze.getGrid().draw();
                StdDraw.setPenRadius(0.1 / n);
                maze.draw();
                player.draw();
                StdDraw.text(n * 5 - 5, n * 10 + 5, "" + watch.elapsedTime());
                StdDraw.text(5, n * 10 + 5, "Highscore: " + highscore);
                StdDraw.show();
                StdDraw.pause(20);
            }
        }
    }
}

