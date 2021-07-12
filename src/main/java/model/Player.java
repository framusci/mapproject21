package model;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author franc
 */
class Player {

    /**
     *
     */
    private String name;

    /**
     *
     */
    private List<String> inventory;
    
    /**
     *
     */
    public Player() {
        inventory = new ArrayList<>();
    }

    /**
     *
     * @param name
     */
    public Player(String name) {
        this.name = name;
        inventory = new ArrayList<>();
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public List getInventory() {
        return inventory;
    }

    /**
     *
     * @param inventory
     */
    public void setInventory(List inventory) {
        this.inventory = inventory;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param item
     */
    public void takeItem(String item) {
        inventory.add(item);
    }

    /**
     *
     * @param item
     */
    public void leaveItem(String item) {
        inventory.remove(item);
    }
}
