package minhkhang1795.sudokusolver;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minhkhang1795 on 01/10/17.
 */

public class EditCellFragment extends DialogFragment {

    // Number selected. [0 - 8]. -1 = null
    int mNum;
    int cellPosition;

    @BindView(R.id.tvOne) TextView tvOne;
    @BindView(R.id.tvTwo) TextView tvTwo;
    @BindView(R.id.tvThree) TextView tvThree;
    @BindView(R.id.tvFour) TextView tvFour;
    @BindView(R.id.tvFive) TextView tvFive;
    @BindView(R.id.tvSix) TextView tvSix;
    @BindView(R.id.tvSeven) TextView tvSeven;
    @BindView(R.id.tvEight) TextView tvEight;
    @BindView(R.id.tvNine) TextView tvNine;

    @BindView(R.id.btnCancel) Button btnCancel;
    @BindView(R.id.btnOk) Button btnOk;
    @BindView(R.id.btnClear) Button btnClear;

    ArrayList<TextView> keypad = new ArrayList<>();

    static EditCellFragment newInstance(int num, int cellPosition) {
        EditCellFragment f = new EditCellFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        args.putInt("cell_pos", cellPosition);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        keypad.add(tvOne);
        keypad.add(tvTwo);
        keypad.add(tvThree);
        keypad.add(tvFour);
        keypad.add(tvFive);
        keypad.add(tvSix);
        keypad.add(tvSeven);
        keypad.add(tvEight);
        keypad.add(tvNine);
        for (int i = 0; i < keypad.size(); i++) {
            final int finalI = i;
            keypad.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectNumber(finalI);
                }
            });
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectNumber(-1);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBackResult(mNum);
            }
        });
        selectNumber(mNum);
    }

    private void sendBackResult(int mNum) {
        EditCellFragmentListener listener = (EditCellFragmentListener) getActivity();
        listener.onFinishEditDialog(mNum + 1, cellPosition);
        dismiss();
    }

    private void selectNumber(int i) {
        if (mNum > -1)
            setTextViewSelected(keypad.get(mNum), false);
        if (i > -1)
            setTextViewSelected(keypad.get(i), true);
        mNum = i;
    }

    private void setTextViewSelected(TextView textView, boolean isSelected) {
        if (isSelected) {
            textView.setTextColor(Color.RED);
            textView.setTypeface(null, Typeface.BOLD);
        } else {
            textView.setTextColor(Color.BLACK);
            textView.setTypeface(null, Typeface.NORMAL);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num", -1);
        cellPosition = getArguments().getInt("cell_pos", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_cell, container, false);
        ButterKnife.bind(this, view);

        return view;

    }

    // Defines the listener interface
    public interface EditCellFragmentListener {
        void onFinishEditDialog(int num, int cellPosition);
    }

}