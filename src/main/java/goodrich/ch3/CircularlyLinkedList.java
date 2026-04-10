package goodrich.ch3;



public class CircularlyLinkedList<E> {

  private static class Node<E> {
    public final E element;

    public Node<E> next;

    public Node(E e, Node<E> n) {
      this.element = e;
      this.next = n;
    }
  }

  private Node<E> tail = null;
  private int size = 0;

  public void rotate() {
      if(!isEmpty()) {
          tail = tail.next;
      }
  }

  public int size() {return this.size;}
  public boolean isEmpty() {
    return this.size == 0;
  }

  public E getFirst() {
    if(isEmpty()) return null;
    return tail.next.element;
  }

  public E getLast() {
    if(isEmpty()) return null;
    return tail.element;
  }

  public void addFront(E e) {
    if(this.size == 0 ){
      this.tail = new Node<>(e, null);
      this.tail.next = this.tail;
    } else {
      var newest = new Node<>(e, tail.next);
      tail.next = newest;
    }
    this.size++;
  }

  public void addLast(E e) {
    addFront(e);
    // rotation operation
    this.tail = this.tail.next;
  }

  public E removeFont() {
    if(isEmpty()) return null;
    var head = this.tail.next;
    if(head == this.tail) {
      this.tail = null;
    } else {
      tail.next = head.next;
    }
    this.size--;
    return head.element;
  }

}
