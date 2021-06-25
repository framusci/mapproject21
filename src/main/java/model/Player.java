package model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author franc
 */

class Player {
    private String name;
    private List<String> inventory;
    private static final int DIRECTIONS = 4;
    
    private int facingDirection;
    
    public Player(int direction){
        this.facingDirection = direction;
        inventory = new ArrayList<>();
    }
    
    public Player() {
        this.facingDirection = 0;
        inventory = new ArrayList<>();
    }
    
    public void setFacingDirection(int direction){
        this.facingDirection = Math.floorMod(direction, DIRECTIONS);
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getFacingDirection(){
        return facingDirection;
    }
    
    public List getInventory(){
        return inventory;
    }
    
    public void setInventory(List inventory){
        this.inventory = inventory;
    }
    
    public String getName(){
        return name;
    }
    
    public void takeItem(String item){
        inventory.add(item);
    }
    
    public void leaveItem(String item){
        inventory.remove(item);
    }
}
