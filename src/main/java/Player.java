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
//Possibile fare Player extends Character
public class Player {
    private String name;
    private List<Item> inventory;
    private static final int DIRECTIONS = 4;
    public static final int LEFT = 1;
    public static final int RIGHT = -1;
    
    private int direction;
    
    public Player(int direction){
        this.direction = direction;
        this.inventory = new ArrayList<>();
    }
    
    public void setFacingDirection(int direction){
        this.direction = Math.floorMod(direction, DIRECTIONS);
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getFacingDirection(){
        return direction;
    }
    
    public void takeItem(Item item){
        this.inventory.add(item);
    }
    
    public void leaveItem(Item item){
        this.inventory.remove(item);
    }
}
