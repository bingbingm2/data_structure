package byow.Core;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private Position pos;
    private int width;
    private int height;
    private List<Position> floor;
    private List<Position> walls;

    public Room(Position p, int w, int h) {
        this.pos = p;
        this.width = w;
        this.height = h;
        this.floor = new ArrayList<>();
        this.walls = new ArrayList<>();

        int endRow = getY() + getHeight();
        int endCol = getX() + getWidth();
        for (int row = getY() - 1; row < endRow + 1; row++) {
            for (int col = getX() - 1; col < endCol + 1; col++) {
                if (row == getY() - 1 || row == endRow || col == getX() - 1 || col == endCol) {
                    walls.add(new Position(col, row));
                } else {
                    floor.add(new Position(col, row));
                }
            }
        }
    }

    public List<Position> getFloor() {
        return this.floor;
    }
    public List<Position> getWalls() {
        return this.walls;
    }
    public int getX() {
        return this.pos.getX();
    }
    public int getY() {
        return this.pos.getY();
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
}
