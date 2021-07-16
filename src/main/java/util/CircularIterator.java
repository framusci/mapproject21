package util;

import java.util.Collection;

/**
 * Un iteratore circolare su una collezione.
 * "Circolare" vuol dire che l'elemento che segue l'ultimo elemento è il primo elemento
 * e l'elemento che precede il primo elemento è l'ultimo elemento.
 * 
 * @param <E> Il tipo di elementi su cui opera l'iteratore.
 */
public interface CircularIterator<E> extends Collection<E> {
    
    /**
     * Restituisce l'elemento successivo nell'iterazione.
     * 
     * @return L'elemento successivo nell'iterazione.
     */
    public E next();
    
    /**
     * Restituisce l'elemento precedente nell'iterazione.
     * 
     * @return L'elemento precedente nell'iterazione.
     */
    public E previous();
    
    /**
     * Restituisce l'elemento corrente nell'iterazione.
     * 
     * @return L'elemento corrente nell'iterazione.
     */
    public E current();
}
