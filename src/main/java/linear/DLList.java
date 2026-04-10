package linear;

public class DLList<T> {


    private Node<T> sentinel;
    private int size;

    public static class Node<T> {
        public Node<T> prev;
        public T item;
        public Node<T> next;

        public Node(Node<T> prev, T item, Node<T> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    /**
     * empty list
     */
    public DLList() {
        final Node<T> objectNode = new Node<>(null, null, null);
        objectNode.prev = objectNode;
        objectNode.next = objectNode;

        this.sentinel = objectNode;
        this.size = 0;
    }

    @Override
    public String toString() {

        final StringBuilder stringBuilder = new StringBuilder();
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            stringBuilder.append(p.item);
            stringBuilder.append(" -> ");
            p = p.next;
        }
        final String s = stringBuilder.toString();
        return s.substring(0, s.length() - 4);
    }

    public void addFirst(T item) {
        Node<T> node = new Node<>(null, item, null);
        addNode(sentinel, node);

    }


    public T removeFirst() {

        return removeNode(sentinel.next).item;
    }


    public void addLast(T item) {

        Node<T> node = new Node<>(null, item, null);
        addNode(sentinel.prev, node);
    }


    public T removeLast() {

        return removeNode(sentinel.prev).item;
    }

    private void addNode(Node<T> curr, Node<T> newnode) {
        newnode.next = curr.next;
        newnode.prev = curr;

        curr.next.prev = newnode;
        curr.next = newnode;

        size++;
    }

    private Node<T> removeNode(Node<T> curr) {
        if(curr == this.sentinel) {
            throw new RuntimeException("empty list");
        }
        size--;
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        curr.prev = null;
        curr.next = null;
        return curr;
    }
}
