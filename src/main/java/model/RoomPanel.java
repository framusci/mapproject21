/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author franc
 */
class RoomPanel {
    private String image;
    private Map<Integer, Boolean> events;
    private Room nextRoom;
    
    public RoomPanel(String image){
        events = new HashMap<>();
        this.image = image;
    }
    
    public RoomPanel(String image, Room nextRoom){
        events = new HashMap<>();
        this.image = image;
        this.nextRoom = nextRoom;
    }

    //Fare classe pubblica a parte (perché GameGUI la deve vedere)
    public enum Event {
        EVENT_SAMPLE
    }

    public String getImage() {
        return image;
    }

    public boolean hasHappened(Event e) {
        return events.get(e.ordinal());
    }

    public void addEvent(Event e) {
        events.put(e.ordinal(), false);
    }

    public void removeEvent(Event e) {
        events.remove(e.ordinal());
    }

    public void makeHappen(Event e) {
        events.replace(e.ordinal(), true);
    }
    
    public void setNextRoomId(Room nextRoom){
        this.nextRoom = nextRoom;
    }
    
    public Room getNextRoom(){
        return nextRoom;
    }
}
