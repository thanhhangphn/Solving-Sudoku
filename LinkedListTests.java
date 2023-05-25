/**
 * Elia Phan
 * LinkedListTests.java
 * CS231 SP23 Lab 5
 * last modified 03/07/2023
 * How to run:
 * javac LinkedListTests.java
 * java -ea LinkedListTests
 */


/**
 * PURPOSE:
 * Test methods of the LinkedList class
 */

public class LinkedListTests {

    public static void main(String[] args) {
        // case 1: testing LinkedList()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();

            // verify
            System.out.println(ll + " != null");

            // test
            assert ll != null : "Error in LinkedList::LinkedList()";
        }

        // case 2: testing add(T item)
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(i);
            }

            // verify
            System.out.println(ll.size() + " == 5");

            // test
            assert ll.size() == 5 : "Error in LinkedList::add(T item) or LinkedList::size()";
        }

        // case 3: testing add(int index, T item)
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            ll.add(0, 1);
            System.out.println(ll);
            ll.add(1, 2);
            System.out.println(ll);
            ll.add(1, 3);
            System.out.println(ll);
            ll.add(0, 4);
            System.out.println(ll);
            ll.add(4, 5);
            System.out.println(ll);
            ll.add(3, 6);
            System.out.println(ll);

            // verify
            System.out.println(ll.size() + " == 6");
            System.out.println(ll.get(0) + " == 4");
            System.out.println(ll.get(1) + " == 1");
            System.out.println(ll.get(2) + " == 3");
            System.out.println(ll.get(3) + " == 6");
            System.out.println(ll.get(4) + " == 2");
            System.out.println(ll.get(5) + " == 5");


            // test
            assert ll.get(0) == 4: "Error in LinkedList::add(int index, T item) or LinkedList::get(index)";
            assert ll.get(1) == 1: "Error in LinkedList::add(int index, T item) or LinkedList::get(index)";
            assert ll.get(2) == 3: "Error in LinkedList::add(int index, T item) or LinkedList::get(index)";
            assert ll.get(3) == 6: "Error in LinkedList::add(int index, T item) or LinkedList::get(index)";
            assert ll.get(4) == 2: "Error in LinkedList::add(int index, T item) or LinkedList::get(index)";
            assert ll.get(5) == 5: "Error in LinkedList::add(int index, T item) or LinkedList::get(index)";
            assert ll.size() == 6 : "Error in LinkedList::add(int index, T item) or LinkedList::size()";
        }

        // case 4: testing clear()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i : new int[] { 1, 2, 3 }) {
                ll.add(i);
            }
            ll.clear();

            // verify
            System.out.println(ll.size() + " == 0");

            // test
            assert ll.size() == 0 : "Error in LinkedList::clear()";
        }


        // case 5: testing equals()
        {
            // setup
            LinkedList<Integer> list1 = new LinkedList<Integer>();
            LinkedList<Integer> list2 = new LinkedList<Integer>();
            LinkedList<Integer> list3 = new LinkedList<Integer>();
            LinkedList<Integer> list4 = new LinkedList<Integer>();
            for (int i = 0; i < 3; i++) {
                list1.add(i);
                list2.add(i);
                list3.add(i);
                list4.add(i);
            }
            list3.add(4);
            list4.add(5);

            // verify
            System.out.println(list1.equals(list2) + " == true");
            System.out.println(list2.equals(list3) + " == false");
            System.out.println(list3.equals(list4) + " == false");
            System.out.println(list4.equals("Hello") + " == false");

            // test
            assert list1.equals(list2) : "Error in LinkedList::equals()";
            assert !list2.equals(list3) : "Error in LinkedList::equals()";
            assert !list3.equals(list4) : "Error in LinkedList::equals()";
            assert !list4.equals("Hello") : "Error in LinkedList::equals()";
        }


        // case 6: testing contains()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 3; i++) {
                ll.add(2 * i);
            }

            // verify
            System.out.println(ll.contains(0) + " == true");
            System.out.println(ll.contains(4) + " == true");
            System.out.println(ll.contains(3) + " == false");

            // test
            assert ll.contains(0) : "Error in LinkedList::contains()";
            assert ll.contains(4) : "Error in LinkedList::contains()";
            assert !ll.contains(3) : "Error in LinkedList::contains()";
        }


        // case 7: testing get()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(4-i);
            }

            // verify
            System.out.println(ll.get(0) + " == 0");
            System.out.println(ll.get(3) + " == 3");
            System.out.println(ll.get(4) + " == 4");

            // test
            assert ll.get(0) == 0 : "Error in LinkedList::get()";
            assert ll.get(3) == 3 : "Error in LinkedList::get()";
            assert ll.get(4) == 4 : "Error in LinkedList::get()";
        }

        // case 8: testing isEmpty()
        {
            // setup
            LinkedList<Integer> list1 = new LinkedList<Integer>();
            LinkedList<Integer> list2 = new LinkedList<Integer>();
            list2.add(5);

            // verify
            System.out.println(list1.isEmpty() + " == true");
            System.out.println(list2.isEmpty() + " == false");

            // test
            assert list1.isEmpty() : "Error in LinkedList::isEmpty()";
            assert !list2.isEmpty() : "Error in LinkedList::isEmpty()";
        }

        // case 9: testing remove()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(4-i);
            }

            int remove0 = ll.remove();
            int remove1 = ll.remove();

            // verify
            System.out.println(remove0 + " == 0");
            System.out.println(remove1 + " == 1");

            // test
            assert remove0 == 0 : "Error in LinkedList::remove()";
            assert remove1 == 1 : "Error in LinkedList::remove()";
        }

        // case 10: testing remove(int index)
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 8; i++) {
                ll.add(7-i);
            }
            int remove0 = ll.remove(0);
            int remove3 = ll.remove(3);
            int remove5 = ll.remove(5);

            // verify
            System.out.println(remove0 + " == 0");
            System.out.println(remove3 + " == 4");
            System.out.println(remove5 + " == 7");

            // test
            assert remove0 == 0 : "Error in LinkedList::remove()";
            assert remove3 == 4 : "Error in LinkedList::remove()";
            assert remove5 == 7 : "Error in LinkedList::remove()";
        }

        // case 11: testing offer()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 4; i >= 0; i--) {
                ll.offer(4-i);
                System.out.println(ll);
            }

            // verify
            System.out.println(ll.get(0) + " == 0");
            System.out.println(ll.get(3) + " == 3");
            System.out.println(ll.get(4) + " == 4");

            // test
            assert ll.get(0) == 0 : "Error in LinkedList::get() or LinkedList::offer()";
            assert ll.get(3) == 3 : "Error in LinkedList::get() or LinkedList::offer()";
            assert ll.get(4) == 4 : "Error in LinkedList::get() or LinkedList::offer()";
        }

        // case 12: testing peek()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(4-i);
            }

            // verify
            System.out.println(ll.peek() + " == 0");

            // test
            assert ll.peek() == 0 : "Error in LinkedList::peek()";
        }

        // case 12: testing poll()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(4-i);
            }

            // verify
            System.out.println(ll.peek() + " == 0");

            // test
            assert ll.poll() == 0 : "Error in LinkedList::poll()";
            assert ll.poll() == 1 : "Error in LinkedList::poll()";
            assert ll.poll() == 2 : "Error in LinkedList::poll()";
        }

        // case 13: testing add(int index, T item) and iterator()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            ll.add(0, 1);
            ll.add(1, 4);
            ll.add(1, 2);
            ll.add(0, 0);
            ll.add(4, 5);
            ll.add(3, 3);

            // verify
            int counter = 0;
            for (int val : ll) {
                System.out.println(val + " == " + counter);
                counter++;
            }

            // test
            counter = 0;
            for (int val : ll) {
                assert val == counter : "Error in LinkedList::add(int index, T item) or LinkedList::iterator()";
                counter++;
            }
        }

        // case 14: testing pop()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(4-i);
            }

            // verify
            System.out.println(ll.peek() + " == 0");

            // test
            assert ll.pop() == 0 : "Error in LinkedList::poll()";
            assert ll.pop() == 1 : "Error in LinkedList::poll()";
            assert ll.pop() == 2 : "Error in LinkedList::poll()";
        }


        // case 15: testing push()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.push(4-i);
            }

            // verify
            System.out.println(ll.peek() + " == 0");

            // test
            assert ll.pop() == 0 : "Error in LinkedList::poll()";
            assert ll.pop() == 1 : "Error in LinkedList::poll()";
            assert ll.pop() == 2 : "Error in LinkedList::poll()";
        }
        System.out.println("Done testing LinkedList!");
    }

}