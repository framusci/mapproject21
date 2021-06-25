/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author franc
 */
public interface GameCoreInterface {
    Object getFacingImage();

    int getFacingDirection();
    
    void addToInventory(Object item);
    
    void removeFromInventory(Object item);
    
    void setPlayerName(String name);
    
    Object getPlayerInventory();
    
    Object getPlayerName();
    
    Object getNextDialogue();

    void setDialogueDatabase(String dbURL, String user, String password);
    
    Object getObservation();

    void loadDialogue();

    void setDialogueLanguage(String language);

    Object getDialogueLanguage();

    void turnRight();
    
    void turnLeft();
    
    void walkForward();

    void walkBackwards();

    void save();

    void load();
}
