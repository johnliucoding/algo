package goodrich.ch3;




public class SinglyLinkedList<E> {


  private static class Node<E> {
    public final E element;

    public Node<E> next;

    public Node(E e, Node<E> n) {
      this.element = e;
      this.next = n;
    }
  }

  private Node<E> head = null;
  private Node<E> tail = null;
  private int size = 0;

  public SinglyLinkedList() {
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
    return head.element;
  }

  public E getLast() {
    if (isEmpty())
      return null;
    return tail.element;
  }

  public void addFront(E e) {
    this.head = new Node<>(e, this.head);
    if (this.size == 0) {
      this.tail = this.head;
    }
    this.size++;
  }

  public void addEnd(E e) {
    var newest = new Node<>(e, null);
    if (isEmpty()) {
      this.head = newest;
    } else {
      tail.next = newest;
    }
    this.tail = newest;
    this.size++;
  }

  public E removeFront() {
    if (isEmpty())
      return null;
    var ans = this.head.element;
    this.head = head.next;
    this.size--;
    if (size == 0) {
      tail = null;
    }
    return ans;
  }

}
