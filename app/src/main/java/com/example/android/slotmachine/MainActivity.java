package com.example.android.slotmachine;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView shape1;
    private ImageView shape2;
    private ImageView shape3;
    private TextView outputPoints;
    private SeekBar seekbar;
    private TextView startButton;
    private Handler handler;
    private Update1 update1;
    private Update2 update2;
    private Update3 update3;

    private boolean stopped;
    private int[] images={R.drawable.cherry, R.drawable.grape, R.drawable.pear, R.drawable.strawberry};
    private long shape1speed;
    private long shape2speed;
    private long shape3speed;
    private int counter1;
    private int counter2;
    private int counter3;
    private int x;
    final Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shape1=findViewById(R.id.shape1);
        shape2=findViewById(R.id.shape2);
        shape3=findViewById(R.id.shape3);
        outputPoints=findViewById(R.id.outputPoints);
        startButton=findViewById(R.id.startButton);
        seekbar=findViewById(R.id.seekbar);
        handler=new Handler();
        update1=new Update1();
        update2=new Update2();
        update3=new Update3();
        stopped=true;
        counter1=0;
        counter2=1;
        counter3=2;

        seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        shape1speed=(seekbar.getProgress()+1)*rand.nextInt(3)+1;
                        shape2speed=(seekbar.getProgress()+1)*rand.nextInt(3)+1;
                        shape3speed=(seekbar.getProgress()+1)*rand.nextInt(3)+1;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        shape1.setImageDrawable(getResources().getDrawable(images[0]));
        shape2.setImageDrawable(getResources().getDrawable(images[1]));
        shape3.setImageDrawable(getResources().getDrawable(images[2]));

    }

    public void startButtonPressed(View v) {
        if (stopped==true) {
            startButton.setText("STOP");
            shape1speed=(seekbar.getProgress()+1)*rand.nextInt(3)+1;
            shape2speed=(seekbar.getProgress()+1)*rand.nextInt(3)+1;
            shape3speed=(seekbar.getProgress()+1)*rand.nextInt(3)+1;
            handler.postDelayed(update1,shape1speed*100);
            handler.postDelayed(update2, shape2speed*100);
            handler.postDelayed(update3, shape3speed*100);
            stopped=false;
        }
        else {
            startButton.setText("START");
            handler.removeCallbacks(update1);
            handler.removeCallbacks(update2);
            handler.removeCallbacks(update3);
            if (counter1==counter2 && counter2 == counter3) {
                Toast.makeText(this, "You won 50pts", Toast.LENGTH_LONG).show();
                x+=50;
            }
            else if (counter1==0 || counter2==0 || counter3==0) {
                Toast.makeText(this, "You won 10pts", Toast.LENGTH_LONG).show();
                x+=10;
            }
            outputPoints.setText(x+"");
            System.out.println(counter1);
            System.out.println(counter2);
            System.out.println(counter3);
            stopped=true;
        }

    }

    private class Update1 implements Runnable {

        @Override
        public void run() {
            if (counter1<3) {
                counter1+=1;
                shape1.setImageDrawable(getResources().getDrawable(images[counter1]));
            }
            else {
                counter1=0;
                shape1.setImageDrawable(getResources().getDrawable(images[0]));
            }
            handler.postDelayed(update1, shape1speed*100);
        }

    }
    private class Update2 implements Runnable {

        @Override
        public void run() {
            if (counter2<3) {
                counter2+=1;
                shape2.setImageDrawable(getResources().getDrawable(images[counter2]));
            }
            else {
                counter2=0;
                shape2.setImageDrawable(getResources().getDrawable(images[0]));
            }
            handler.postDelayed(update2, shape2speed*100);
        }

    }
    private class Update3 implements Runnable {

        @Override
        public void run() {
            if (counter3<3) {
                counter3+=1;
                shape3.setImageDrawable(getResources().getDrawable(images[counter3]));
            }
            else {
                counter3=0;
                shape3.setImageDrawable(getResources().getDrawable(images[0]));
            }
            handler.postDelayed(update3, shape3speed*100);
        }

    }
}
