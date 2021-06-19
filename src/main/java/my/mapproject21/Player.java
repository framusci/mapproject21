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
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
/**
 *
 * @author franc
 */
//Possibile fare Player extends Character
public class Player implements Serializable {
    private String name;
    private List<Item> inventory;
    private static final int DIRECTIONS = 4;
    
    private int direction;
    
    public Player(int direction){
        this.direction = direction;
        this.inventory = new ArrayList<>();
    }
    
    public Player(){
        this.direction = 0;
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
    
    public String getName(){
        return this.name;
    }
    
    public void takeItem(Item item){
        this.inventory.add(item);
    }
    
    public void leaveItem(Item item){
        this.inventory.remove(item);
    }
    
    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream("savePlayer.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in savegame.ser");
        } catch (IOException i) {
            //Rivedere catch
            i.printStackTrace();
        }
    }

    public Player load() {
        try {
            FileInputStream fileIn = new FileInputStream("savePlayer.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            //Possibile fare this = (this) in.readObject();
            Player pl = (Player) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Serialized data loaded");
            return pl;
        } catch (IOException i) {
            //Rivedere questi catch
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Core class not found");
            c.printStackTrace();
            return null;
        }
    }
}
