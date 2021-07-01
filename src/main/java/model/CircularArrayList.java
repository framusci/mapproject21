/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author franc
 */
public class CircularArrayList<E> extends ArrayList implements CircularList {
    private int index;
    
    public CircularArrayList(){
        index = 0;
    }
    
    @Override
    public Object previous(){
        if(index < 0){
            index = super.size() - 1;
        }
        
        return super.get(index--);
    }
    
    @Override
    public Object next(){
        if(index == super.size()){
            index = 0;
        }
        
        return super.get(index++);
    }
}
