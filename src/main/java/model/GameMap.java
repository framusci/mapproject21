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
    static Room start = new Room();
    static Room streetEmporium = new Room();
    static Room emporium = new Room();
    static Room kid = new Room();
    private Map<Integer, Room> rooms;
    
    public static final int NORTH = 0;
    public static final int WEST = 1;
    public static final int SOUTH = 2;
    public static final int EAST = 1;
    
    public GameMap() {
        rooms = new HashMap();
        
        start.addPanel(new RoomPanel("1_N.png", streetEmporium));
        start.addPanel(new RoomPanel("1_E.png"));
        start.addPanel(new RoomPanel("1_S.png", kid));
        start.addPanel(new RoomPanel("1_W.png"));
        
        streetEmporium.addPanel(new RoomPanel("2_N.png"));
        streetEmporium.addPanel(new RoomPanel("2_E.png", emporium));
        streetEmporium.addPanel(new RoomPanel("2_S.png", start));
        streetEmporium.addPanel(new RoomPanel("2_W.png"));
        
        kid.addPanel(new RoomPanel("test_N.png", start));
        kid.addPanel(new RoomPanel("test_E.png"));
        kid.addPanel(new RoomPanel("test_S.png"));
        kid.addPanel(new RoomPanel("test_W.png"));
        
        rooms.put(start.getId(), start);
        rooms.put(streetEmporium.getId(), streetEmporium);
        rooms.put(kid.getId(), start);
    }
    
    public Room getStartingRoom(){
        return start;
    }
    
    public Room getRoomById(int roomId){
        return rooms.get(roomId);
    }
}
