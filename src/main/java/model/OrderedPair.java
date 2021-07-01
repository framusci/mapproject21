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
public class OrderedPair<U, V> {
    private U first;
    private V second;
    
    public OrderedPair(U first, V second){
        this.first = first;
        this.second = second;
    }
    
    public U getFirst(){
        return first;
    }
    
    public V getSecond(){
        return second;
    }
}
