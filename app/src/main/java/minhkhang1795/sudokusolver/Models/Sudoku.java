package minhkhang1795.sudokusolver.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by minhkhang1795 on 22/09/17.
 */

public class Sudoku {

    private int n = 4;

    public ArrayList<Cell> allCells = new ArrayList<>();
    private ArrayList<Cell> uncompleted = new ArrayList<>();
    private ArrayList<ArrayList<Cell>> rows = new ArrayList<>();
    private ArrayList<ArrayList<Cell>> columns = new ArrayList<>();
    private ArrayList<ArrayList<Cell>> boxes = new ArrayList<>();

    public ArrayList<Solution> solutions = new ArrayList<>();
    public ArrayList<Cell> problemSet = new ArrayList<>();

    public Sudoku(int n) {
        createNew(n);
    }

    public Sudoku(Sudoku sudoku) {
        this.n = sudoku.n;
        this.uncompleted = Sudoku.copyArray(sudoku.uncompleted);
        this.allCells = Sudoku.copyArray(sudoku.allCells);
        this.initGroups();
        this.addCellToGroups();
        for (Cell cell : sudoku.allCells)
            if (cell.isKnown())
                problemSet.add(cell);
    }

    public void createNew(int n) {
        resetSolution(n);
        problemSet.clear();
    }

    public void resetSolution(int n) {
        this.n = n;

        solutions.clear();
        uncompleted.clear();
        problemSet.clear();
        initGroups();

        ArrayList<Cell> tempAllCells = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean isKnown = this.allCells.isEmpty() ? false : this.allCells.get(i * n + j).isKnown();
                ArrayList<Integer> potentials = new ArrayList<>();
                if (isKnown && this.allCells.get(i * n + j).getNumber() != -1)
                    potentials.add(this.allCells.get(i * n + j).getNumber());
                Cell cell = new Cell(n, i, j, potentials, isKnown);
                tempAllCells.add(cell);
                addCellToGroups(cell);
                if (cell.isKnown())
                    problemSet.add(cell);
                else
                    uncompleted.add(cell);
            }
        }

        if (!this.allCells.isEmpty())
            allCells.clear();

        allCells = tempAllCells;

    }

    // A cell belonging to one row, column and box
    private void initGroups() {
        initialize(rows);
        initialize(columns);
        initialize(boxes);
    }

    private void initialize(ArrayList<ArrayList<Cell>> cells) {
        for (int i = 0; i < n; i++) {
            ArrayList<Cell> arr = new ArrayList<>();
            for (int j = 0; j < n; j++)
                arr.add(new Cell());
            cells.add(arr);
        }
    }

    private void addCellToGroups(Cell cell) {
        rows.get(cell.getRow()).set(cell.getColumn(), cell);
        columns.get(cell.getColumn()).set(cell.getRow(), cell);
        boxes.get(cell.getBox()).set(cell.getPositionInBox(), cell);
    }

    private void addCellToGroups() {
        for (Cell cell : allCells)
            addCellToGroups(cell);
    }

    public void setCell(int cellBox, int number, boolean isKnown, boolean isFirstSetup) {
        allCells.get(cellBox).setKnown(isKnown);
        allCells.get(cellBox).setNumber(number);
        if (isFirstSetup)
            problemSet.add(allCells.get(cellBox));
    }


    public int getSize() {
        return n;
    }

    public void solve() {
        // TODO: show unable to solve message!

        int flag = uncompleted.size(); // flag to exit loop if we can't solve the sudoku
        do {
            updateCellPotentials();
            sortUncompletedArray();
            checkUniqueInUncompleted();
            if (flag == uncompleted.size())
                break;
            else
                flag = uncompleted.size();
        } while (uncompleted.size() > 0);

        if (uncompleted.size() > 0) {
            // Create multiple states of the Sudoku with different solutions
            // Each solution has a number taken randomly from a cell with a least number of potentials
            Cell cell = uncompleted.get(0);
            for (int i : cell.getPotentials()) {
                Sudoku childSudoku= new Sudoku(this);
                childSudoku.allCells.get(cell.getPositionInSudokuGrid()).setNumber(i);
                childSudoku.solve();
                for (Solution solution : childSudoku.solutions) {
                    Solution temp = new Solution(solution);
                    solutions.add(temp);
                }
            }

        } else {
            // Check if our solution is correct
            if (check()) {
                Solution solution = new Solution(allCells);
                solutions.add(solution);
            }
        }

    }

    private void updateCellPotentials() {
        for (int i = 0; i < allCells.size(); i++) {
            Cell cell = allCells.get(i);
            updateCellPotential(cell);
            updateUncompletedArray(cell);
        }
    }

    private void updateCellPotential(Cell cell) {
        if (cell.getNumber() != -1)
            return;

        int[] potentials = new int[n];
        int row = cell.getRow(), column = cell.getColumn(), box = cell.getBox();

        // Check cell row
        ArrayList<Cell> rowCells = rows.get(row);
        for (int i = 0; i < rowCells.size(); i++) {
            Cell rowCell = rowCells.get(i);
            if (rowCell.getNumber() != -1)
                potentials[rowCell.getNumber() - 1]++;
        }

        // Check cell column
        ArrayList<Cell> colCells = columns.get(column);
        for (int i = 0; i < colCells.size(); i++) {
            Cell colCell = colCells.get(i);
            if (colCell.getNumber() != -1)
                potentials[colCell.getNumber() - 1]++;
        }

        // Check cell box
        ArrayList<Cell> boxCells = boxes.get(box);
        for (int i = 0; i < boxCells.size(); i++) {
            Cell boxCell = boxCells.get(i);
            if (boxCell.getNumber() != -1)
                potentials[boxCell.getNumber() - 1]++;
        }

        cell.setPotentials(potentials);
    }

    private void sortUncompletedArray() {
        // Sort based on numbers of potential
        Collections.sort(uncompleted, new Comparator<Cell>() {
            public int compare(Cell one, Cell other) {
                return one.getPotentials().size() - (other.getPotentials().size());
            }
        });
    }

    private void updateUncompletedArray(Cell cell) {
        if (cell.getNumber() != -1) {
            // Completed
            for (int i = 0; i < uncompleted.size(); i++) {
                Cell temp = uncompleted.get(i);
                if (temp.getRow() == cell.getRow() && temp.getColumn() == cell.getColumn()) {
                    uncompleted.remove(i);
                    break;
                }
            }
        }
    }

    private void checkUniqueInUncompleted() {
        for (Cell cell : uncompleted) {
            for (int potential : cell.getPotentials()) {
                if (checkUniqueInRow(cell, potential) || checkUniqueInCol(cell, potential) || checkUniqueInBox(cell, potential)) {
                    cell.setNumber(potential);
                    break;
                }
            }
        }
    }

    private boolean checkUniqueInBox(Cell cell, int potential) {
        for (Cell tempCell : boxes.get(cell.getBox())) {
            if (tempCell.getRow() != cell.getRow() || tempCell.getColumn() != cell.getColumn())
                if (tempCell.getNumber() == potential || tempCell.getPotentials().contains(potential))
                    return false;
        }
        return true;
    }

    private boolean checkUniqueInCol(Cell cell, int potential) {
        for (Cell tempCell : columns.get(cell.getColumn())) {
            if (tempCell.getRow() != cell.getRow())
                if (tempCell.getNumber() == potential || tempCell.getPotentials().contains(potential))
                    return false;
        }
        return true;
    }

    private boolean checkUniqueInRow(Cell cell, int potential) {
        for (Cell tempCell : rows.get(cell.getRow())) {
            if (tempCell.getColumn() != cell.getColumn())
                if (tempCell.getNumber() == potential || tempCell.getPotentials().contains(potential))
                    return false;
        }
        return true;
    }

    // Check if we have solved the sudoku
    public boolean check() {

        // Check Rows
        for (int i = 0; i < rows.size(); i++) {
            ArrayList<Cell> row = rows.get(i);
            int[] temp = new int[getSize()];

            for (int j = 0; j < row.size(); j++) {
                if (row.get(j).getNumber() == -1)
                    return false;
                temp[row.get(j).getNumber() - 1]++;
            }

            for (int j : temp)
                if (j == 0)
                    return false;
        }

        // Check Columns
        for (int i = 0; i < columns.size(); i++) {
            ArrayList<Cell> column = columns.get(i);
            int[] temp = new int[getSize()];

            for (int j = 0; j < column.size(); j++)
                temp[column.get(j).getNumber() - 1]++;

            for (int j : temp)
                if (j == 0)
                    return false;
        }

        // Check Boxes
        for (int i = 0; i < boxes.size(); i++) {
            ArrayList<Cell> box = rows.get(i);
            int[] temp = new int[getSize()];

            for (int j = 0; j < box.size(); j++)
                temp[box.get(j).getNumber() - 1]++;

            for (int j : temp)
                if (j == 0)
                    return false;
        }

        return true;
    }

    public class Solution {
        public ArrayList<Cell> allCells = new ArrayList<>();

        public Solution(ArrayList<Cell> allCells) {
            this.allCells = Sudoku.copyArray(allCells);
        }

        public Solution(Solution solution) {
            this.allCells = Sudoku.copyArray(solution.allCells);
        }
    }

    private static ArrayList<Cell> copyArray(ArrayList<Cell> cells) {
        ArrayList<Cell> result = new ArrayList<>();
        for (Cell cell: cells) {
            Cell temp = new Cell(cell.getSize(), cell.getRow(), cell.getColumn(), cell.getPotentials(), cell.isKnown());
            result.add(temp);
        }
        return result;
    }

    private static ArrayList<ArrayList<Cell>> copy2DArray(ArrayList<ArrayList<Cell>> array) {
        ArrayList<ArrayList<Cell>> result = new ArrayList<>();
        for (ArrayList<Cell> cells: array) {
            ArrayList<Cell> temp = Sudoku.copyArray(cells);
            result.add(temp);
        }
        return result;
    }


}
