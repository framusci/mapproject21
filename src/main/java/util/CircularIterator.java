package util;

import java.util.Collection;

public interface CircularIterator<E> extends Collection<E> {
    public E next();
    public E previous();
    public E current();
}
