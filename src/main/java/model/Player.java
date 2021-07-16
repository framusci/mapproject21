package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Descrive il giocatore.
 */
class Player {

    private String name;
    private List<String> inventory;

    /**
     * Costruisce un player con inventario vuoto e inizializza il nome a <tt>""</tt>.
     */
    public Player() {
        name = "";
        inventory = new ArrayList<>();
    }

    /**
     * Costruisce un player con inventario vuoto e inizializza il nome a <tt>name</tt>
     * 
     * @param name Il nome del giocatore.
     */
    public Player(String name) {
        this.name = name;
        inventory = new ArrayList<>();
    }

    /**
     * Imposta il nome del giocatore.
     * 
     * @param name Il nome del giocatore.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce l'inventario del giocatore.
     * 
     * @return L'inventario del giocatore.
     */
    public List getInventory() {
        return inventory;
    }

    /**
     * Imposta l'inventario del giocatore.
     * 
     * @param inventory La lista che sarà l'inventario del giocatore.
     */
    public void setInventory(List inventory) {
        this.inventory = inventory;
    }

    /**
     * Restituisce il nome del giocatore.
     * 
     * @return Il nome del giocatore.
     */
    public String getName() {
        return name;
    }
}
