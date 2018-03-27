package com.example.android.slotmachine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by android on 3/27/18.
 */

public class DirectionsActivity extends AppCompatActivity {

    private TextView outputPoints;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        outputPoints=findViewById(R.id.outputPoints);

        outputPoints.setText(getIntent().getIntExtra("POINTS", -1)+"");


        if (savedInstanceState !=null) {
            outputPoints.setText(savedInstanceState.getString("Points"));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Points", outputPoints.getText().toString());
    }

}
