public class RightCommand extends RobotCommand {

    public void move(RobotPosition[][] grid, Robot robot) {
        int newDegrees = (robot.getOrientation().getDegreesFromNorth() + 90) % 360;
        robot.setOrientation(Orientation.fromDegrees(newDegrees == 0 ? 360 : newDegrees));
    }
}