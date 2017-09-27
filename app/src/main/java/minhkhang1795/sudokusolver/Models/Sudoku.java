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
    private ArrayList<Cell> uncompleted = new ArrayList<>();
    public ArrayList<Cell> allCells = new ArrayList<>();
    public ArrayList<ArrayList<Cell>> rows = new ArrayList<>();
    public ArrayList<ArrayList<Cell>> columns = new ArrayList<>();
    public ArrayList<ArrayList<Cell>> boxes = new ArrayList<>();

    public Sudoku(int n) {
        this.n = n;

        initialize();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Cell cell = new Cell(n, i, j, null);
                allCells.add(cell);
                uncompleted.add(cell);
                addCellToGroups(cell);
            }
        }
    }

    private void addCellToGroups(Cell cell) {
        rows.get(cell.getRow()).set(cell.getColumn(), cell);
        columns.get(cell.getColumn()).set(cell.getRow(), cell);
        boxes.get(cell.getBox()).set(cell.getPositionInBox(), cell);
    }

    private void initialize() {
        initialize(rows);
        initialize(columns);
        initialize(boxes);
    }

    private void initialize(ArrayList<ArrayList<Cell>> a) {
        for (int i = 0; i < n; i++) {
            ArrayList<Cell> arr = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                arr.add(new Cell());
            }
            a.add(arr);
        }
    }

    public void setCell(int cellBox, int number) {
        if (allCells != null && allCells.size() > cellBox)
            allCells.get(cellBox).setNumber(number);
    }

    public void print() {
        for (int i = 0; i < allCells.size(); i++) {
            Log.d("SUDOKU", "Cell " + i + ": " + allCells.get(i).getNumber());
        }
    }

    public void solve() {
        updateCellPotentials();
        int a = 0;
        while (uncompleted.size() > 0) {
            if (a == uncompleted.size())
                break;
            else
                a = uncompleted.size();
            updateCellPotentials();
            sortUncompletedArray();
            checkUniqueInUncompleted();
        }
    }

    private void checkUniqueInUncompleted() {
        for (Cell cell : uncompleted) {
            for (int potential : cell.getPotentials()) {
                boolean uniqueR = checkUniqueInRow(cell, potential),
                uniqueC = checkUniqueInCol(cell, potential),
                uniqueB = checkUniqueInBox(cell, potential);
                if (uniqueR || uniqueB || uniqueC) {
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

    private void sortUncompletedArray() {
        // Sort based on numbers of potential
        Collections.sort(uncompleted, new Comparator<Cell>() {
            public int compare(Cell one, Cell other) {
                return one.getPotentials().size() - (other.getPotentials().size());
            }
        });
    }

    private void updateCellPotentials() {
        for (int i = 0; i < allCells.size(); i++) {
            Cell cell = allCells.get(i);
            updateCellPotential(cell);
            updateUncompletedArray(cell);
        }
        System.out.print("a");
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

    public void solveTest() {
        for (int i = 0; i < 10; i++) {
            allCells.get(i).setNumber(5);
        }
    }
}
