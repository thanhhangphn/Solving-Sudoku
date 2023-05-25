/**
 * Elia Phan
 * LinkedListTests.java
 * CS231 SP23 Project 5
 * last modified 03/17/2023
 */

import java.awt.Graphics;
import java.awt.Color;
import java.text.DecimalFormat;

//TODO: everything done & tested

/**
 * PURPOSE:
 * Cell class, each Cell will represent one location in a Sudoku board.
 */

public class Cell {
    private int row;
    private int col;
    private int val;
    private boolean isLocked;


    /**
     * constructor: initialize all values to 0 or false
     */
    public Cell() {
        row = 0;
        col = 0;
        val = 0;
        isLocked = false;
    }


    /**
     * constructor: initialize the row, column, and value fields to the given parameter values. Set the locked field to false;
     */
    public Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.val = value;
        isLocked = false;
    }


    /**
     * constructor: initialize all of the Cell fields given the parameters.
     */
    public Cell(int row, int col, int value, boolean locked) {
        this.row = row;
        this.col = col;
        this.val = value;
        isLocked = locked;
    }


    /**
     * return the Cell's row index.
     */
    public int getRow() {
        return row;
    }


    /**
     * return the Cell's column index.
     */
    public int getCol() {
        return col;
    }


    /**
     * return the Cell's value.
     */
    public int getValue() {
        return val;
    }


    /**
     * set the Cell's value.
     */
    public void setValue(int newVal){
        this.val = newVal;
    }


    /**
     * return the value of the locked field.
     */
    public boolean isLocked() {
        return isLocked;
    }


    /**
     * set the Cell's locked field to the new value.
     */
    public void setLocked(boolean lock) {
        this.isLocked = lock;
    }


    /**
     * return a string with the Cell's numeric value.
     */
    public String toString() {
        return new DecimalFormat("00").format(val);
    }


    /**
     * draws the Cell's number.
     * The x0 and y0 are a global offset for the board so that it does not have to be smashed up against the upper left corner.
     */
    public void draw(Graphics g, int x, int y, int scale){
        String toDraw = new DecimalFormat("00").format(getValue());
        g.setColor(isLocked()? Color.BLUE : Color.RED);
        g.drawString(toDraw, x, y);
    }

}
