package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.CircularIterator;

/**
 * Descrive la mappa di gioco.
 */
class GameMap {

    private List<CircularIterator> rooms;
    private Map<String, String> edges;
    private CircularIterator<String> currentRoom;

    /**
     * Costruisce una mappa vuota.
     */
    public GameMap() {
        rooms = new ArrayList();
        edges = new HashMap();
    }

    /**
     * Imposta la stanza corrente alla stanza che contiene l'immagine
     * specificata. Poi itera sulla stanza finché l'immagine corrente non è
     * uguale all'immagine specificata.
     *
     * @param image L'immagine che dovrà essere l'immagine corrente della stanza
     * corrente.
     */
    public void setCurrentRoom(String image) {
        currentRoom = getRoom(image);
        while (!currentRoom.next().equals(image));
    }

    /**
     * Aggiunge l'immagine specificata alla mappa.
     *
     * @param panels La stanza da aggiungere alla mappa.
     */
    public void addRoom(CircularIterator panels) {
        rooms.add(panels);
    }

    /**
     * Aggiunge un collegamento tra due immagini (e quindi due stanze),
     * permettendo il movimento del giocatore.
     *
     * @param first L'immagine che il giocatore guarda prima di iniziare il
     * movimento.
     * @param last L'immagine che il giocatore guarda quando il movimento è
     * finito.
     */
    public void addEdge(String first, String last) {
        edges.put(first, last);
    }

    /**
     * Restituisce il nome dell'immagine successiva della stanza corrente.
     *
     * @return L'immagine successiva.
     */
    public String next() {
        return currentRoom.next();
    }

    /**
     * Restituisce il nome dell'immagine precedente della stanza corrente.
     *
     * @return Il nome dell'immagine precedente.
     */
    public String previous() {
        return currentRoom.previous();
    }

    /**
     * Restituisce il nome dell'immagine corrente della stanza corrente.
     *
     * @return Il nome dell'immagine corrente.
     */
    public String current() {
        return currentRoom.current();
    }

    /**
     * Restituisce il nome dell'immagine di destinazione (che diventa l'immagine
     * corrente) e imposta la stanza corrente all'immagine di destinazione.
     *
     * @return Il nome dell'immagine corrente.
     */
    public String walk() {
        String currentImage = currentRoom.current();
        String next;

        if (edges.containsKey(currentImage)) {
            next = edges.get(currentImage);
            currentRoom = getRoom(next);

            while (!currentRoom.next().equals(next)); //Itera finché il cursore non si posiziona sull'immagine corretta.
        }

        return this.current();
    }

    /**
     * Restituisce la stanza che contiene <tt>image</tt>.
     *
     * @param image Il nome dell'immagine contenuta nella stanza.
     * @return La stanza che contiene <tt>image</tt>.
     */
    public CircularIterator getRoom(String image) {
        rooms.stream().filter(ci -> (ci.contains(image))).forEach(ci -> {
            currentRoom = ci;
        });

        return currentRoom;
    }
}
