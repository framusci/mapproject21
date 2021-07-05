package engine;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GameController {

    //Player movement
    public static final int LEFT = 1;
    public static final int RIGHT = -1;
    public static final int FORWARD = 0;
    public static final int BACKWARDS = 2;

    private Room currentRoom;
    private Player player;
    private Dialogue dialogue;

    private Map<Integer, Boolean> events;

    public GameController() {
        player = new Player();
        dialogue = new Dialogue();
        events = new HashMap<>();
    }
    
    void setStartingRoom(Room start){
        currentRoom = start;
    }

    public void addDialogue(int id, String text) {
        dialogue.addDialogue(id, text);
    }
    
    public void addObservation(int id, String text){
        dialogue.addObservation(id, text);
    }
    
    public void addItemDescription(String itemName, String description){
        dialogue.addItem(itemName, description);
    }
    
    public String loadItemDescription(String itemName){
        return dialogue.getItem(itemName);
    }
    
    public String loadObservation(int id){
        return dialogue.getObservation(id);
    }

    public void setDialogueDatabase(String dbURL, String dbUser, String dbPassword) {
        dialogue.setDatabase(dbURL, dbUser, dbPassword);
    }

    public void setDialogueSeparator(String s) {
        dialogue.setSeparator(s);
    }

    /**
     * Adds the specified event to the event map
     *
     * @param evt
     */
    public void addEvent(int evt) {
        events.put(evt, false);
    }

    public void setPlayerName(String name) {
        player.setName(name);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public List getPlayerInventory() {
        return player.getInventory();
    }
    
    public boolean isInInventory(String s){
        return player.getInventory().contains(s);
    }

    public void addToInventory(String item) {
        player.takeItem(item);
    }

    public void removeFromInventory(String item) {
        player.leaveItem(item);
    }

    public String getFacingImageFileName() {
        return currentRoom.getImage(player.getFacingDirection());
    }

    public int getFacingDirection() {
        return player.getFacingDirection();
    }

    public Iterator loadDialogue(int id) {
        return dialogue.getDialogue(id);
    }

    public void turnRight() {
        turn(RIGHT);
    }

    public void turnLeft() {
        turn(LEFT);
    }

    public void walkForward() {
        walk(FORWARD);
    }

    public void walkBackwards() {
        walk(BACKWARDS);
    }

    public void save() {
        SavePlayer savePlayer = new SavePlayer(player.getName(), player.getFacingDirection(), player.getInventory(), events);

        try {
            BufferedWriter outputStream = new BufferedWriter(new FileWriter("savePlayer.json"));
            outputStream.write(new Gson().toJson(savePlayer));
            outputStream.close();

            FileOutputStream outputFile = new FileOutputStream("saveMap.dat");
            ObjectOutputStream outStream = new ObjectOutputStream(outputFile);
            outStream.writeObject(currentRoom);
            outStream.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    public void load() {
        SavePlayer savePlayer = null;

        try {
            BufferedReader inputStream = new BufferedReader(new FileReader("savePlayer.json"));
            savePlayer = new Gson().fromJson(inputStream, SavePlayer.class);
            inputStream.close();

            FileInputStream inFile = new FileInputStream("saveMap.dat");
            ObjectInputStream inStream = new ObjectInputStream(inFile);
            currentRoom = (Room) inStream.readObject();
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }

        player.setName(savePlayer.getPlayerName());
        player.setFacingDirection(savePlayer.getFacingDirection());
        player.setInventory(savePlayer.getInventory());
    }

    private void turn(final int direction) {
        player.setFacingDirection(player.getFacingDirection() + direction);
    }

    private void walk(final int direction) {
        if (currentRoom.getAdjacentRoom((player.getFacingDirection()) + direction) != null) {
            currentRoom = currentRoom.getAdjacentRoom(player.getFacingDirection() + direction);
        }
    }

    public boolean hasHappened(int evt) {
        return events.get(evt);
    }

    public void makeHappen(int evt) {
        events.put(evt, true);
    }
    
    public String getEvent(){
        return String.valueOf(currentRoom.getId()) + String.valueOf(player.getFacingDirection());
    }

    private class SavePlayer {

        private String name;
        private int facingDirection;
        private List<String> inventory;
        private Map<Integer, Boolean> events;

        public SavePlayer(String name, int facingDirection, List<String> inventory, Map<Integer, Boolean> events) {
            this.name = name;
            this.facingDirection = facingDirection;
            this.inventory = inventory;
            this.events = new HashMap<>();
            this.events = events;
        }

        public String getPlayerName() {
            return name;
        }

        public int getFacingDirection() {
            return facingDirection;
        }

        public List getInventory() {
            return inventory;
        }
        
        public Map getEvents(){
            return events;
        }
    }
}
