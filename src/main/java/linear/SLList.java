package linear;

public class SLList<T> implements List<T> {


    private Node<T> head;
    private int size;
    public static class Node<T> {
        public T item;
        public Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * create an empty SLList
     */
    public SLList() {
        head = null;
        size = 0;
    }



    @Override
    public void addFirst(T x) {
        head = new Node(x, head);
        size++;
    }

    @Override
    public void addLast(T x) {
        size++;

        // special case for empty list
        if(head == null) {
            head = new Node(x, null);
            return;
        }

        Node<T> p = head;
        // advance p to the end of the list
        while (p.next != null) {
            p = p.next;
        }

        p.next = new Node(x, null);
    }

    @Override
    public T getFirst() {
        if(head == null) {
            return null;
        }
        return head.item;
    }

    /**
     * retrieves the front item from the list
     * @return
     */
    @Override
    public T getLast() {
        // special case for empty list
        if(head == null) {

            return null;
        }

        Node<T> p = head;
        // advance p to the end of the list
        while (p.next != null) {
            p = p.next;
        }

        return p.item;
    }

    @Override
    public T removeLast() {
        // special case for empty list
        if(head == null) {

            return null;
        }
        // special case for single list
        if(size == 1) {
            size--;
            T tem = head.item;

            head = null;
            return tem;
        }

        Node<T> p = head;
        Node<T> n = p.next;

        // advance n to the end of the list
        while ( n.next != null) {
            p = p.next;
            n = n.next;
        }
        size--;
        p.next = null;
        return n.item;
    }

    @Override
    public T get(int i) {
        if(i < 0 || i > size - 1) {
            throw new RuntimeException("out of range");
        }

        Node<T> p = head;
        int index = 0;
        // advance p to the end of the list
        while (p.next != null && index < i) {
            p = p.next;
            index++;
        }

        return p.item;
    }

    @Override
    public void insert(T x, int p) {
        if(p < 0 || p > size) {
            throw new RuntimeException("out of range");
        }
        if(p == 0) {
            addFirst(x);
        }
        int pre = p - 1;

        Node<T> node = head;
        int index = 0;
        // advance p to the end of the list
        while (node.next != null && index < pre) {
            node = node.next;
            index++;
        }

        final Node<T> newNode = new Node<>(x, null);

        newNode.next = node.next;

        node.next = newNode;

        size++;

    }



    @Override
    public int size() {
        return size;
    }
}
