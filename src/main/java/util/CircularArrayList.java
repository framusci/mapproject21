package util;

import java.util.ArrayList;

public class CircularArrayList<E> extends ArrayList<E> implements CircularIterator<E> {
    
    private int index;

    public CircularArrayList() {
        index = 0;
    }
    
    @Override
    public E get(int i){
        return super.get(Math.floorMod(i, super.size()));
    }

    @Override
    public E previous() {
        try {
            index = Math.decrementExact(index);
        } catch (ArithmeticException aex){
            index = Math.floorMod(Integer.MIN_VALUE, super.size());
        }
        
        return this.get(index);
    }
    
    @Override
    public E next() {
        try {
            index = Math.incrementExact(index);
        } catch (ArithmeticException aex){
            index = Math.floorMod(Integer.MAX_VALUE, super.size());
        }
        
        return this.get(index);
    }

    @Override
    public E current() {
        return this.get(index);
    }
}
