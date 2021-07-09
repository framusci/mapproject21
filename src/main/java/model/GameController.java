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

public class GameController implements AdventureGame {

    private Dialogue dialogue;
    private Player player;
    private GameMap map;
    
    private Map<String, Object> saveGame;
    private CircularIterator<String> currentRoom;

    public GameController() {
        currentRoom = new CircularArrayList();
        dialogue = new Dialogue();
        player = new Player();
        map = new GameMap();
    }

    @Override
    public void setPlayerName(String name) {
        player.setName(name);
    }

    @Override
    public String getPlayerName() {
        return player.getName();
    }

    public void initDialogues() {
        dialogue.init();
    }

    @Override
    public List getPlayerInventory() {
        return player.getInventory();
    }

    @Override
    public void addRoom(String... panels) {
        CircularIterator<String> room = new CircularArrayList();
        room.addAll(Arrays.asList(panels));
        map.addRoom(room);
    }

    public void addFirstRoom(String... panels) {
        CircularIterator<String> room = new CircularArrayList();
        room.addAll(Arrays.asList(panels));
        map.addRoom(room);
        
        currentRoom = room;
    }

    @Override
    public void addEdge(String first, String second) {
        map.addEdge(first, second);
    }

    @Override
    public String walk() {
        currentRoom = map.searchRoom(currentRoom);
        return currentRoom.current();
    }

    @Override
    public String turnRight() {
        return currentRoom.next();
    }

    @Override
    public String turnLeft() {
        return currentRoom.previous();
    }

    public String currentImage() {
        return currentRoom.current();
    }

    public void addDialogue(int id, String text) {
        dialogue.addDialogue(id, text);
    }

    public void addObservation(int id, String text) {
        dialogue.addObservation(id, text);
    }

    public void addItemDescription(String itemName, String description) {
        dialogue.addItem(itemName, description);
    }

    public String loadItemDescription(String itemName) {
        return dialogue.getItem(itemName);
    }

    public String loadObservation(int id) {
        return dialogue.getObservation(id);
    }

    public void setDialogueDatabase(String dbURL, String dbUser, String dbPassword) {
        dialogue.setDatabase(dbURL, dbUser, dbPassword);
    }

    public void setDialogueSeparator(String s) {
        dialogue.setSeparator(s);
    }

    public List loadDialogue(int id) {
        return dialogue.getDialogue(id);
    }

    @Override
    public void save() {
        saveGame = new HashMap();

        saveGame.put("name", player.getName());
        saveGame.put("currentImage", currentRoom.current());
        saveGame.put("currentRoom", currentRoom);
        saveGame.put("inventory", player.getInventory());

        try {
            BufferedWriter outputStream = new BufferedWriter(new FileWriter("saveGame.json"));
            outputStream.write(new Gson().toJson(saveGame));
            outputStream.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

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
        currentRoom.clear();
        currentRoom.addAll((List) saveGame.get("currentRoom"));

        while (!currentRoom.current().equals(saveGame.get("currentImage"))) {
            currentRoom.next();
        }

        player.setInventory((List) saveGame.get("inventory"));
    }
}