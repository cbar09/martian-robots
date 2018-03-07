public class ForwardCommand extends RobotCommand {

    public void move(RobotPosition[][] grid, Robot robot) {
        int newPositionX = robot.getPosition().getX();
        int newPositionY = robot.getPosition().getY();

        switch(robot.getOrientation()) {
            case NORTH: newPositionY++; break;
            case SOUTH: newPositionY--; break;
            case EAST:  newPositionX++; break;
            case WEST:  newPositionX--; break;
        }

        //robot is trying to move off the edge
        if(new RobotPosition(newPositionX,newPositionY).isOffGrid(grid)){
            if(!robot.getPosition().hasLostScent()){
                robot.getPosition().setLostScent(true);
                robot.setLost();
            }
            //Else we smell the scent, and Robot does not move
        }
        else{
            //Else robot is not going off the edge, so update robot's position to newPosition
            robot.setPosition(grid[newPositionX][newPositionY]);
        }
    }
}