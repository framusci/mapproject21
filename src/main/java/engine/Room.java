package engine;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

class Room implements Serializable {
    private Map<Integer, Room> rooms;
    private Map<Integer, String> images;
    private int id;
    private static final int DIRECTIONS = 4;

    public Room(int id) {
        rooms = new HashMap<>();
        images = new HashMap<>();
        this.id = id;
    }

    //Set only image (no adjacent room in that direction)
    public void setAdjacentRoom(int direction, String image) {
        images.put(direction, image);
    }

    //Set room and image
    public void setAdjacentRoom(int direction, String image, Room room) {
        images.put(direction, image);
        rooms.put(direction, room);
    }

    public Room getAdjacentRoom(int direction) {
        return rooms.get(Math.floorMod(direction, DIRECTIONS));
    }

    public String getImage(int direction) {
        return images.get(Math.floorMod(direction, DIRECTIONS));
    }
    
    public int getId(){
        return id;
    }
}
