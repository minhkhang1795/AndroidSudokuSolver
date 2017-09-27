package minhkhang1795.sudokusolver;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhkhang1795.sudokusolver.Models.Cell;
import minhkhang1795.sudokusolver.Models.Sudoku;

public class MainActivityTwo extends AppCompatActivity {

    @BindView(R.id.cell11)
    TextView cell11;
    @BindView(R.id.cell21)
    TextView cell21;
    @BindView(R.id.cell31)
    TextView cell31;
    @BindView(R.id.cell41)
    TextView cell41;
    @BindView(R.id.cell51)
    TextView cell51;
    @BindView(R.id.cell61)
    TextView cell61;
    @BindView(R.id.cell71)
    TextView cell71;
    @BindView(R.id.cell81)
    TextView cell81;
    @BindView(R.id.cell91)
    TextView cell91;

    @BindView(R.id.cell12)
    TextView cell12;
    @BindView(R.id.cell22)
    TextView cell22;
    @BindView(R.id.cell32)
    TextView cell32;
    @BindView(R.id.cell42)
    TextView cell42;
    @BindView(R.id.cell52)
    TextView cell52;
    @BindView(R.id.cell62)
    TextView cell62;
    @BindView(R.id.cell72)
    TextView cell72;
    @BindView(R.id.cell82)
    TextView cell82;
    @BindView(R.id.cell92)
    TextView cell92;

    @BindView(R.id.cell13)
    TextView cell13;
    @BindView(R.id.cell23)
    TextView cell23;
    @BindView(R.id.cell33)
    TextView cell33;
    @BindView(R.id.cell43)
    TextView cell43;
    @BindView(R.id.cell53)
    TextView cell53;
    @BindView(R.id.cell63)
    TextView cell63;
    @BindView(R.id.cell73)
    TextView cell73;
    @BindView(R.id.cell83)
    TextView cell83;
    @BindView(R.id.cell93)
    TextView cell93;

    @BindView(R.id.cell14)
    TextView cell14;
    @BindView(R.id.cell24)
    TextView cell24;
    @BindView(R.id.cell34)
    TextView cell34;
    @BindView(R.id.cell44)
    TextView cell44;
    @BindView(R.id.cell54)
    TextView cell54;
    @BindView(R.id.cell64)
    TextView cell64;
    @BindView(R.id.cell74)
    TextView cell74;
    @BindView(R.id.cell84)
    TextView cell84;
    @BindView(R.id.cell94)
    TextView cell94;

    @BindView(R.id.cell15)
    TextView cell15;
    @BindView(R.id.cell25)
    TextView cell25;
    @BindView(R.id.cell35)
    TextView cell35;
    @BindView(R.id.cell45)
    TextView cell45;
    @BindView(R.id.cell55)
    TextView cell55;
    @BindView(R.id.cell65)
    TextView cell65;
    @BindView(R.id.cell75)
    TextView cell75;
    @BindView(R.id.cell85)
    TextView cell85;
    @BindView(R.id.cell95)
    TextView cell95;

    @BindView(R.id.cell16)
    TextView cell16;
    @BindView(R.id.cell26)
    TextView cell26;
    @BindView(R.id.cell36)
    TextView cell36;
    @BindView(R.id.cell46)
    TextView cell46;
    @BindView(R.id.cell56)
    TextView cell56;
    @BindView(R.id.cell66)
    TextView cell66;
    @BindView(R.id.cell76)
    TextView cell76;
    @BindView(R.id.cell86)
    TextView cell86;
    @BindView(R.id.cell96)
    TextView cell96;

    @BindView(R.id.cell17)
    TextView cell17;
    @BindView(R.id.cell27)
    TextView cell27;
    @BindView(R.id.cell37)
    TextView cell37;
    @BindView(R.id.cell47)
    TextView cell47;
    @BindView(R.id.cell57)
    TextView cell57;
    @BindView(R.id.cell67)
    TextView cell67;
    @BindView(R.id.cell77)
    TextView cell77;
    @BindView(R.id.cell87)
    TextView cell87;
    @BindView(R.id.cell97)
    TextView cell97;

    @BindView(R.id.cell18)
    TextView cell18;
    @BindView(R.id.cell28)
    TextView cell28;
    @BindView(R.id.cell38)
    TextView cell38;
    @BindView(R.id.cell48)
    TextView cell48;
    @BindView(R.id.cell58)
    TextView cell58;
    @BindView(R.id.cell68)
    TextView cell68;
    @BindView(R.id.cell78)
    TextView cell78;
    @BindView(R.id.cell88)
    TextView cell88;
    @BindView(R.id.cell98)
    TextView cell98;

    @BindView(R.id.cell19)
    TextView cell19;
    @BindView(R.id.cell29)
    TextView cell29;
    @BindView(R.id.cell39)
    TextView cell39;
    @BindView(R.id.cell49)
    TextView cell49;
    @BindView(R.id.cell59)
    TextView cell59;
    @BindView(R.id.cell69)
    TextView cell69;
    @BindView(R.id.cell79)
    TextView cell79;
    @BindView(R.id.cell89)
    TextView cell89;
    @BindView(R.id.cell99)
    TextView cell99;

    @BindView(R.id.checktv)
    TextView checktv;

    private Sudoku sudoku;
    private ArrayList<TextView> cellViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        ButterKnife.bind(this);

        int n = 9;
        initilize(n);
        initilizeTest();
        setupSudokuFromGrid();
//        sudoku.print();
        sudoku.solve();
        setupGridFromSudoku();
//        if (check(sudoku))
//            checktv.setText("Check if correct: True");
//        else
//            checktv.setText("Check if correct: False");
    }

//    private boolean check(Sudoku sudoku) {
//        for (int i = 0; i < sudoku.rows)
//        return true;
//    }

    private void setupGridFromSudoku() {
        for (int i = 0; i < sudoku.allCells.size(); i++) {
            Cell cell = sudoku.allCells.get(i);
            if (cell.getNumber() != -1)
                setNumberForCell(i, cell.getNumber());
            else
                setPotentialForCell(i, cell.getPotentials());
        }
    }

    private void setPotentialForCell(int cellBox, ArrayList<Integer> potentials) {
        TextView cellView = cellViews.get(cellBox);
        String a = "";
        for (int i : potentials)
            a += "" + i;
        cellView.setText(a);
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
        setBoldNumberForCell(2, 1);
        setBoldNumberForCell(3, 9);
        setBoldNumberForCell(8, 8);
        setBoldNumberForCell(9, 6);
        setBoldNumberForCell(13, 8);
        setBoldNumberForCell(14, 5);
        setBoldNumberForCell(16, 3);
        setBoldNumberForCell(20, 7);
        setBoldNumberForCell(22, 6);
        setBoldNumberForCell(24, 1);
        setBoldNumberForCell(28, 3);
        setBoldNumberForCell(29, 4);
        setBoldNumberForCell(31, 9);
        setBoldNumberForCell(39, 5);
        setBoldNumberForCell(41, 4);
        setBoldNumberForCell(49, 1);
        setBoldNumberForCell(51, 4);
        setBoldNumberForCell(52, 2);
        setBoldNumberForCell(56, 5);
        setBoldNumberForCell(58, 7);
        setBoldNumberForCell(60, 9);
        setBoldNumberForCell(64, 1);
        setBoldNumberForCell(66, 8);
        setBoldNumberForCell(67, 4);
        setBoldNumberForCell(71, 7);
        setBoldNumberForCell(72, 7);
        setBoldNumberForCell(77, 9);
        setBoldNumberForCell(78, 2);

        setBoldNumberForCell(4, 2);
    }

    private void initilize(int n) {
        sudoku = new Sudoku(n);
        cellViews = new ArrayList<>();
        cellViews.add(cell11);
        cellViews.add(cell12);
        cellViews.add(cell13);
        cellViews.add(cell14);
        cellViews.add(cell15);
        cellViews.add(cell16);
        cellViews.add(cell17);
        cellViews.add(cell18);
        cellViews.add(cell19);
        cellViews.add(cell21);
        cellViews.add(cell22);
        cellViews.add(cell23);
        cellViews.add(cell24);
        cellViews.add(cell25);
        cellViews.add(cell26);
        cellViews.add(cell27);
        cellViews.add(cell28);
        cellViews.add(cell29);
        cellViews.add(cell31);
        cellViews.add(cell32);
        cellViews.add(cell33);
        cellViews.add(cell34);
        cellViews.add(cell35);
        cellViews.add(cell36);
        cellViews.add(cell37);
        cellViews.add(cell38);
        cellViews.add(cell39);

        cellViews.add(cell41);
        cellViews.add(cell42);
        cellViews.add(cell43);
        cellViews.add(cell44);
        cellViews.add(cell45);
        cellViews.add(cell46);
        cellViews.add(cell47);
        cellViews.add(cell48);
        cellViews.add(cell49);
        cellViews.add(cell51);
        cellViews.add(cell52);
        cellViews.add(cell53);
        cellViews.add(cell54);
        cellViews.add(cell55);
        cellViews.add(cell56);
        cellViews.add(cell57);
        cellViews.add(cell58);
        cellViews.add(cell59);
        cellViews.add(cell61);
        cellViews.add(cell62);
        cellViews.add(cell63);
        cellViews.add(cell64);
        cellViews.add(cell65);
        cellViews.add(cell66);
        cellViews.add(cell67);
        cellViews.add(cell68);
        cellViews.add(cell69);

        cellViews.add(cell71);
        cellViews.add(cell72);
        cellViews.add(cell73);
        cellViews.add(cell74);
        cellViews.add(cell75);
        cellViews.add(cell76);
        cellViews.add(cell77);
        cellViews.add(cell78);
        cellViews.add(cell79);
        cellViews.add(cell81);
        cellViews.add(cell82);
        cellViews.add(cell83);
        cellViews.add(cell84);
        cellViews.add(cell85);
        cellViews.add(cell86);
        cellViews.add(cell87);
        cellViews.add(cell88);
        cellViews.add(cell89);
        cellViews.add(cell91);
        cellViews.add(cell92);
        cellViews.add(cell93);
        cellViews.add(cell94);
        cellViews.add(cell95);
        cellViews.add(cell96);
        cellViews.add(cell97);
        cellViews.add(cell98);
        cellViews.add(cell99);
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
