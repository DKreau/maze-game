import java.awt.Color;

public class Grid {
    // Connectivity data structure
    private UnionFind uf;
    // How big is the grid
    private int n;
    // Hold all of the cells
    private Cell[][] cells;

    // Constructs a grid with no connectivity
    public Grid(int n) {
        this.n = n;
        cells = new Cell[n][n];
        uf = new UnionFind(n * n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Cell cell = new Cell(10 * j, 10 * i, i * n + j);
                cells[i][j] = cell;
            }
        }
        // Make the first and last cells colored
        cells[n - 1][0].setColor(Color.GREEN);
        cells[0][n - 1].setColor(Color.RED);
    }

    // Getter method for cells
    public Cell[][] getCells() {
        Cell[][] cellsCopy = new Cell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cellsCopy[i][j] = cells[i][j];
            }
        }
        return cellsCopy;
    }

    // Getter method for union find
    public UnionFind getUf() {
        return uf;
    }

    // Connect two cells
    public void connect(Cell cell1, Cell cell2) {
        uf.union(cell1.getGrid(), cell2.getGrid());
    }

    // Check if two cells are connected
    public boolean isConnected(Cell cell1, Cell cell2) {
        return uf.find(cell1.getGrid()) == uf.find(cell2.getGrid());
    }

    // Returns how many connected components are in the union find
    public int size() {
        return uf.count();
    }

    public void draw() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j].draw();
            }
        }
    }

    public static void main(String[] args) {
        Grid grid = new Grid(9);
        grid.connect(grid.cells[0][0], grid.cells[0][1]);
        grid.connect(grid.cells[0][1], grid.cells[1][1]);
        StdOut.println(grid.isConnected(grid.cells[0][0], grid.cells[1][1]));
        // Should print 79...which is 81-2
        StdOut.println(grid.size());
        // Set a scale which holds a 9x9 grid for testing
        StdDraw.setScale(-30, 9 * 10 + 20);
        grid.draw();
    }
}
