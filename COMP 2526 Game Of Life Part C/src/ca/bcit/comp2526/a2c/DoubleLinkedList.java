package ca.bcit.comp2526.a2c;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Doubly linked list class.
 * 
 * @author Vinh Le
 * @param <E>
 *            - Element Type
 * @version 1
 * 
 */
@SuppressWarnings("serial")
public class DoubleLinkedList<E> implements Iterable<E>, Serializable {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * This class keeps track of each element information.
     * 
     * @author Vinh Le
     * @param <E>
     *            - Generic varibale
     */
    public static class Node<E> implements Serializable {
        
        /**
         * Previous node object.
         */
        private Node<E> prev;
        
        /**
         * Next Node object.
         */
        private Node<E> next;

        /**
         * Node's data.
         */
        private E data;
        
        /**
         * Node constructor.
         * 
         * @param e
         *            - element
         */
        Node(E e) {
            this.data = e;
        }

        /**
         * Returning an int value which is the data of the Node.
         * @return an integer
         */
        public int intValue() {
            return (int) data;
        }
    }

    /**
     * Adding to the front of the linked list.
     * 
     * @param e
     *            - Element
     * @throws CouldNotAddException
     *             - Could not add exception
     */
    public void addToFront(E e) throws CouldNotAddException {
        if (e == null) {
            throw new CouldNotAddException("Could not add!!!");
        }
        Node<E> tmp = new Node<E>(e);
        if (head == null) {
            head = tmp;
            tail = tmp;
            size = 1;
        } else {
            head.prev = tmp;
            tmp.next = head;
            head = tmp;
            size++;
        }
    }

    /**
     * Removing the front object in the DoubleLinkedList. 
     * @return temp data
     * @throws CouldNotRemoveException
     *             - Could not remove exception
     */
    public E removeFromFront() throws CouldNotRemoveException {
        if (head == null) {
            throw new CouldNotRemoveException("Could not remove!!!");
        }
        Node<E> tmp = head;
        if (head == tail) {
            head = null;
            tail = null;
            size = 0;
        } else {
            head = head.next;
            head.prev = null;
            size--;
        }
        return tmp.data;
    }

    /**
     * Adding a Node object to the end of the DoubleLinkedList.
     * @param e
     *            - element
     * @throws CouldNotAddException
     *             - Could not add exception
     */
    public void addToEnd(E e) throws CouldNotAddException {
        if (e == null) {
            throw new CouldNotAddException("Could not add!!!");
        }
        Node<E> tmp = new Node<E>(e);
        if (tail == null) {
            tail = tmp;
            head = tmp;
            size = 1;
        } else {
            Node<E> current = tail;
            tail.next = tmp;
            tail = tmp;
            tail.prev = current;
            size++;
        }
    }

    /**
     * Deleting a Node at the end of the DoubleLinkedList.
     * @return deleted element
     * @throws CouldNotRemoveException
     *             - Could not remove exception
     */
    public E removeFromEnd() throws CouldNotRemoveException {
        if (tail == null) {
            throw new CouldNotRemoveException("Could not remove!!!");
        }
        Node<E> tmp = tail;
        if (head == tail) {
            head = null;
            tail = null;
            size = 0;
        } else {
            tail = tail.prev;
            tail.next = null;
            size--;
        }
        return tmp.data;
    }

    /**
     * Iterating through the DoubleLinkedList.
     * @return a new Iterator of Node<E>
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;

            }

            @Override
            public E next() {
                if (hasNext()) {
                    Node<E> temp = current;
                    current = current.next;
                    return temp.data;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * Getting the head Node of the DoubleLinkedList.
     * @return the head
     */
    public Node<E> getFirst() {
        return head;
    }

    /**
     * Getting the tail Node of the DoubleLinkedList.
     * @return the tail
     */
    public Node<E> getLast() {
        return tail;
    }

    /**
     * Getting the size of the DoubleLinkedList.
     * @return the size
     */
    public int size() {
        return size;
    }

    /**
     * Testing is the DoubleLinkedList is empty or not.
     * @param data - Node's data
     * @return true if it's empty
     *          false if it's not
     */
    public boolean isEmpty(E data) {
        return ((int) data) == 0;
    }
}
