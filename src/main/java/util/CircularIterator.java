package util;

import java.util.Collection;

/**
 *
 * @author franc
 * @param <E>
 */
public interface CircularIterator<E> extends Collection<E> {

    /**
     *
     * @return
     */
    public E next();

    /**
     *
     * @return
     */
    public E previous();

    /**
     *
     * @return
     */
    public E current();
}
