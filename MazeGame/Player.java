public class Player {
    // Players x position
    private double x;
    // Players y position
    private double y;
    // Players Size
    private double SIZE;
    // Players Speed
    private double SPEED;

    // Player constructor
    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        SIZE = 2;
        SPEED = 0.8;
    }

    // Draw the player
    public void draw() {
        StdDraw.filledCircle(x, y, SIZE);
    }

    // Four methods for movement for simplicity
    // Moves player left
    public void moveLeft() {
        x -= SPEED;
    }

    // Moves player right
    public void moveRight() {
        x += SPEED;
    }

    // Moves player up
    public void moveUp() {
        y += SPEED;
    }

    // Moves player down
    public void moveDown() {
        y -= SPEED;
    }

    // Getter method for x position
    public double getX() {
        return x;
    }

    // Getter method for y position
    public double getY() {
        return y;
    }

    // Getter method for the size
    public double getSize() {
        return SIZE;
    }

    // Returns the location of the cell the player is currently in
    public int[] getLocation() {
        double xPos = x + 5;
        xPos = xPos / 10.0;
        int j = (int) xPos;

        double yPos = y + 5;
        yPos = yPos / 10.0;
        int i = (int) yPos;

        int[] location = { i, j };
        return location;
    }

    public static void main(String[] args) {
        Player player = new Player(0, 0);
        for (int i = 0; i < 10; i++) player.moveRight();
        for (int i = 0; i < 10; i++) player.moveUp();
        StdOut.println(player.getX() + "," + player.getY());
        int[] loc = player.getLocation();
        StdOut.println(loc[0] + " " + loc[1]);
        player.draw();
    }
}
