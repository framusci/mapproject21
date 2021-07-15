package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.CircularIterator;

class GameMap {

    private List<CircularIterator> rooms;

    private Map<String, String> edges;
    
    private CircularIterator<String> currentRoom;

    public GameMap() {
        rooms = new ArrayList();
        edges = new HashMap();
    }
    
    public void setCurrentRoom(CircularIterator panels){
        currentRoom = panels;
    }

    public void addRoom(CircularIterator panels) {
        rooms.add(panels);
    }
    
    public void addEdge(String first, String last) {
        edges.put(first, last);
    }
    
    public String next(){
        return currentRoom.next();
    }
    
    public String previous(){
        return currentRoom.previous();
    }
    
    public String current(){
        return currentRoom.current();
    }

    public String walk() {
        String currentImage = currentRoom.current();
        String next;

        if (edges.containsKey(currentImage)) {
            next = edges.get(currentImage);
            currentRoom = getRoom(next);

            while (!currentRoom.current().equals(next)) {
                currentRoom.next();
            }
        }

        return this.current();
    }

    public CircularIterator getRoom(String image) {
        rooms.stream().filter(ci -> (ci.contains(image))).forEach(ci -> {
            currentRoom = ci;
        });

        return currentRoom;
    }
}
