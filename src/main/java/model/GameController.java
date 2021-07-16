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

/**
 * Una possibile implementazione dell'interfaccia <tt>AdventureGame</tt>, con dei metodi aggiuntivi.
 */
public class GameController implements AdventureGame {

    private Dialogue dialogue;
    private Player player;
    private GameMap map;
    private Map<String, Object> saveGame;
    private Map<String, Boolean> events;

    /**
     * Inizializza un game controller vuoto.
     */
    public GameController() {
        dialogue = new Dialogue();
        player = new Player();
        map = new GameMap();
        events = new HashMap();
    }

    /**
     * Imposta il nome del giocatore.
     * 
     * @param name Il nome del giocatore.
     */
    @Override
    public void setPlayerName(String name) {
        player.setName(name);
    }

    /**
     * Restituisce il nome del giocatore.
     * 
     * @return Il nome del giocatore.
     */
    @Override
    public String getPlayerName() {
        return player.getName();
    }

    /**
     * Restituisce la lista contenente l'inventario del giocatore.
     * 
     * @return La lista contenente l'inventario del giocatore.
     */
    @Override
    public List getPlayerInventory() {
        return player.getInventory();
    }
    
    /**
     * Aggiunge alla mappa una stanza, composta da pannelli.
     * 
     * @param panels Le immagini che rappresentano i pannelli.
     */
    @Override
    public void addRoom(String... panels) {
        CircularIterator<String> room = new CircularArrayList();
        room.addAll(Arrays.asList(panels));
        map.addRoom(room);
    }

    /**
     * Aggiunge un collegamento tra due immagini (e quindi due stanze), permettendo il movimento del giocatore.
     * 
     * @param first L'immagine che il giocatore guarda prima di iniziare il movimento.
     * @param second L'immagine che il giocatore guarda quando il movimento è finito.
     */
    @Override
    public void addEdge(String first, String second) {
        map.addEdge(first, second);
    }

    /**
     * Cammina nella direzione corrente.
     * Se non c'è nessuna stanza nella direzione corrente, non succede nulla.
     * 
     * @return L'immagine che il giocatore guarda quando il movimento è finito.
     */
    @Override
    public String walk() {
        return map.walk();
    }

    /**
     * Volta la visuale del giocatore a destra.
     * 
     * @return L'immagine che il giocatore guarda quando il movimento è finito.
     */
    @Override
    public String turnRight() {
        return map.next();
    }

    /**
     * Volta la visuale del giocatore a sinistra.
     * 
     * @return L'immagine che il giocatore guarda quando il movimento è finito.
     */
    @Override
    public String turnLeft() {
        return map.previous();
    }
    
    /**
     * Aggiunge un evento alla mappa degli eventi, inizializzandolo col valore di default <tt>false</tt>
     * 
     * @param event Il nome dell'evento.
     */
    @Override
    public void addEvent(String event){
        events.put(event, false);
    }
    
    /**
     * Restituisce <tt>true</tt> se l'evento è avvenuto, <tt>false</tt> altrimenti.
     * 
     * @param event L'evento preso in esame.
     * @return <tt>true</tt> se l'evento è avvenuto, <tt>false</tt> altrimenti.
     */
    @Override
    public boolean hasHappened(String event){
        return events.get(event);
    }
    
    /**
     * Fa avvenire l'evento specificato, settando il valore a <tt>true</tt>
     * 
     * @param event L'evento che si vuole far avvenire.
     */
    @Override
    public void makeHappen(String event){
        events.put(event, true);
    }
    
    /**
     * Inizializza i dialoghi.
     */
    public void initDialogues() {
        dialogue.init();
    }
    
    /**
     * Aggiunge alla mappa una stanza, composta da pannelli.
     * Imposta la stanza come prima stanza.
     * 
     * @param panels Le immagini che rappresentano i pannelli.
     */
    public void addFirstRoom(String... panels) {
        CircularIterator<String> room = new CircularArrayList();
        room.addAll(Arrays.asList(panels));
        map.addRoom(room);
        map.setCurrentRoom(room.current());
    }

    /**
     * Restituisce il nome dell'immagine corrente.
     * 
     * @return Il nome dell'immagine corrente.
     */
    public String currentImage() {
        return map.current();
    }

    /**
     * Aggiunge un dialogo alla tabella dei dialoghi.
     * 
     * @param id L'id del dialogo.
     * @param text Il testo del dialogo.
     */
    public void addDialogue(int id, String text) {
        dialogue.addDialogue(id, text);
    }

    /**
     * Imposta il database in cui sono memorizzati i dialoghi.
     * 
     * @param dbURL L'URL del database.
     * @param dbUser Lo username del database.
     * @param dbPassword La password del database.
     */
    public void setDialogueDatabase(String dbURL, String dbUser, String dbPassword) {
        dialogue.setDatabase(dbURL, dbUser, dbPassword);
    }

    /**
     * Imposta il separatore delle frasi dei dialoghi.
     * Per ulteriori informazionni, vedere @see model.Dialogue.Class#setSeparator(String s) .
     * 
     * @param s Il separatore delle frasi.
     */
    public void setDialogueSeparator(String s) {
        dialogue.setSeparator(s);
    }

    public List loadDialogue(int id) {
        return dialogue.getDialogue(id);
    }
    
    /**
     * Salva la partita allo stato corrente.
     * Crea nella cartella di installazione il file <tt>saveGame.json</tt>
     */
    @Override
    public void save() {
        saveGame = new HashMap();

        saveGame.put("name", player.getName());
        saveGame.put("currentImage", map.current());
        saveGame.put("inventory", player.getInventory());
        saveGame.put("events", events);

        try {
            BufferedWriter outputStream = new BufferedWriter(new FileWriter("saveGame.json"));
            outputStream.write(new Gson().toJson(saveGame));
            outputStream.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
    
    /**
     * Carica una partita salvata.
     * Carica il file <tt>saveGame.json</tt> dalla cartella di installazione.
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
        map.setCurrentRoom((String) saveGame.get("currentImage"));
        player.setInventory((List) saveGame.get("inventory"));
        events = (Map) saveGame.get("events");
    }
}