import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MartianRobotsRunner implements Runnable {
    private String filePath;
    private Scanner fileScanner;
    private boolean verboseFlag;

    public MartianRobotsRunner(String filePath, boolean verboseFlag){
        this.filePath = filePath;
        this.verboseFlag = verboseFlag;
        try {
            this.fileScanner = new Scanner(new File(filePath.isEmpty() ? "input.txt" : filePath));
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    @Override
    public void run() {

        int lineCounter = 0;
        RobotPosition[][] robotPositionGrid = null;
        List<Robot> robots = new ArrayList<Robot>();

        //Try to load input.txt file


        //Step through and process input file
        while (fileScanner.hasNextLine()) {
            String nextLine = fileScanner.nextLine();

            if (lineCounter == 0) {
                //Build our Martian Grid
                String[] nextLineSplit = nextLine.split(" ");
                int gridWidth = Integer.parseInt(nextLineSplit[0]) + 1;
                int gridHeight = Integer.parseInt(nextLineSplit[1]) + 1;
                robotPositionGrid = RobotPosition.buildGrid(gridWidth, gridHeight);
            } else if (!nextLine.isEmpty()) {

                // Parse Robot Position/Orientation
                String[] robotPosition = nextLine.split(" ");
                int nextX = Integer.parseInt(robotPosition[0]);
                int nextY = Integer.parseInt(robotPosition[1]);
                char nextDirection = robotPosition[2].charAt(0);

                //Create new robot and add to our list of robots
                Robot nextRobot = new Robot(robotPositionGrid[nextX][nextY], Orientation.fromDirection(nextDirection));
                robots.add(nextRobot);

                //Parse next input line into RobotCommands and issue each command in succession
                String commands = fileScanner.nextLine();
                if (verboseFlag) System.out.println("Starting moves for robot: " + nextRobot);
                for (char c : commands.toCharArray()) {
                    nextRobot.issueCommand(robotPositionGrid, c);
                    if (verboseFlag) System.out.println("Moving " + c + " to: " + nextRobot);
                }
                if (verboseFlag) System.out.println("-----DONE MOVING------");
            }
            lineCounter++;
        }
        //Output Final Robot Positions
        for (Robot r : robots) System.out.println(r);
    }

    public static void main(String[] args) {
        MartianRobotsRunner r = new MartianRobotsRunner(
                args.length > 0 ? args[0] : "", args.length > 1 ? args[1].equals("-v") : false);
        r.run();
    }
}
