package my.mapproject21;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

/**
 *
 * @author franc
 */
public class Room implements Serializable {
    private Map<Integer, Room> rooms;
    private Map<Integer, String> images;
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
    
    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveRoom.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in savegame.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Room load() {
        try {
            FileInputStream fileIn = new FileInputStream("saveRoom.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Room room = (Room) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Serialized data loaded");
            return room;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Core class not found");
            c.printStackTrace();
            return null;
        }
    }
}
