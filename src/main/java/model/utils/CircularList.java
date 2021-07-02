/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.utils;

import java.util.List;

/**
 *
 * @author franc
 */
public interface CircularList<E> extends List<E> {
    public E next();
    public E previous();
    public E current();
}