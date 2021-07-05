package engine;

import java.util.List;

public interface CircularList<E> extends List<E> {
    public E next();
    public E previous();
    public E current();
}