package gradle.cucumber;

public class Bomb extends Item{
    private Integer ticks;

    public Bomb(Maze maze, Location currentLocation, Integer ticks) {
        super(maze);
        this.setCurrentLocation(currentLocation);
        this.ticks = ticks;
    }

    @Override
    public void boom() {
        this.getCurrentLocation().removeItem(this);
    }

    @Override
    protected void interactsWith(Item otherItem) {

    }

    private void boomExpansive(){
        Location location = getCurrentLocation();
        Integer x = location.getXCoordinate();
        Integer y = location.getYCoordinate();

        for (int i = 1; i < 3; i++) {
            if (maze.existLocation(x + i, y)) {maze.getLocation(x + i, y).boom();}
            if (maze.existLocation(x - i, y)) {maze.getLocation(x - i, y).boom();}
            if (maze.existLocation(x, y + i)) {maze.getLocation(x, y + i).boom();}
            if (maze.existLocation(x, y - i)) {maze.getLocation(x, y - 1).boom();}
        }
    }

    public void tick() {
        this.ticks -= 1;
        if(ticks == 0){
            this.getCurrentLocation().boom();
            this.boomExpansive();
        }
    }
}
