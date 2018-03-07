public enum Orientation {
    NORTH(360), WEST(270), SOUTH(180), EAST(90);
    private int degreesFromNorth;

    private Orientation(int degreesFromNorth){
        this.degreesFromNorth = degreesFromNorth;
    }

    public int getDegreesFromNorth() {
        return degreesFromNorth;
    }

    public char getDirection(){
        return this.name().charAt(0);
    }

    public static Orientation fromDirection(char direction) throws IllegalArgumentException {
        for(Orientation o: Orientation.values()){
            if(o.name().charAt(0) == direction)
                return o;
        }
        throw new IllegalArgumentException("{direction}: is an invalid direction.  " +
                "Must be (N,S,E,W).");
    }

    public static Orientation fromDegrees(int degreesFromNorth) throws IllegalArgumentException {
        for(Orientation o: Orientation.values()){
            if(o.degreesFromNorth == degreesFromNorth)
                return o;
        }
        throw new IllegalArgumentException("{degreesFromNorth}: is an invalid degrees.  " + "" +
                "Must be integral of 90.");
    }
}