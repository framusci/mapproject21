package model;

import java.util.List;
import java.util.ArrayList;

class Player {

    private String name;
    private List<String> inventory;
    
    public Player() {
        inventory = new ArrayList<>();
    }

    public Player(String name) {
        this.name = name;
        inventory = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getInventory() {
        return inventory;
    }

    public void setInventory(List inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void takeItem(String item) {
        inventory.add(item);
    }

    public void leaveItem(String item) {
        inventory.remove(item);
    }
}
