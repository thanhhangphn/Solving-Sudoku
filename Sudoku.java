/**
 * Elia Phan
 * Board.java
 * CS231 SP23 Project 5
 * last modified 03/17/2023
 */


/**
 * PURPOSE:
 * Sudoku class that will actually solve a puzzle
 */

//TODO: everything done & tested except

public class Sudoku {
    private LandscapeDisplay ld;
    private Board board;
    private int steps = 0; //count the steps taken to solve the board


    /**
     * constructor: creates a new Board with some number of pre-determined randomly placed values.
     */
    public Sudoku(int size, int numOfLocked){
        board = new Board(size, numOfLocked);
        ld = new LandscapeDisplay((int) Math.sqrt(size), board);
    }


    /**
     * find a valid value for a Cell
     */
    public int findNextValue(Cell cell){
        int val = cell.getValue();
        while (!board.validValue(cell.getRow(), cell.getCol(), val) || val == cell.getValue()){
            val = val + 1;
            if (val > board.size){
                return 0;
            }
        }
        return val;
    }


    /**
     * find the next cell to go to and find an appropriate value for it
     */
    public Cell findNextCell(){
        int i = 0;
        while (i < board.size){
            i++;
            int j = 0;
            while (j < board.size){
                j++;
                if (board.get(i, j).getValue() == 0){
                    if (findNextValue(board.get(i, j)) != 0){
                        board.get(i, j).setValue(findNextValue(board.get(i, j)));
                        return board.get(i, j);
                    }
                    else{
                        return null;
                    }
                }
            }
        }
        //System.out.println("Sudoku.java line 67: There is no next cell to findNextCell");
        return board.get(board.size, board.size);
    }


    /**
     * uses a stack to keep track of the solution and allow backtracking when it gets stuck
     */
    public boolean solve(int delay) throws InterruptedException {
        //Allocate a stack, initially empty
        LinkedList<Cell> stack = new LinkedList<>();


        //while the stack size is less than the number of unspecified cells
        while (stack.size() < board.size*board.size - board.numOfLocked()){

            steps = steps + 1;
            if (delay > 0)
                Thread.sleep(delay);
            if (ld != null)
                ld.repaint();

            //System.out.println("board.getArrOriginal(): " + board.getArrOriginal());
            //System.out.println("hereA1");
            //Create a cell called next by calling findNextCell.
            Cell next = findNextCell();
            //while next is null and the stack is non-empty:
            while (next == null && !stack.isEmpty()){
                //System.out.println("hereB1");
                //pop a cell off the stack and call findNextValue on this Cell
                Cell cell = stack.pop();
                cell.setValue(findNextValue(cell));
                //System.out.println("cell that is just pop out: " + cell.getValue() + " and its findNextValue: " + findNextValue(cell));
                //if the cell's value is no longer 0, set next to this popped cell.
                if (cell.getValue() != 0){
                    //System.out.println("hereC1");
                    next = cell;
                }
            }
            //if next is still null
            if (next == null){
                //System.out.println("hereD1");
                //then the stack must be empty, so we can give up as we've tried all options
                //return false
                board.finished = true;
                return false;
            }
            else{
                //System.out.println("hereE1");
                //System.out.println(board);
                //System.out.println("stack: " + stack);
                //push next onto the stack
                stack.push(next);
            }
        }
        //System.out.println("Sudoku.java line 118: Return true: the board contains the solution");
        //return true: the board contains the solution
        board.finished = true;
        return true;
    }


    /**
     * test
     */
    public static void main(String[] args) throws InterruptedException {
        int count = 0;         // count the number of times the board is solved
        int times = 5;         // number of cases run
        int totalSteps = 0;      //total amount of time to run every cases
        for (int i = 0; i < times; i++){
            Sudoku s1 = new Sudoku(16, 10);
            s1.solve(0);
            if (s1.board.validSolution()){
                count = count + 1;
            }
            totalSteps = totalSteps + s1.steps;
        }
        System.out.println("valid cases: " + count + "/" + times);
        System.out.println("average steps: " + totalSteps/times);
    }
}
