/**
 * Elia Phan
 * Board.java
 * CS231 SP23 Project 5
 * last modified 03/17/2023
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

//TODO: everything done & tested

/**
 * PURPOSE:
 * The Board class holds the array of Cells that make up the Sudoku board.
 * It will be able to read a board from a file, test if a value is valid at a certain position on the board,
 * and test if the board is solved.Cell class, each Cell will represent one location in a Sudoku board.
 */

public class Board {
    public boolean finished = false;
    private Cell[][] arr;
    private Cell[][] arrOriginal; //stores the original board before solving with staring values
    public int size;


    /**
     * constructor: creates a size x size 2D array of Cells, all of which are initialized to have value 0
     */
    public Board(int size) {
        this.size = size;
        if (Math.sqrt(size) != (int) Math.sqrt(size) || size < 4){
            System.out.println("size must be a perfect square that equals at least 4.");
        }
        else {
            arr = new Cell[size][size];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = new Cell(i+1, j+1, 0);
                }
            }
        }
    }



    /**
     * auxiliary constructor takes in a String filename and call the read method.
     */
    public Board(int size, String filename) {
        this.size = size;
        if (Math.sqrt(size) != (int) Math.sqrt(size)|| size < 4){
            System.out.println("size must be a perfect square that equals at least 4.");
        }
        else {
            arr = new Cell[size][size];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = new Cell(i+1, j+1, 0);
                }
            }
            read(filename);
        }
    }


    /**
     * auxiliary constructor takes an int parameter to specify the number of initial cells to be locked
     */
    public Board(int size, int numOfLocked) {
        this.size = size;

        if (Math.sqrt(size) != (int) Math.sqrt(size)|| size < 4){
            System.out.println("size must be a perfect square that equals at least 4.");
        }
        else {

            Random rand = new Random();
            arr = new Cell[size][size];

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = new Cell(i + 1, j + 1, 0);
                }
            }

            for (int count = 0; count < numOfLocked; count++) {
                int row = rand.nextInt(1, size + 1);
                int col = rand.nextInt(1, size + 1);
                while (get(row, col).isLocked() == true){
                    row = rand.nextInt(1, size + 1);
                    col = rand.nextInt(1, size + 1);
                }
                get(row, col).setValue(rand.nextInt(1, 10));
                while (!validValue(row, col, get(row, col).getValue())) {
                    get(row, col).setValue(rand.nextInt(1, 10));
                }
                get(row, col).setLocked(true);
            }

            //create a deep copy of the original board
            arrOriginal = new Cell[arr.length][arr.length];

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    Cell cell = new Cell(i+1, j+1, arr[i][j].getValue(), true);
                    arrOriginal[i][j] = cell ;
                }
            }
        }
    }



    /**
     * read in a Sudoku board
     */
    public boolean read(String filename) {
        try {
            // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
            BufferedReader br = new BufferedReader(fr);

            // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
            String line = br.readLine();

            int rowNum = 0;
            // start a while loop that loops while line isn't null
            while (line != null) {
                // print line
                //System.out.println(line);
                // assign to an array of Strings the result of splitting the line up by spaces (line.split("[ ]+"))
                String[] array = line.split("[ ]+");
                // print the size of the String array (you can use .length)
                //System.out.println("size of arr: " + array.length);
                // use the line to set various Cells of this Board accordingly
                int colNum = 0;
                while (colNum < array.length) {
                    arr[rowNum][colNum] = new Cell(rowNum, colNum, Integer.valueOf(array[colNum]));
                    colNum = colNum + 1;
                }
                rowNum = rowNum + 1;
                // assign to line the result of calling the readLine method of your BufferedReader object.
                line = br.readLine();
            }
            // call the close method of the BufferedReader
            br.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }

        return false;
    }


    /**
     * generates a multi-line string representation of the board
     */
    public String toString() {
        int square = (int) Math.sqrt(size);
        if (square != 0) {
            String output = "\n[";
            for (int i = 0; i < arr.length; i++) {
                output = output + "\n";
                if (i > 0 && i < arr.length && i % square == 0) {
                    output = output + "\n";
                }
                for (int j = 0; j < arr[i].length; j++) {
                    if (j > 0 && j < arr.length && j % square == 0) {
                        output = output + " ";
                    }
                    output = output + new DecimalFormat("00").format(arr[i][j].getValue()) + " ";
                }
            }
            output = output + "\n]";
            return output;
        }
        else return null;
    }


    /**
     * returns the number of columns
     */
    public int getCols(){
        return size;
    }


    /**
     * returns the original board with starting values
     */
    public String getArrOriginal(){
        int square = (int) Math.sqrt(size);
        String output = "\n[";
        for (int i = 0; i < arrOriginal.length; i++) {
            output = output + "\n";
            if (i > 0 && i < 9 && i%square == 0){
                output = output + "\n";
            }
            for (int j = 0; j < arrOriginal[i].length; j++) {
                if (j > 0 && j < 9 && j%square == 0){
                    output = output + " ";
                }
                output = output + arrOriginal[i][j] + " ";
            }
        }
        output = output + "\n]";
        return output;
    }



    /**
     * returns the number of rows
     */
    public int getRows(){
        return size;
    }


    /**
     * returns the Cell at location r, c
     */
    public Cell get(int r, int c){
        return arr[r-1][c-1];
    }


    /**
     * returns whether the Cell at r, c, is locked
     */
    public boolean isLocked(int r, int c){
        return get(r,c).isLocked();
    }


    /**
     * returns the number of locked Cells on the board
     */
    public int numOfLocked(){
        int output = 0;
        for (Cell[] row : arr){
            for (Cell cell : row) {
                if (cell.isLocked()) {
                    output = output + 1;
                }
            }
        }
        return output;
    }


    /**
     * returns the value at Cell r, c
     */
    public int value(int r, int c){
        return get(r,c).getValue();
    }


    /**
     *  sets the value of the Cell at r, c
     */
    public void set(int r, int c, int value){
        if (!get(r,c).isLocked()) {
            get(r,c).setValue(value);
        }
    }


    /**
     * sets the value and locked fields of the Cell at r, c
     */
    public void set(int r, int c, int value, boolean locked){
        get(r,c).setValue(value);
        get(r,c).setLocked(locked);
    }


    /**
     *  tests if the given value is a valid value at the given row and column of the board.
     *  make sure the value is in the range [1,size], unique in its row, in its column, and in its local size x size square.
     */
    public boolean validValue(int row, int col, int value) {
        int square = (int) Math.sqrt(size);

        //check if value is not out of range of 1 to size
        if (value > size || value < 1) {
            //System.out.println("hereA");
            return false;
        }

        //check if there is identical value in the same row
        for (int i = 1; i <= size; i++) {
            if (i != col && value(row, i) == value) {
                //System.out.println("hereB");
                //System.out.println("original item at: " + row + ", " + col + " & identical value at: " + row + ", " + i);
                return false;
            }
        }

        //check if there is identical value in the same column
        for (int i = 1; i <= size; i++) {
            if (i != row && value(i, col) == value) {
                //System.out.println("hereC");
                //System.out.println("original item at: " + row + ", " + col + " & identical value at: " + i + ", " + col);
                return false;
            }
        }

        //check if there are identical values in the surrounded square x square area
        if (square != 0){
            int groupCol = (col - 1)/square;
            int groupRow = (row - 1)/square;
            //System.out.println("groupCol: " + groupCol + " groupRow: " + groupRow);
            for (int i = groupRow*square+1; i <= groupRow*square + square; i++){
                for (int j = groupCol*square+1; j <= groupCol*square + square; j++){
                    if (i == row & j == col){
                        continue;
                    }
                    if (value == get(i,j).getValue()){
                        return false;
                    }
                }
            }
            return true;
        }
        else return false;
    }


    /**
     * returns true if the board is solved
     * looping over all the Cells on the board.
     * If all the Cells are between 1 and size and all the Cells are valid, the board is solved.
     */
    public boolean validSolution(){
        for (int i = 1; i < getRows(); i++){
            for (int j = 1; j < getCols(); j++){
                if (value(i,j) > size){
                    //System.out.println("here1");
                    return false;
                }
                else if (value(i,j) < 1){
                    //System.out.println("here2");
                    return false;
                }
                else if (!validValue(i, j, value(i,j))){
                    //System.out.println("here3");
                    return false;
                }
            }
        }
        return true;
    }



    /**
     * draws the board
     */
    public void draw(Graphics g, int scale){
        int square = (int) Math.sqrt(size);

        for(int i = 1; i<=getRows(); i++){
            for(int j = 1; j<=getCols(); j++){
                get(i, j).draw(g, j*scale+5, i*scale+10, scale);
            }
        } if(finished){
            if(validSolution()){
                g.setColor(new Color(0, 127, 0));
                g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale*square+square, 20);
            } else {
                g.setColor(new Color(127, 0, 0));
                g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale*square+square, 20);
            }
        }
    }



    /**
     * test
     */
    public static void main(String[] args){
        //test Board()
        Board b = new Board(9, "board1.txt");
        System.out.println(b);

        //test getCols()
        System.out.println(b.getCols() + " == 9");

        //test getRows()
        System.out.println(b.getRows() + " == 9");

        //test get()
        System.out.println((b.get(4,7) instanceof Cell) + " == true");

        //test isLocked() and set()
        System.out.println(b.get(3,6).isLocked() + " == false");
        b.set(3,6,2, true);
        System.out.println(b.get(3,6).isLocked() + " == true");

        //test value() and set()
        System.out.println(b.value(4,7) + " == 9");
        System.out.println(b.value(1,1) + " == 0");
        b.set(4,7, 8);
        System.out.println(b.value(4,7) + " == 8");

        //test validValue() and validSolution()
        Board b3 = new Board(9, "board3.txt");
        System.out.println(b3.validSolution() + " == true");

        //test validValue() and validSolution()
        Board b1 = new Board(4, 0);
        b1.set(1,1,7);
        System.out.println("b1: " + b1);
        System.out.println(b1.validSolution() + " == false");
    }
}
