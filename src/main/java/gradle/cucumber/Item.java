package gradle.cucumber;

import java.util.HashSet;
import java.util.List;

public abstract class Item {

    protected final Maze maze;
    protected boolean alive;
    protected Location currentLocation;

    public Item(Maze maze) {
        this.maze = maze;
        this.alive = true;
    }

    public Item(Maze maze, Location location){
        this.maze = maze;
        this.alive = true;
        this.currentLocation = location;
        this.currentLocation.addItem(this);
    }

    public abstract void boom();

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location newLocation) {
        this.currentLocation = newLocation;
    }

    public void move(Direction direction) {
        this.maze.move(this, direction);

        HashSet<Item> items = this.currentLocation.getItems();

        items.stream().forEach((item)->{
            if (!item.equals(this))
                item.interactsWith(this);
        });

    }

    public boolean isAlive() {
        return this.alive;
    }

    protected abstract void interactsWith(Item otherItem);

    protected void kill() {
        this.alive = false;
    }
}
