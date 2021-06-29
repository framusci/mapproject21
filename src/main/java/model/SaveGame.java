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
    private int roomId;
    private List<String> inventory;
    
    public SaveGame(String name, int facingDirection, int roomId, List inventory) {
        this.name = name;
        this.facingDirection = facingDirection;
        this.roomId = roomId;
        this.inventory = inventory;
    }
    
    public String getPlayerName(){
        return name;
    }
    
    public int getFacingDirection(){
        return facingDirection;
    }
    
    public int getRoomId(){
        return roomId;
    }
    
    public List getInventory(){
        return inventory;
    }
}
