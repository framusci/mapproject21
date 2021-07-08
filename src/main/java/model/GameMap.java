package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.CircularIterator;

class GameMap {

    private List<CircularIterator> rooms;
    private Map<String, String> edges;
    private CircularIterator<String> tmpRoom;

    public GameMap() {
        rooms = new ArrayList();
        edges = new HashMap();
    }

    public void addRoom(CircularIterator panels) {
        rooms.add(panels);
    }

    public void addEdge(String first, String last) {
        edges.put(first, last);
    }

    public CircularIterator searchRoom(CircularIterator<String> currentRoom) {
        tmpRoom = currentRoom;
        
        if (edges.containsKey(tmpRoom.current())) {
            String next = edges.get(tmpRoom.current());

            rooms.stream().filter(ci -> (ci.contains(next))).forEach(ci -> {
                tmpRoom = ci;
                
                while (!tmpRoom.current().equals(next)) {
                    tmpRoom.next();
                }
            });
        }

        return tmpRoom;
    }
}
