public class RobotPosition {
    private int x, y;
    private boolean lostScent;

    public RobotPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setLostScent(boolean lostScent) {
        this.lostScent = lostScent;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hasLostScent() {
        return lostScent;
    }

    public boolean isOffGrid(RobotPosition[][] grid){
        return x < 0 || x >= grid.length || y < 0 || y >= grid[0].length;
    }

    @Override
    public String toString(){
        return String.format("x=%s,y=%s - LostScent: %s", x, y, lostScent);
    }

    public static RobotPosition[][] buildGrid(int gridWidth, int gridHeight){
        RobotPosition[][] robotPositionGrid = new RobotPosition[gridWidth][gridHeight];
        for (int row = 0; row < robotPositionGrid.length; row++) {
            for (int col = 0; col < robotPositionGrid[0].length; col++) {
                robotPositionGrid[row][col] = new RobotPosition(row, col);
            }
        }
        return robotPositionGrid;
    }
}