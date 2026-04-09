package goodrich.ch3;

import lombok.Getter;
import lombok.Setter;

public class CircularlyLinkedList<E> {
  @Getter
  private static class Node<E> {
    private final E element;
    @Setter
    private Node<E> next;

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
    return tail.getNext().getElement();
  }

  public E getLast() {
    if(isEmpty()) return null;
    return tail.getElement();
  }

  public void addFront(E e) {
    if(this.size == 0 ){
      this.tail = new Node<>(e, null);
      this.tail.setNext(this.tail);
    } else {
      var newest = new Node<>(e, tail.getNext());
      tail.setNext(newest);
    }
    this.size++;
  }

  public void addLast(E e) {
    addFront(e);
    // rotation operation
    this.tail = this.tail.getNext();
  }

  public E removeFont() {
    if(isEmpty()) return null;
    var head = this.tail.getNext();
    if(head == this.tail) {
      this.tail = null;
    } else {
      tail.setNext(head.getNext());
    }
    this.size--;
    return head.getElement();
  }

}
