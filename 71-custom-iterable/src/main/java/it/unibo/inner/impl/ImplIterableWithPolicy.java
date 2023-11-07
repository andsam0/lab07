package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class ImplIterableWithPolicy<T> implements IterableWithPolicy<T>{
    
    private T[] array;

    public ImplIterableWithPolicy(T[] array){
        this.array = array;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorWithPolicy();
    }
    private class IteratorWithPolicy implements Iterator<T>{
        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < ImplIterableWithPolicy.this.array.length;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T actualElem = ImplIterableWithPolicy.this.array[index];
            index++;
            return actualElem;     
        }
        
    }
}
