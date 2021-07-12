package util;

import java.util.ArrayList;

/**
 *
 * @author franc
 * @param <E>
 */
public class CircularArrayList<E> extends ArrayList<E> implements CircularIterator<E> {

    /**
     *
     */
    private int index;

    /**
     *
     */
    public CircularArrayList() {
        index = 0;
    }

    /**
     *
     * @return
     */
    @Override
    public E previous() {
        if (--index < 0) {
            index = super.size() - 1;
        }

        return super.get(index);
    }

    /**
     *
     * @return
     */
    @Override
    public E next() {
        if (++index == super.size()) {
            index = 0;
        }

        return super.get(index);
    }

    /**
     *
     * @return
     */
    @Override
    public E current() {
        return super.get(index);
    }
}
