/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author franc
 */
class GameMap {
    static Room start;
    static Room streetEmporium;
    static Room emporium;
    static Room kid;
    private Map<Integer, Room> rooms;
    
    public static final int NORTH = 0;
    public static final int WEST = 1;
    public static final int SOUTH = 2;
    public static final int EAST = 3;
    
    public GameMap() {
        start = new Room(1);
        streetEmporium = new Room(2);
        emporium = new Room(3);
        kid = new Room(4);
        
        rooms = new HashMap<>();
        rooms.put(start.getId(), start);
        rooms.put(streetEmporium.getId(), streetEmporium);
        rooms.put(emporium.getId(), emporium);
        rooms.put(kid.getId(), kid);
        
        start.setAdjacentRoom(EAST, "1_E.png");
        start.setAdjacentRoom(SOUTH, "1_S.png", kid);
        start.setAdjacentRoom(WEST, "1_W.png");
        start.setAdjacentRoom(NORTH, "1_N.png", streetEmporium);

        streetEmporium.setAdjacentRoom(SOUTH, "2_S.png", start);
        streetEmporium.setAdjacentRoom(EAST, "2_E.png", emporium);
        streetEmporium.setAdjacentRoom(NORTH, "2_N.png");
        streetEmporium.setAdjacentRoom(WEST, "2_W.png");

        emporium.setAdjacentRoom(NORTH, "3_N.png");
        emporium.setAdjacentRoom(EAST, "3_E.png");
        emporium.setAdjacentRoom(SOUTH, "3_S.png");
        emporium.setAdjacentRoom(WEST, "3_W.png", streetEmporium);
        
        kid.setAdjacentRoom(NORTH, "test_N.png", start);
        kid.setAdjacentRoom(WEST, "test_W.png");
        kid.setAdjacentRoom(SOUTH, "test_S.png");
        kid.setAdjacentRoom(EAST, "test_E.png");
    }
    
    public Room getStartingRoom(){
        return start;
    }
    
    public Room getRoomById(final int roomId){
        return rooms.get(roomId);
    }
}
