package minhkhang1795.sudokusolver;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhkhang1795.sudokusolver.Models.Sudoku;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cell11)
    TextView cell11;
    @BindView(R.id.cell21)
    TextView cell21;
    @BindView(R.id.cell31)
    TextView cell31;
    @BindView(R.id.cell41)
    TextView cell41;
    @BindView(R.id.cell12)
    TextView cell12;
    @BindView(R.id.cell22)
    TextView cell22;
    @BindView(R.id.cell32)
    TextView cell32;
    @BindView(R.id.cell42)
    TextView cell42;
    @BindView(R.id.cell13)
    TextView cell13;
    @BindView(R.id.cell23)
    TextView cell23;
    @BindView(R.id.cell33)
    TextView cell33;
    @BindView(R.id.cell43)
    TextView cell43;
    @BindView(R.id.cell14)
    TextView cell14;
    @BindView(R.id.cell24)
    TextView cell24;
    @BindView(R.id.cell34)
    TextView cell34;
    @BindView(R.id.cell44)
    TextView cell44;

    private Sudoku sudoku;
    private ArrayList<TextView> cellViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        int n = 4;
        initilize(n);
        initilizeTest();
        setupSudokuFromGrid();
//        sudoku.print();
        sudoku.solve();
        setupGridFromSudoku();
    }

    private void setupGridFromSudoku() {
        for (int i = 0; i < sudoku.allCells.size(); i++) {
            setNumberForCell(i, sudoku.allCells.get(i).getNumber());
        }
    }

    private void setupSudokuFromGrid() {
        for (int i = 0; i < cellViews.size(); i++) {
            String temp = cellViews.get(i).getText().toString();
            if (temp.isEmpty())
                continue;
            sudoku.setCell(i, Integer.parseInt(cellViews.get(i).getText().toString()));
        }
    }

    private void initilizeTest() {
        setBoldNumberForCell(0, 1);
        setBoldNumberForCell(2, 3);
        setBoldNumberForCell(5, 2);
        setBoldNumberForCell(8, 4);
        setBoldNumberForCell(14, 1);
    }

    private void initilizeTest2() {
        setBoldNumberForCell(0, 1);
        setBoldNumberForCell(2, 3);
        setBoldNumberForCell(5, 2);
        setBoldNumberForCell(8, 4);
        setBoldNumberForCell(14, 1);
    }

    private void initilize(int n) {
        sudoku = new Sudoku(n);
        cellViews = new ArrayList<>();
        cellViews.add(cell11);
        cellViews.add(cell12);
        cellViews.add(cell13);
        cellViews.add(cell14);
        cellViews.add(cell21);
        cellViews.add(cell22);
        cellViews.add(cell23);
        cellViews.add(cell24);
        cellViews.add(cell31);
        cellViews.add(cell32);
        cellViews.add(cell33);
        cellViews.add(cell34);
        cellViews.add(cell41);
        cellViews.add(cell42);
        cellViews.add(cell43);
        cellViews.add(cell44);
    }

    private void setNumberForCell(int cellBox, int number) {
        TextView cellView = cellViews.get(cellBox);
        cellView.setText("" + number);
    }

    private void setBoldNumberForCell(int cellBox, int number) {
        TextView cellView = cellViews.get(cellBox);
        cellView.setText("" + number);
        cellView.setTextColor(Color.RED);
        cellView.setTypeface(null, Typeface.BOLD);
    }
}
