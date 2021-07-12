package model;

import util.CircularIterator;
import util.CircularArrayList;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.function.Predicate;

/**
 *
 * @author franc
 */
public class GameController implements AdventureGame {

    /**
     *
     */
    private Dialogue dialogue;

    /**
     *
     */
    private Player player;

    /**
     *
     */
    private GameMap map;
    
    /**
     *
     */
    private Map<String, Object> saveGame;

    /**
     *
     */
    private CircularIterator<String> currentRoom;

    /**
     *
     */
    public GameController() {
        currentRoom = new CircularArrayList();
        dialogue = new Dialogue();
        player = new Player();
        map = new GameMap();
    }

    /**
     *
     * @param name
     */
    @Override
    public void setPlayerName(String name) {
        player.setName(name);
    }

    /**
     *
     * @return
     */
    @Override
    public String getPlayerName() {
        return player.getName();
    }

    /**
     *
     */
    public void initDialogues() {
        dialogue.init();
    }

    /**
     *
     * @return
     */
    @Override
    public List getPlayerInventory() {
        return player.getInventory();
    }

    /**
     *
     * @param panels
     */
    @Override
    public void addRoom(String... panels) {
        CircularIterator<String> room = new CircularArrayList();
        room.addAll(Arrays.asList(panels));
        map.addRoom(room);
    }

    /**
     *
     * @param panels
     */
    public void addFirstRoom(String... panels) {
        CircularIterator<String> room = new CircularArrayList();
        room.addAll(Arrays.asList(panels));
        map.addRoom(room);
        
        currentRoom = room;
    }

    /**
     *
     * @param first
     * @param second
     */
    @Override
    public void addEdge(String first, String second) {
        map.addEdge(first, second);
    }

    /**
     *
     * @return
     */
    @Override
    public String walk() {
        currentRoom = map.nextRoom(currentRoom.current());
        return currentRoom.current();
    }

    /**
     *
     * @return
     */
    @Override
    public String turnRight() {
        return currentRoom.next();
    }

    /**
     *
     * @return
     */
    @Override
    public String turnLeft() {
        return currentRoom.previous();
    }

    /**
     *
     * @return
     */
    public String currentImage() {
        return currentRoom.current();
    }

    /**
     *
     * @param id
     * @param text
     */
    public void addDialogue(int id, String text) {
        dialogue.addDialogue(id, text);
    }
    
    /**
     *
     * @param dbURL
     * @param dbUser
     * @param dbPassword
     */
    public void setDialogueDatabase(String dbURL, String dbUser, String dbPassword) {
        dialogue.setDatabase(dbURL, dbUser, dbPassword);
    }

    /**
     *
     * @param s
     */
    public void setDialogueSeparator(String s) {
        dialogue.setSeparator(s);
    }

    /**
     *
     * @param id
     * @return
     */
    public List loadDialogue(int id) {
        return dialogue.getDialogue(id);
    }

    /**
     *
     */
    @Override
    public void save() {
        saveGame = new HashMap();

        saveGame.put("name", player.getName());
        saveGame.put("currentImage", currentRoom.current());
        saveGame.put("inventory", player.getInventory());

        try {
            BufferedWriter outputStream = new BufferedWriter(new FileWriter("saveGame.json"));
            outputStream.write(new Gson().toJson(saveGame));
            outputStream.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    /**
     *
     */
    @Override
    public void load() {
        saveGame = new HashMap();

        try {
            BufferedReader inputStream = new BufferedReader(new FileReader("saveGame.json"));
            saveGame = new Gson().fromJson(inputStream, Map.class);
            inputStream.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        player.setName(saveGame.get("name").toString());
        currentRoom = map.getRoom((String) saveGame.get("currentImage"));

        player.setInventory((List) saveGame.get("inventory"));
    }
}