package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class ImplIterableWithPolicy<T> implements IterableWithPolicy<T>{
    
    private final List<T> items;
    private Predicate<T> filter;

    public ImplIterableWithPolicy(T[] array){
        this(
            array,
            new Predicate<T>() {
                public boolean test(T elem){
                    return true;
                }
            });
    }

    public ImplIterableWithPolicy(T[] array, Predicate<T> predicate){
        this.items = List.of(array);
        this.filter = predicate;
    }

    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    public Iterator<T> iterator() {
        return new IteratorWithPolicy();
    }
    
    private class IteratorWithPolicy implements Iterator<T>{
        private int index = 0;
        
        public boolean hasNext() {
            while(index < items.size() && !filter.test(items.get(index))){
                index++;
            }
            return index < items.size();
        }
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return items.get(index++);     
        }   
    }
}
