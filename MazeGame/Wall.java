public class Wall {
    // Holds the position of the wall's midpoint
    private double x;
    private double y;
    // Orientation of the wall
    private boolean isVertical;
    // Is the wall drawn
    private boolean isDrawn;
    // Two cells the wall separates
    private Cell cell1;
    private Cell cell2;

    // For making edge walls
    public Wall(double x, double y, boolean isVertical) {
        this.x = x;
        this.y = y;
        this.isVertical = isVertical;
        isDrawn = true;
        cell1 = null;
        cell2 = null;
    }

    // For making central walls
    public Wall(double x, double y, boolean isVertical, Cell cell1, Cell cell2) {
        this.x = x;
        this.y = y;
        this.isVertical = isVertical;
        isDrawn = true;
        // This does not ever get mutated so the spot bug is not a problem in this context
        this.cell1 = cell1;
        this.cell2 = cell2;
    }

    // Getter method for cell 1
    public Cell getCell1() {
        return cell1;
    }

    // Getter method for cell 2
    public Cell getCell2() {
        return cell2;
    }

    // Remove the wall
    public void remove() {
        isDrawn = false;
    }

    // Checks if the player is colliding with the wall if they are then it returns
    // the direction which the player should not be allowed to move during this tick
    // of the annimation.
    public String collide(Player player) {
        if (!isDrawn) {
            return "";
        }
        if (isVertical) {
            double x2 = player.getX() + player.getSize();
            double x1 = player.getX() - player.getSize();

            if (x1 <= x && x <= x2) {
                if (player.getX() < x) {
                    return "right";
                }
                else return "left";
            }
        }
        else {
            double y2 = player.getY() + player.getSize();
            double y1 = player.getY() - player.getSize();

            if (y1 <= y && y <= y2) {
                if (player.getY() < y) {
                    return "up";
                }
                else return "down";
            }
        }
        return "";
    }

    public double distanceTo(Player player) {
        double xDist = (x - player.getX()) * (x - player.getX());
        double yDist = (y - player.getY()) * (y - player.getY());

        return Math.sqrt(xDist + yDist);
    }

    public void draw() {
        if (isDrawn) {
            if (isVertical) {
                StdDraw.line(x, y - 5, x, y + 5);
            }
            else {
                StdDraw.line(x - 5, y, x + 5, y);
            }
        }
    }

    public static void main(String[] args) {
        // Check edge wall constructor
        Wall edge = new Wall(5, 5, false);
        Cell cell1 = new Cell(0, 0, 0);
        Cell cell2 = new Cell(10, 0, 1);
        // Check center wall constructor
        Wall center = new Wall(5, 5, true, cell1, cell2);

        Player player = new Player(5, 4);
        StdOut.println(center.collide(player));
        StdOut.println(edge.distanceTo(player));
        edge.remove();
        StdDraw.setScale(0, 20);
        center.draw();

    }
}
