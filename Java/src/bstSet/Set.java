package bstSet;

public interface Set<E> {
    void Add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}