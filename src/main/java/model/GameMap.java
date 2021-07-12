package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.CircularIterator;

/**
 *
 * @author franc
 */
class GameMap {

    /**
     *
     */
    private List<CircularIterator> rooms;

    /**
     *
     */
    private Map<String, String> edges;

    /**
     *
     */
    private CircularIterator<String> tmpRoom;

    /**
     *
     */
    public GameMap() {
        rooms = new ArrayList();
        edges = new HashMap();
    }

    /**
     *
     * @param panels
     */
    public void addRoom(CircularIterator panels) {
        rooms.add(panels);
    }

    /**
     *
     * @param first
     * @param last
     */
    public void addEdge(String first, String last) {
        edges.put(first, last);
    }

    /**
     *
     * @param currentImage
     * @return
     */
    public CircularIterator nextRoom(String currentImage) {
        String next;

        if (edges.containsKey(currentImage)) {
            next = edges.get(currentImage);
            tmpRoom = getRoom(next);

            while (!tmpRoom.current().equals(next)) {
                tmpRoom.next();
            }
        }

        return tmpRoom;
    }

    /**
     *
     * @param currentImage
     * @return
     */
    public CircularIterator getRoom(String currentImage) {
        rooms.stream().filter(ci -> (ci.contains(currentImage))).forEach(ci -> {
            tmpRoom = ci;
        });

        return tmpRoom;
    }
}
