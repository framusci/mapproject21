package engine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author franc
 */
class Room implements Serializable {
    private Map<Integer, Room> rooms;
    private transient Map<Integer, String> images;
    private static final int DIRECTIONS = 4;

    public Room() {
        rooms = new HashMap<>();
        images = new HashMap<>();
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
}
