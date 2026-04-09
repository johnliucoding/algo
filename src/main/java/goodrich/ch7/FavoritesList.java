package goodrich.ch7;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Liu Xiaodong
 * @since 2024/8/18 9:44 PM
 */
public class FavoritesList<E> {
    static class Item<E> {
        E value;
        int count = 0;
        public Item(E value) {
            this.value = value;
        }

        public void increment() {
            count++;
        }
    }
    LinkedPositionalList<Item<E>> list =  new LinkedPositionalList<Item<E>>();

    public FavoritesList() {

    }

    private E value(Position<Item<E>> p) {
        return p.getElement().value;
    }

    private int count(Position<Item<E>> p) {
        return p.getElement().count;
    }

    private Position<Item<E>> findPosition(E e) {
        Position<Item<E>> walk = list.first();
        while(walk != null && !e.equals(value(walk))) {
            walk = list.after(walk);
        }
        return walk;
    }

    private void moveUp(Position<Item<E>> p) {
        int cnt = count(p);
        Position<Item<E>> walk = p;
        while(walk != list.first() && count(list.before(walk)) < cnt) {
            walk = list.before(walk);
        }
        if(walk != p) {
            list.addBefore(walk, list.remove(p));
        }
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void access(E e) {
        Position<Item<E>> p = findPosition(e);
        if(p == null) {
            p = list.addLast(new Item<>(e));
        }
        p.getElement().increment();
        moveUp(p);
    }

    public void remove(E e) {
        final Position<Item<E>> p = findPosition(e);
        if(p != null) {
            list.remove(p);
        }
    }

    public Iterable<E> getFavorites(int k) throws IllegalArgumentException {
        if(k < 0 || k > size()) {
            throw new IllegalArgumentException("invalid k");
        }
        final LinkedList<E> result = new LinkedList<>();
        final Iterator<Item<E>> iter = list.iterator();
        for(int i = 0; i < k; i++) {
            result.addLast(iter.next().value);
        }
        return result;
    }
}
