package util;

import java.util.ArrayList;

/**
 *
 * @author franc
 * @param <E>
 */
public class CircularArrayList<E> extends ArrayList<E> implements CircularIterator<E> {
    
    private int index;

    /**
     *
     */
    public CircularArrayList() {
        index = 0;
    }
    
    @Override
    public E get(int i){
        return super.get(Math.floorMod(i, super.size()));
    }

    /**
     *
     * @return
     */
    @Override
    public E previous() {
        try {
            index = Math.decrementExact(index);
        } catch (ArithmeticException aex){
            index = Math.floorMod(Integer.MIN_VALUE, super.size());
        }
        
        return this.get(index);
    }

    /**
     *
     * @return
     */
    @Override
    public E next() {
        try {
            index = Math.incrementExact(index);
        } catch (ArithmeticException aex){
            index = Math.floorMod(Integer.MAX_VALUE, super.size());
        }
        
        return this.get(index);
    }

    /**
     *
     * @return
     */
    @Override
    public E current() {
        return this.get(index);
    }
}
