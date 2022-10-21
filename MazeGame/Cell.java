import java.awt.Color;

public class Cell {
    // Holds the position of cell
    private double x;
    private double y;
    // Holds the number of the cell
    private int grid;
    private Color color;

    public Cell(double x, double y, int grid) {
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.color = Color.WHITE;
    }

    // To use in a union find must have an integer representation of a cell
    public int getGrid() {
        return grid;
    }

    // Setter method for color
    public void setColor(Color color) {
        this.color = color;
    }

    // Getter method for color
    public Color getColor() {
        return color;
    }

    public void draw() {
        // Changes the pen color back to ensure the maze is still drawn in black
        StdDraw.setPenColor(color);
        StdDraw.filledSquare(x, y, 4.9);
        StdDraw.setPenColor(Color.BLACK);
    }

    public static void main(String[] args) {
        Cell cell = new Cell(5.0, 5.0, 6);
        StdOut.println(cell.getGrid());
        cell.setColor(Color.YELLOW);
        StdOut.println(cell.getColor());
        StdDraw.setScale(0, 20);
        cell.draw();
    }
}
