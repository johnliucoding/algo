package goodrich.ch3;

import lombok.Getter;
import lombok.Setter;

public class DoublyLinkedList<E> {

  @Getter
  private static class Node<E> {
    private final E element;
    @Setter
    private Node<E> prev;
    @Setter
    private Node<E> next;

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
    this.header.setNext(this.trailer);
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
    return header.getNext().getElement();
  }

  public E getLast() {
    if (isEmpty())
      return null;
    return trailer.getPrev().getElement();
  }

  private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
    var newest = new Node<>(e, predecessor, successor);
    predecessor.setNext(newest);
    successor.setPrev(newest);
    size++;
  }

  private E remove(Node<E> node) {
    var predecessor = node.getPrev();
    var successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrev(predecessor);
    size--;
    return node.getElement();
  }

  public void addFront(E e) {
    addBetween(e, header, header.getNext());
  }

  public void addEnd(E e) {
    addBetween(e, trailer.getPrev(), trailer);
  }

  public E removeFront() {
    if (isEmpty())
      return null;
    return remove(header.getNext());
  }

  public E removeEnd() {
    if (isEmpty())
      return null;
    return remove(trailer.getPrev());
  }
}
