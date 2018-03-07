import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotPositionTest {

    private RobotPosition[][] grid;
    private RobotPosition gridOrigin;
    private Robot originRobotNorth;
    private Robot originRobotSouth;
    private Robot topLeftRobotNorth;
    private Robot topRightRobotNorth;
    private Robot bottomRightRobotNorth;
    private RobotPosition originRobotStartPosition;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        int gridSize = 5;
        grid                = RobotPosition.buildGrid(gridSize, gridSize);
        gridOrigin          = grid[0][0];
        originRobotNorth    = new Robot(gridOrigin, Orientation.NORTH);
        originRobotSouth    = new Robot(gridOrigin, Orientation.SOUTH);

        topLeftRobotNorth       = new Robot(grid[0][grid[0].length - 1], Orientation.NORTH);
        topRightRobotNorth      = new Robot(grid[grid.length - 1][grid[0].length - 1], Orientation.NORTH);
        bottomRightRobotNorth   = new Robot(grid[grid.length - 1][0], Orientation.NORTH);

        originRobotStartPosition  = originRobotNorth.getPosition();
        assertTrue(grid.length == gridSize && grid[0].length == gridSize);
    }

    @Test
    void positivePositionOffGrid(){
        RobotPosition tooFarEast = new RobotPosition(grid.length + 1,0);
        RobotPosition tooFarNorth = new RobotPosition(0,grid[0].length + 1);
        assertTrue(tooFarEast.isOffGrid(grid));
        assertTrue(tooFarNorth.isOffGrid(grid));
    }

    @Test
    void negativePositionOffGrid(){
        RobotPosition tooFarSouth = new RobotPosition(0,-1);
        RobotPosition tooFarWest = new RobotPosition(-1,0);

        assertTrue(tooFarWest.isOffGrid(grid));
        assertTrue(tooFarSouth.isOffGrid(grid));
    }

    @Test
    void originMovingForwardFacingSouthisOffGrid(){

        originRobotSouth.issueCommand(grid, 'F');

        assertEquals(originRobotSouth.getPosition(), gridOrigin);
        assertTrue(gridOrigin.hasLostScent());
        assertTrue(originRobotSouth.isLost());
    }

    @Test
    void cycleEndsAtStart(){
        //Test Bottom Left cycle
        String originCycleRightCommands = "FRFRFRFR";
        for(char c: originCycleRightCommands.toCharArray()){
            originRobotNorth.issueCommand(grid, c);
        }

        assertEquals(originRobotNorth.getPosition(), gridOrigin);
        assertEquals(originRobotNorth.getOrientation(), Orientation.NORTH);

        //Test Top Left Cycle
        String topLeftCycleRight = "RFRFRFRF";
        for(char c: topLeftCycleRight.toCharArray()){
            topLeftRobotNorth.issueCommand(grid, c);
        }

        assertEquals(topLeftRobotNorth.getPosition(), new RobotPosition(0, grid[0].length - 1));
        assertEquals(topLeftRobotNorth.getOrientation(), Orientation.NORTH);

        //Test Top Right Cycle
        String topRightCycleRight = "LFLFLFLF";
        for(char c: topRightCycleRight.toCharArray()){
            topRightRobotNorth.issueCommand(grid, c);
        }

        assertEquals(topRightRobotNorth.getPosition(), new RobotPosition(grid[0].length - 1, grid[0].length - 1));
        assertEquals(topRightRobotNorth.getOrientation(), Orientation.NORTH);

        //Check Bottom Right cycle
        String bototmRightCycleRight = "FLFLFLFL";
        for(char c: bototmRightCycleRight.toCharArray()){
            bottomRightRobotNorth.issueCommand(grid, c);
        }

        assertEquals(bottomRightRobotNorth.getPosition(), new RobotPosition(grid[0].length - 1, 0));
        assertEquals(bottomRightRobotNorth.getOrientation(), Orientation.NORTH);
    }

    @Test
    void testMoveOffGridWithScent(){
        gridOrigin.setLostScent(true);
        originRobotNorth.issueCommand(grid, 'L');
        originRobotNorth.issueCommand(grid, 'F');

        assertTrue(!originRobotNorth.isLost());
        assertEquals(originRobotNorth.getPosition(), gridOrigin);
        assertEquals(originRobotNorth.getOrientation(), Orientation.WEST);
    }

}