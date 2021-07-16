package util;

import java.util.ArrayList;

/**
 * Implementazione di <tt>CircularIterator</tt> con una lista. Si avvale della classe
 * già esistente <tt>ArrayList</tt>, estendendola e effettuando l'override dell'operatore <tt>get</tt>.
 * 
 * @param <E> Il tipo di elementi su cui opera la lista.
 */
public class CircularArrayList<E> extends ArrayList<E> implements CircularIterator<E> {
    
    private int index;

    /**
     * Inizializza l'indice dell'iterazione a zero.
     */
    public CircularArrayList() {
        index = 0;
    }
    
    /**
     * Restituisce l'elemento che si trova in posizione equivalente
     * al resto di <tt>i</tt> diviso il numero degli elementi nella lista.
     * 
     * @param i L'indice dell'elemento da restituire.
     * @return L'elemento nella posizione specificata.
     */
    @Override
    public E get(int i){
        return super.get(Math.floorMod(i, super.size()));
    }

    /**
     * Restituisce l'elemento precedente nell'iterazione.
     * 
     * @return L'elemento precedente nell'iterazione.
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
     * Restituisce l'elemento successivo nell'iterazione.
     * 
     * @return L'elemento successivo nell'iterazione.
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
     * Restituisce l'elemento corrente nell'iterazione.
     * 
     * @return L'elemento corrente nell'iterazione.
     */
    @Override
    public E current() {
        return this.get(index);
    }
}
