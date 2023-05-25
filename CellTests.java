/**
 * Elia Phan
 * CellTests.java
 * CS231 SP23 Lab 5
 * last modified 03/19/2023
 * How to run:
 * javac LinkedListTests.java
 * java -ea LinkedListTests
 */

//TODO: everything done & tested

/**
 * PURPOSE:
 * Test methods of the Cell class
 */

public class CellTests {

    public static void main(String[] args) {

        // case 1: testing Cell() and Cell(row,col,val) and Cell(row,col,val,lock)
        {
            // set up
            Cell c1 = new Cell();
            Cell c2 = new Cell(3,2,4,true);
            Cell c3 = new Cell(5,6,7);

            // verify
            System.out.println(c1 + " == 0 Case 1");
            System.out.println(c2 + " == 4 Case 1");
            System.out.println(c3 + " == 7 Case 1");

            // test
            assert c1 != null : "Case 1 Error in Cell::Cell()";
            assert c2 != null : "Case 1 Error in Cell::Cell()";
            assert c3 != null : "Case 1 Error in Cell::Cell()";
        }

        // case 2: testing getRow() and getCol() and getValue() and isLocked()
        {
            // set up
            Cell c1 = new Cell(7,5,4,true);
            Cell c2 = new Cell(3,2,4,true);
            Cell c3 = new Cell(5,6,7);

            // verify
            System.out.println(c1.getCol() + " == 5 Case 2");
            System.out.println(c2.getRow() + " == 3 Case 2");
            System.out.println(c3.getValue() + " == 7 Case 2");
            System.out.println(c3.isLocked() + " == false Case 2");

            // test
            assert c1.getCol() == 5 : "Case 2 Error in Cell::Cell() or Cell::getCol()";
            assert c2.getRow() == 3 : "Case 2 in Cell::Cell() or Cell::getRow()";
            assert c3.getValue() == 7 : "Case 2 Error in Cell::Cell() or Cell::getValue()";
            assert c3.isLocked() == false : "Case 2 Error in Cell::Cell() or Cell::isLocked()";
        }

        // case 3: testing setValue() and setLocked()
        {
            // set up
            Cell c1 = new Cell();
            Cell c2 = new Cell(3,2,4,true);
            Cell c3 = new Cell(5,6,7);

            c1.setValue(5);
            c2.setLocked(false);
            c3.setValue(3);

            // verify
            System.out.println(c1.getValue() + " == 5 Case 3");
            System.out.println(c2.isLocked() + " == false Case 3");
            System.out.println(c3.getValue() + " == 3 Case 3");

            // test
            assert c1.getValue() == 5 : "Case 3 Error in Cell::getValue()";
            assert c2.isLocked() == false : "Case 3 Error in Cell::isLocked()";
            assert c3.getValue() == 3 : "Case 3 Error in Cell::getValue()";
        }
        System.out.println("Done testing LinkedList!");
    }
}