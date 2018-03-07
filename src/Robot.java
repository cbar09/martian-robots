import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Robot {
    private RobotPosition position;
    private Orientation orientation;
    private boolean lost;

    public Robot(RobotPosition position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
        this.lost = false;
    }

    public Robot(int x, int y, char direction) {
        this(new RobotPosition(x, y), Orientation.fromDirection(direction));
    }

    public RobotPosition getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public boolean isLost(){
        return lost;
    }

    public void setLost(){
        this.lost = true;
    }

    public void setPosition(RobotPosition p) throws IllegalStateException {
        if(isLost()){
            throw new IllegalStateException("A lost robot cannot update its position.");
        }
        else{
            this.position = p;
        }
    }

    public void setOrientation(Orientation o) throws IllegalStateException {
        if(isLost()){
            throw new IllegalStateException("A lost robot cannot update its orientation.");
        }
        else {
            this.orientation = o;
        }
    }

    public void issueCommand(RobotPosition[][] grid, RobotCommand rc){
        if(!isLost()) rc.move(grid,this);
    }

    public void issueCommand(RobotPosition[][] grid, char command){
        this.issueCommand(grid, RobotCommand.getCommand(command));
    }

    public String toString(){
        return String.format("%s %s %s", position.getX(), position.getY(),
                orientation.getDirection()) + (isLost() ? " LOST" : "");
    }
}
