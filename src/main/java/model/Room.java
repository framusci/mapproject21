package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author franc
 */
class Room {
    private Map<Integer, String> rooms;
    private Map<Integer, String> images;
    private String name;
    private static final int DIRECTIONS = 4;

    public Room(String name) {
        rooms = new HashMap<>();
        images = new HashMap<>();
        this.name = name;
    }

    //Set room and image
    public void setAdjacentRoom(int direction, String room) {
        rooms.put(direction, room);
    }

    //Set only image (no adjacent room in that direction)
    public void setAdjacentImage(int direction, String roomImg) {
        images.put(direction, roomImg);
    }

    public String getAdjacentRoom(int direction) {
        return rooms.get(Math.floorMod(direction, DIRECTIONS));
    }

    public String getImage(int direction) {
        return images.get(Math.floorMod(direction, DIRECTIONS));
    }
    
    public String getName(){
        return name;
    }
}
