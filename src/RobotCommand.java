public abstract class RobotCommand {

    public abstract void move(RobotPosition[][] grid, Robot robot);

    public static RobotCommand getCommand(char command) throws IllegalArgumentException {
        switch(command) {
            case 'L': return new LeftCommand();
            case 'R': return new RightCommand();
            case 'F': return new ForwardCommand();
            default: throw new IllegalArgumentException("No RobotCommand Exists for " +
                    "command moniker: {command}");
        }
    }

}
