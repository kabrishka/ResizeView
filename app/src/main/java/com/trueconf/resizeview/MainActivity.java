package com.trueconf.resizeview;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayoutMain;
    private View viewBlue, viewRed, viewOrange, viewGreen, viewClose;
    private boolean swapped = false;
    private boolean closed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayoutMain = findViewById(R.id.constraintLayoutMain);
        viewClose = findViewById(R.id.viewClose);
        viewBlue = findViewById(R.id.viewBlue);
        viewRed = findViewById(R.id.viewRed);
        viewOrange = findViewById(R.id.viewOrange);
        viewGreen = findViewById(R.id.viewGreen);

        viewClose.setOnClickListener(v -> onCloseClick());
    }

    private void onCloseClick() {
        closed = !closed;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayoutMain);

        ViewGroup.LayoutParams params = constraintLayoutMain.getLayoutParams();

        if (closed) {
            params.width = 400;
            params.height = 100;

            constraintSet.clear(viewClose.getId(), ConstraintSet.TOP);
            constraintSet.connect(viewClose.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
            constraintSet.connect(viewClose.getId(), ConstraintSet.END, viewBlue.getId(), ConstraintSet.START);

            constraintSet.clear(viewBlue.getId(), ConstraintSet.START);
            constraintSet.connect(viewBlue.getId(), ConstraintSet.START, viewClose.getId(), ConstraintSet.END);

            constraintSet.clear(viewRed.getId(), ConstraintSet.END);
            constraintSet.connect(viewRed.getId(), ConstraintSet.END, viewGreen.getId(), ConstraintSet.START);

            constraintSet.clear(viewGreen.getId(), ConstraintSet.START);
            constraintSet.connect(viewGreen.getId(), ConstraintSet.START, viewRed.getId(), ConstraintSet.END);
        } else {
            params.width = 600;
            params.height = 300;

            constraintSet.clear(viewClose.getId(), ConstraintSet.BOTTOM);
            constraintSet.clear(viewClose.getId(), ConstraintSet.END);
            constraintSet.connect(viewClose.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);

            constraintSet.clear(viewBlue.getId(), ConstraintSet.START);
            constraintSet.connect(viewBlue.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);

            constraintSet.clear(viewRed.getId(), ConstraintSet.END);
            constraintSet.connect(viewRed.getId(), ConstraintSet.END, viewOrange.getId(), ConstraintSet.START);

            constraintSet.clear(viewGreen.getId(), ConstraintSet.START);
            constraintSet.connect(viewGreen.getId(), ConstraintSet.START, viewOrange.getId(), ConstraintSet.END);
        }
        TransitionManager.beginDelayedTransition(constraintLayoutMain);
        constraintSet.applyTo(constraintLayoutMain);

        viewOrange.setVisibility(closed ? View.GONE : View.VISIBLE);
    }

    public void onConstraintLayoutClick(View view) {
        swapped = !swapped;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayoutMain);

        ViewGroup.LayoutParams params = constraintLayoutMain.getLayoutParams();

        if (swapped) {
            params.width = 300;
            params.height = 600;
            // настраиваем синий
            constraintSet.clear(viewBlue.getId(), ConstraintSet.END);
            constraintSet.connect(viewBlue.getId(), ConstraintSet.END, viewOrange.getId(), ConstraintSet.START);

            // настраиваем красный
            constraintSet.clear(viewRed.getId(), ConstraintSet.BOTTOM);
            constraintSet.clear(viewRed.getId(), ConstraintSet.END);
            constraintSet.clear(viewRed.getId(), ConstraintSet.START);
            constraintSet.connect(viewRed.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
            constraintSet.connect(viewRed.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
            constraintSet.connect(viewRed.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);

            // настраиваем оранжевый
            constraintSet.clear(viewOrange.getId(), ConstraintSet.START);
            constraintSet.connect(viewOrange.getId(), ConstraintSet.START, viewBlue.getId(), ConstraintSet.END);
        } else {
            params.width = 600;
            params.height = 300;

            // настраиваем синий
            constraintSet.clear(viewBlue.getId(), ConstraintSet.END);
            constraintSet.connect(viewBlue.getId(), ConstraintSet.END, viewRed.getId(), ConstraintSet.START);

            // настраиваем красный
            constraintSet.clear(viewRed.getId(), ConstraintSet.BOTTOM);
            constraintSet.clear(viewRed.getId(), ConstraintSet.END);
            constraintSet.clear(viewRed.getId(), ConstraintSet.TOP);
            constraintSet.connect(viewRed.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
            constraintSet.connect(viewRed.getId(), ConstraintSet.END, viewOrange.getId(), ConstraintSet.START);
            constraintSet.connect(viewRed.getId(), ConstraintSet.START, viewBlue.getId(), ConstraintSet.END);

            // настраиваем оранжевый
            constraintSet.clear(viewOrange.getId(), ConstraintSet.START);
            constraintSet.connect(viewOrange.getId(), ConstraintSet.START, viewRed.getId(), ConstraintSet.END);
        }
        TransitionManager.beginDelayedTransition(constraintLayoutMain);
        constraintSet.applyTo(constraintLayoutMain);
    }
}