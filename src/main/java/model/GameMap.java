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
    static CircularList<Room> start = new CircularArrayList();
    static CircularList<Room> streetEmporium = new CircularArrayList();
    static CircularList<Room> emporium = new CircularArrayList();
    static CircularList<Room> kid = new CircularArrayList();
    private Map<Integer, Room> rooms;
    
    public static final int NORTH = 0;
    public static final int WEST = 1;
    public static final int SOUTH = 2;
    public static final int EAST = 3;
    
    public GameMap() {
    }
    
    public CircularList<Room> getStartingRoom(){
        return start;
    }
    
    public Room getRoomById(final int roomId){
        return rooms.get(roomId);
    }
}
