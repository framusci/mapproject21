package model;

import java.util.List;

/**
 *
 * @author franc
 */
public interface AdventureGame {

    /**
     *
     * @param name
     */
    public void setPlayerName(String name);

    /**
     *
     * @return
     */
    public String getPlayerName();

    /**
     *
     * @return
     */
    public List getPlayerInventory();

    /**
     *
     * @param panels
     */
    public void addRoom(String... panels);

    /**
     *
     * @param first
     * @param second
     */
    public void addEdge(String first, String second);

    /**
     *
     * @return
     */
    public String walk();

    /**
     *
     * @return
     */
    public String turnLeft();

    /**
     *
     * @return
     */
    public String turnRight();

    /**
     *
     */
    public void save();

    /**
     *
     */
    public void load();
}