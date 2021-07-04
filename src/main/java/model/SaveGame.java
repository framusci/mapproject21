/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author franc
 */
class SaveGame {
    private String name;
    private int facingDirection;
    private String roomName;
    private List<String> inventory;
    
    public SaveGame(String name, int facingDirection, String roomName, List inventory) {
        this.name = name;
        this.facingDirection = facingDirection;
        this.roomName = roomName;
        this.inventory = inventory;
    }
    
    public String getPlayerName(){
        return name;
    }
    
    public int getFacingDirection(){
        return facingDirection;
    }
    
    public String getRoomName(){
        return roomName;
    }
    
    public List getInventory(){
        return inventory;
    }
}
