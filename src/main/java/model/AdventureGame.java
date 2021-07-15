package model;

import java.util.List;

/**
 *
 * @author franc
 */
public interface AdventureGame {

    public void setPlayerName(String name);
    public String getPlayerName();
    public List getPlayerInventory();
    public void addRoom(String... panels);
    public void addEdge(String first, String second);
    public String walk();
    public String turnLeft();
    public String turnRight();
    public void addEvent(String event);
    public boolean hasHappened(String event);
    public void makeHappen(String event);
    public void save();
    public void load();
    
}