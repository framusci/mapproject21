package my.gametest2;

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
public class Room {
    //Provare a usare delle code circolari.
    private Map<Integer, Room> rooms;
    private Map<Integer, String> images;
    //private Map<Integer, Room, String> evry = new HashMap<Integer, Room, String>();
    private static final int DIRECTIONS = 4;
    
    public Room(){
        this.rooms = new HashMap<>();
        this.images = new HashMap<>();
    }
    
    //Set room and image
    public void setAdjacentRoom(int direction, String roomImg, Room room){
        this.rooms.put(direction, room);
        this.images.put(direction, roomImg);
    }
    
    //Set only image (no adjacent room in that direction)
    public void setAdjacentRoom(int direction, String roomImg){
        this.images.put(direction, roomImg);
    }
    
    public Room getAdjacentRoom(int direction){
        return this.rooms.get(Math.floorMod(direction, DIRECTIONS));
    }
    
    public String getImage(int direction){
        return this.images.get(Math.floorMod(direction, DIRECTIONS));
    }
    
    
}
