package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldBuilder {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 60;
    private TETile[][] world;
    private Random RANDOM;
    private List<Room> rooms;



    public WorldBuilder(Long seed) {
        world = new TETile[WIDTH][HEIGHT];
        RANDOM = new Random(seed);
        rooms = new ArrayList<>();

        // initialize
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public TETile[][] getWorld() {
        return world;
    }

    public void makeNRooms(int n) {
        for (int i = 0; i < n; i++) {
            Room r = makeOneRoom();
            rooms.add(r);
            drawRoom(r);
        }
    }

//    public void addRandomTile(TETile[][] world) {
//        int randPosX = RANDOM.nextInt(56) + 2;
//        int randPosY = RANDOM.nextInt(36) + 2;
//
//        makeOneRoom(world, randPosX, randPosY);
//    }

    public Room makeOneRoom() {
        Position randpos = randomPosition();
        int randlenX = RANDOM.nextInt(10) + 6;
        int randlenY = RANDOM.nextInt(10) + 6;

        Room RandRoom = new Room(randpos, randlenX, randlenY);
        for (Room r : rooms) {
            if (overlaps(r, RandRoom) && outsideWorld(RandRoom)) {
                return makeOneRoom();
            }
        }
        return RandRoom;
    }

    public Position randomPosition() {
        int x = RANDOM.nextInt(WIDTH) - 10;
        int y = RANDOM.nextInt(HEIGHT) - 10;
        return new Position(x, y);
    }

    public void drawRoom(Room r) {
        for (Position p : r.getFloor()) {
            world[p.getX()][p.getY()] = Tileset.FLOOR;
        }
        for (Position p : r.getWalls()) {
            world[p.getX()][p.getY()] = Tileset.WALL;
        }
    }

    public boolean overlaps(Room a, Room b) {
        for (Position p1 : b.getFloor()) {
            for (Position p2 : a.getFloor()) {
                if (Math.abs(p1.getX() - p2.getX()) <= 3 && Math.abs(p1.getY() - p2.getY()) <= 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean outsideWorld(Room r) {
        if (r.getX() + r.getWidth() > WIDTH || r.getY() + r.getHeight() > HEIGHT) {
            return true;
        }
        return false;
    }
}
