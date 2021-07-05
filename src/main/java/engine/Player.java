package engine;

import java.util.List;
import java.util.ArrayList;

class Player {
    private String name;
    private List<String> inventory;
    private static final int DIRECTIONS = 4;
    
    private int facingDirection;
    
    public Player(int direction){
        facingDirection = direction;
        inventory = new ArrayList<>();
    }
    
    public Player() {
        facingDirection = 0;
        inventory = new ArrayList<>();
    }
    
    public Player(String name){
        facingDirection = 0;
        this.name = name;
        inventory = new ArrayList<>();
    }
    
    public Player(String name, int direction){
        facingDirection = direction;
        this.name = name;
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
