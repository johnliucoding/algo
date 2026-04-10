package goodrich.ch3;



public class DoublyLinkedList<E> {


  private static class Node<E> {
    public final E element;

    public Node<E> prev;

    public Node<E> next;

    public Node(E e, Node<E> p, Node<E> n) {
      this.element = e;
      this.prev = p;
      this.next = n;
    }
  }

  private final Node<E> header;
  private final Node<E> trailer;
  private int size = 0;

  public DoublyLinkedList() {
    this.header = new Node<>(null, null, null);
    this.trailer = new Node<>(null, header, null);
    this.header.next = this.trailer;
  }

  public int size() {
    return this.size;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public E getFirst() {
    if (isEmpty())
      return null;
    return header.next.element;
  }

  public E getLast() {
    if (isEmpty())
      return null;
    return trailer.prev.element;
  }

  private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
    var newest = new Node<>(e, predecessor, successor);
    predecessor.next = newest;
    successor.prev = newest;
    size++;
  }

  private E remove(Node<E> node) {
    var predecessor = node.prev;
    var successor = node.next;
    predecessor.next = successor;
    successor.prev = predecessor;
    size--;
    return node.element;
  }

  public void addFront(E e) {
    addBetween(e, header, header.next);
  }

  public void addEnd(E e) {
    addBetween(e, trailer.prev, trailer);
  }

  public E removeFront() {
    if (isEmpty())
      return null;
    return remove(header.next);
  }

  public E removeEnd() {
    if (isEmpty())
      return null;
    return remove(trailer.prev);
  }
}
