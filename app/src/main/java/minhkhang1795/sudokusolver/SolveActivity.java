package minhkhang1795.sudokusolver;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhkhang1795.sudokusolver.Models.Cell;
import minhkhang1795.sudokusolver.Models.Sudoku;

public class SolveActivity extends AppCompatActivity implements EditCellFragment.EditCellFragmentListener {

    @BindView(R.id.sudoku)
    LinearLayout sudokuFrame;

    @BindView(R.id.checktv)
    TextView checktv;

    private Sudoku sudoku;
    private ArrayList<TextView> cellViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);
        ButterKnife.bind(this);

        int n = 9;
        sudoku = new Sudoku(n);
        initializeGrid(n);
        initilizeTest3();

    }

    private void setupGridFromSudoku() {
        ArrayList<Cell> cells;
        if (sudoku.solutions.isEmpty())
            cells = sudoku.allCells;
        else
            cells = sudoku.solutions.get(0).allCells;
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            setNumberForCell(i, cell.getNumber(), cell.isKnown());

            if (cell.getNumber() == -1)
                setPotentialForCell(i, cell.getPotentials());
        }
    }

    private void setupSudokuFromGrid() {
        boolean isFirstSetup = sudoku.problemSet.isEmpty();
        if (isFirstSetup) {
            for (int i = 0; i < cellViews.size(); i++) {
                String temp = cellViews.get(i).getText().toString();
                if (temp.length() != 1)
                    continue;

                int number = Integer.parseInt(cellViews.get(i).getText().toString());
                boolean isCellKnown = true;
                sudoku.setCell(i, number, isCellKnown, isFirstSetup);
            }
        } else {
            for (int i = 0; i < cellViews.size(); i++) {
                String temp = cellViews.get(i).getText().toString();
                if (temp.length() != 1)
                    continue;

                int number = Integer.parseInt(cellViews.get(i).getText().toString());
                boolean isCellKnown = sudoku.allCells.get(i).isKnown();
                sudoku.setCell(i, number, isCellKnown, isFirstSetup);
            }
        }
    }

    private void setPotentialForCell(int cellBox, ArrayList<Integer> potentials) {
        TextView cellView = cellViews.get(cellBox);
        String a = "";
        for (int i : potentials)
            a += "" + i;
        cellView.setText(a);
    }

    private void setNumberForCell(int cellBox, int number, boolean isKnown) {
        TextView cellView = cellViews.get(cellBox);

        if (number < 1) {
            cellView.setText("");
            cellView.setTextColor(Color.BLACK);
            cellView.setTypeface(null, Typeface.NORMAL);
            return;
        }

        cellView.setText("" + number);
        if (isKnown) {
            cellView.setTextColor(Color.RED);
            cellView.setTypeface(null, Typeface.BOLD);
        } else {
            cellView.setTextColor(Color.BLACK);
            cellView.setTypeface(null, Typeface.NORMAL);
        }
    }

    private void initializeGrid(final int n) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        for (int i = 0; i < n; i++) {
            LinearLayout row = new LinearLayout(this, null, R.style.LinearRow);
            row.setWeightSum(n);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            row.setGravity(Gravity.CENTER);

            for (int j = 0; j < n; j++) {
                TextView cell = new TextView(this, null, R.style.CellText);
                cell.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, width / n, 1f));
                cell.setGravity(Gravity.CENTER);
                cell.setBackgroundResource(R.drawable.cell_border);
                final int finalI = i;
                final int finalJ = j;
                cell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int cellPosition = finalI * n + finalJ;
                        showEditDialog(sudoku.allCells.get(cellPosition).getNumber() - 1, cellPosition);
                    }
                });
                cellViews.add(cell);
                row.addView(cell);
            }
            sudokuFrame.addView(row);
        }
    }


    private void initilizeTest3() {
        setNumberForCell(0, 3, true);
        setNumberForCell(4, 6, true);
        setNumberForCell(8, 1, true);
        setNumberForCell(9, 2, true);
        setNumberForCell(11, 8, true);
        setNumberForCell(12, 7, true);
        setNumberForCell(18, 4, true);
        setNumberForCell(23, 2, true);
        setNumberForCell(26, 8, true);
        setNumberForCell(27, 6, true);
        setNumberForCell(28, 8, true);
        setNumberForCell(33, 9, true);
        setNumberForCell(34, 4, true);
        setNumberForCell(35, 2, true);
        setNumberForCell(38, 4, true);

        setNumberForCell(42, 1, true);
        setNumberForCell(45, 5, true);
        setNumberForCell(46, 2, true);
        setNumberForCell(47, 1, true);
        setNumberForCell(52, 8, true);
        setNumberForCell(53, 9, true);
        setNumberForCell(54, 1, true);
        setNumberForCell(57, 2, true);
        setNumberForCell(62, 3, true);
        setNumberForCell(68, 3, true);
        setNumberForCell(69, 8, true);
        setNumberForCell(71, 9, true);
        setNumberForCell(72, 8, true);

        setNumberForCell(76, 1, true);
        setNumberForCell(80, 4, true);
    }


    private void initilizeTest2() {
        setNumberForCell(1, 5, true);
        setNumberForCell(4, 2, true);
        setNumberForCell(7, 3, true);
        setNumberForCell(9, 2, true);
        setNumberForCell(14, 1, true);
        setNumberForCell(15, 7, true);
        setNumberForCell(17, 8, true);
        setNumberForCell(18, 4, true);
        setNumberForCell(20, 7, true);
        setNumberForCell(21, 6, true);
        setNumberForCell(32, 5, true);
        setNumberForCell(36, 5, true);
        setNumberForCell(37, 2, true);
        setNumberForCell(43, 4, true);
        setNumberForCell(44, 7, true);

        setNumberForCell(48, 7, true);
        setNumberForCell(59, 3, true);
        setNumberForCell(60, 5, true);
        setNumberForCell(62, 4, true);
        setNumberForCell(63, 3, true);
        setNumberForCell(65, 6, true);
        setNumberForCell(66, 5, true);
        setNumberForCell(71, 1, true);
        setNumberForCell(73, 9, true);
        setNumberForCell(76, 7, true);
        setNumberForCell(79, 6, true);
//        setNumberForCell(77, 9, true);
//        setNumberForCell(78, 2, true);

//        setNumberForCell(4, 3, true);
//        setNumberForCell(4, 2, true);
    }

//    private void initilizeTest() {
//        setNumberForCell(2, 1, true);
//        setNumberForCell(3, 9, true);
//        setNumberForCell(8, 8, true);
//        setNumberForCell(9, 6, true);
//        setNumberForCell(13, 8, true);
//        setNumberForCell(14, 5, true);
//        setNumberForCell(16, 3, true);
//        setNumberForCell(20, 7, true);
//        setNumberForCell(22, 6, true);
//        setNumberForCell(24, 1, true);
//        setNumberForCell(28, 3, true);
//        setNumberForCell(29, 4, true);
//        setNumberForCell(31, 9, true);
//        setNumberForCell(39, 5, true);
//        setNumberForCell(41, 4, true);
//        setNumberForCell(49, 1, true);
//        setNumberForCell(51, 4, true);
//        setNumberForCell(52, 2, true);
//        setNumberForCell(56, 5, true);
//        setNumberForCell(58, 7, true);
//        setNumberForCell(60, 9, true);
//        setNumberForCell(64, 1, true);
//        setNumberForCell(66, 8, true);
//        setNumberForCell(67, 4, true);
//        setNumberForCell(71, 7, true);
//        setNumberForCell(72, 7, true);
//        setNumberForCell(77, 9, true);
//        setNumberForCell(78, 2, true);
//
////        setNumberForCell(4, 3, true);
////        setNumberForCell(4, 2, true);
//    }

    public void onNew(View view) {
        sudoku = new Sudoku(sudoku.getSize());
        setupGridFromSudoku();
    }

    public void onReset(View view) {
        sudoku.resetSolution(sudoku.getSize());
        setupGridFromSudoku();
    }

    public void onSolve(View view) {
        setupSudokuFromGrid();
        sudoku.solve();
        setupGridFromSudoku();
        checktv.setText("Solutions found: " + sudoku.solutions.size());
    }

    private void showEditDialog(int number, int cellPosition) {
        FragmentManager fm = getSupportFragmentManager();
        EditCellFragment editDialog = EditCellFragment.newInstance(number, cellPosition);
        editDialog.show(fm, "fragment_edit_cell");
    }

    @Override
    public void onFinishEditDialog(int num, int cellPosition) {
        sudoku.allCells.get(cellPosition).setNumber(num);
        setNumberForCell(cellPosition, num, sudoku.allCells.get(cellPosition).isKnown());
    }
}
