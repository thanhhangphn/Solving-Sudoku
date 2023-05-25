/**
 * Elia Phan
 * LinkedList.java
 * CS231 SP23 Lab 5
 * based on Colby College CS Department's original outline
 * last modified 03/07/2023
 */

/**
 * PURPOSE:
 * LinkedLists class that implements Queue, Iterable, and Stack
 */

import java.util.ArrayList;
import java.util.Iterator;

public class LinkedList<T> implements Stack<T>, Queue<T>, Iterable<T>{

    Node head; //the head of the linked list
    int size; //number of items in the linked list

    private class Node {
        private Node next;
        private T container;


        /**
         * a constructor that initializes next to null and the container field to item.
         */
        public Node(T item) {
            next = null;
            container = item;
        }

        public T getData() { //returns the value of the container field.
            return container;
        }

        public void setNext(Node n) { //sets next to the given node.
            next = n;
        }

        public Node getNext() { //returns the next field.
            return next;
        }
    }


    /**
     * constructor that initializes the fields so that it is an empty list.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }


    /**
     * returns the size of the list.
     */
    public int size() {
        return size;
    }


    /**
     * returns true if the list is empty, otherwise this method returns false.
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * empties the list (resets the fields so it is an empty list).
     */
    public void clear() {
        head = null;
        size = 0;
    }


    /**
     * inserts the item at the beginning of the list.
     */
    public void add(T item) {
        Node node = new Node(item);
        if (size() > 0) {
            node.setNext(head);
        }
        head = node;
        size++;
    }


    /**
     * removes the first item of the list and returns it.
     */
    public T remove() {
        Node temp = head;
        head = head.getNext();
        size--;
        return temp.getData();
    }


    /**
     * returns the item specified by the given index.
     */
    public T get(int index) {
        Node output = head;
        for (int i = 0; i < index; i++){
            output = output.getNext();
        }
        return output.container;
    }


    /**
     * inserts the item at the specified position in the list.
     */
    public void add(int index, T item) {
        if (index == 0){
            add(item);
        }
        else if (index == 1){
            Node newNode = new Node(item);
            newNode.setNext(head.getNext());
            head.setNext(newNode);
            size++;
        }
        else{
            Node temp = head;
            Node newNode = new Node(item);
            for (int i = 0; i < index-1; i++){
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
            size++;
        }
    }


    /**
     * removes the item at the specified position in the list and returns it.
     */
    public T remove(int index){
        T output1 = get(index);
        if (index == 0){
            remove();
        }
        else if (index == 1){
            head.setNext(head.getNext().getNext());
        }
        else{
            Node temp = head;
            for (int i = 0; i < index-2; i++){
                temp = temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());
        }
        size--;
        return output1;
    }


    /**
     * returns true if o is present in this list, otherwise this method returns false.
     */
    public boolean contains(Object o){
        for (int i = 0; i < size(); i++){
            System.out.println(i + " " + get(i));
            if (get(i).equals(o)){
                return true;
            }
        }
        return false;
    }


    /**
     * returns true if o is a LinkedList that also contains the same items in the same order.
     */
    public boolean equals(Object o){
        if (!(o instanceof LinkedList)){
            return false;
        }
        LinkedList oAsALinkedList = (LinkedList) o; // Now I have a reference to something Java knows is a LinkedList!

        Node temp = head;
        Node temp1 = oAsALinkedList.head;
        for (int i = 0; i < Math.max(size(), oAsALinkedList.size()); i++){
            if (temp.getData() != temp1.getData()){
                return false;
            }
            temp = temp.getNext();
            temp1 = temp1.getNext();
        }
        return true;
    }


    /**
     * handles traversing the linked list
     */
    private class LLIterator implements Iterator<T> {
        Node nextNode;


        /**
         * the constructor for the LLIterator given the head of a list.
         */
        public LLIterator(Node head) {
            nextNode = head;
        }


        /**
         * returns true if there are still values to traverse (if the current node reference is not null).
         */
        public boolean hasNext(){
            return nextNode != null;
        }


        /**
         * returns the next item in the list, which is the item contained in the current node.
         * The method also needs to move the traversal along to the next node in the list.
         */
        public T next() {
            T output = nextNode.getData();
            nextNode = nextNode.getNext();
            return output;
        }


        /**
         * does nothing. Implementing this function is optional for an Iterator.
         */
        public void remove() {
            {
                throw new UnsupportedOperationException();
            }
        }
    }


    /**
     * Return a new LLIterator pointing to the head of the list
     */
    public Iterator<T> iterator() {
        return new LLIterator(this.head);
    }


    /**
     * returns a String representation of the list.
     */
    public String toString(){
        String output = "";
        Node temp = head;
        for (int i = 0; i < size(); i++){
            output += temp.getData() + " ";
            temp = temp.getNext();
        }
        return output;
    }


    /**
     * converts the LinkedList to an ArrayList with the items in the same order
     */
    public ArrayList<T> toArrayList(){
        ArrayList arr = new ArrayList<T>();
        Node item = head;
        for (int i = 0; i< size(); i++){
            arr.add(item.getData());
            item = item.getNext();
        }
        return arr;
    }


    /**
     * insert new element at the end of the queue
     */
    @Override
    public void offer(T item) {
        if (size() == 0){
            add(item);
        }
        else {
            add(size(), item);
        }
    }


    /**
     * returns the element at the front the queue (or on the top of the stack)
     */
    @Override
    public T peek() {
        return get(0);
    }


    /**
     * return and remove the element at the front of the queue
     */
    @Override
    public T poll() {
        return remove();
    }


    /**
     * removes and returns the item on the top of the stack
     */
    public T pop(){
        return remove();
    }


    /**
     * adds the given item to the top of the stack
     */
    public void push(T item){
        add(item);
    }
}