package minhkhang1795.sudokusolver.Models;

import android.support.annotation.IntegerRes;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by minhkhang1795 on 22/09/17.
 */

public class Cell {

    private int r = 1, c = 1, b = 1, n = 4;
    private boolean isKnown = false;
    private ArrayList<Integer> potentials = new ArrayList<>(); // From 1 to 9

    public Cell () {

    }

    public Cell (int n, int r, int c, ArrayList<Integer> potentials, boolean isKnown) {
        this.n = n;
        this.r = ++r;
        this.c = ++c;
        this.b = getBoxFromColumnAndRow(c, r);
        this.isKnown = isKnown;
        if (potentials == null || potentials.isEmpty())
            initPotential(n);
        else
            setPotentials(potentials);
    }

    public Cell (int n, int r, int c, int number) {
        this.n = n;
        this.r = ++r;
        this.c = ++c;
        this.b = getBoxFromColumnAndRow(c, r);
        this.potentials = new ArrayList<>();
        potentials.add(number);
    }

    public int getNumber() {
        if (this.potentials != null && this.potentials.size() == 1)
            return potentials.get(0);
        else
            return -1;
    }

    public void setNumber(int a) {
        if (a <= 0) {
            setKnown(false);
            initPotential(n);
            return;
        }
        ArrayList<Integer> number = new ArrayList<>();
        number.add(a);
        setPotentials(number);
    }

    public void setPotentials(ArrayList<Integer> potentials) {
        this.potentials = new ArrayList<>(potentials);
    }

    public void setPotentials(int[] potentials) {
        this.potentials = new ArrayList<>();
        for (int i = 0; i < potentials.length; i++)
            if (potentials[i] == 0)
                this.potentials.add(i + 1);
    }

    public ArrayList<Integer> getPotentials() {
        return this.potentials;
    }

    public int getPositionInSudoku() {
        return (r - 1) * n + c - 1;
    }

    public void initPotential(int n) {
        potentials.clear();
        for (int i = 1; i <= n; i++)
            potentials.add(i);
    }

    private int getBoxFromColumnAndRow(int c, int r) {
        int temp = (int) Math.sqrt(n);
        int k = c % temp == 0 ? 0 : 1, l = r % temp == 0 ? -1 : 0;
        return c / temp + k + temp * (r / temp + l);
    }

    public int getRow() {
        return r - 1;
    }

    public int getColumn() {
        return c - 1;
    }

    public int getBox() {
        return b - 1;
    }

    public int getPositionInBox() {
        int temp = (int) Math.sqrt(n);
        int i = r % temp, j = c % temp;
        if (i == 0)
            i = temp;
        if (j == 0)
            j = temp;
        return (i - 1) * temp + j - 1;
    }

    public int getPositionInSudokuGrid() {
        return (r - 1) * n + c - 1;
    }

    public int getSize() {
        return n;
    }

    public boolean isKnown() {
        return isKnown;
    }

    public void setKnown(boolean known) {
        isKnown = known;
    }
}
