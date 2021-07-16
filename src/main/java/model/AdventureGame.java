package model;

import java.util.List;

/**
 * Interfaccia che descrive il comportamento di un'avventura grafica in prima persona.
 */
public interface AdventureGame {

    /**
     * Imposta il nome del giocatore.
     * 
     * @param name Il nome del giocatore.
     */
    public void setPlayerName(String name);
    
    /**
     * Restituisce il nome del giocatore.
     * 
     * @return Il nome del giocatore.
     */
    public String getPlayerName();
    
    /**
     * Restituisce una lista contenente l'inventario del giocatore
     * 
     * @return La lista contenente l'inventario del giocatore.
     */
    public List getPlayerInventory();
    
    /**
     * Aggiunge alla mappa una stanza, composta da pannelli.
     * 
     * @param panels Le immagini che rappresentano i pannelli.
     */
    public void addRoom(String... panels);
    
    /**
     * Aggiunge un collegamento tra due immagini (e quindi due stanze), permettendo il movimento del giocatore.
     * 
     * @param first L'immagine che il giocatore guarda prima di iniziare il movimento.
     * @param second L'immagine che il giocatore guarda quando il movimento è finito.
     */
    public void addEdge(String first, String second);
    
    /**
     * Cammina nella direzione corrente.
     * Se non c'è nessuna stanza nella direzione corrente, non succede nulla.
     * 
     * @return L'immagine che il giocatore guarda quando il movimento è finito.
     */
    public String walk();
    
    /**
     * Volta la visuale del giocatore a sinistra.
     * 
     * @return L'immagine che il giocatore guarda quando il movimento è finito.
     */
    public String turnLeft();
    
    /**
     * Volta la visuale del giocatore a destra.
     * 
     * @return L'immagine che il giocatore guarda quando il movimento è finito.
     */
    public String turnRight();
    
    /**
     * Aggiunge un evento alla mappa degli eventi, inizializzandolo col valore di default <tt>false</tt>
     * 
     * @param event Il nome dell'evento.
     */
    public void addEvent(String event);
    
    /**
     * Restituisce <tt>true</tt> se l'evento è avvenuto, <tt>false</tt> altrimenti.
     * 
     * @param event L'evento preso in esame.
     * @return <tt>true</tt> se l'evento è avvenuto, <tt>false</tt> altrimenti.
     */
    public boolean hasHappened(String event);
    
    /**
     * Fa avvenire l'evento specificato, settando il valore a <tt>true</tt>
     * 
     * @param event L'evento che si vuole far avvenire.
     */
    public void makeHappen(String event);
    
    /**
     * Salva la partita allo stato corrente.
     */
    public void save();
    
    /**
     * Carica una partita salvata.
     */
    public void load();
    
}