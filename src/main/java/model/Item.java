package model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author franc
 */
class Item {
    private String name;
    private String lookDescription;
    
    public Item(String name, String lookDescription){
        this.name = name;
        this.lookDescription = lookDescription;
    }
    
    public String getName(){
        return name;
    }
    
    public String getLookDescription(){
        return lookDescription;
    }
}
