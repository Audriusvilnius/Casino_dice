package com.example.dice;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView ImageDice1;
    private ImageView ImageDice2;
    private final Random rand = new Random();
    int[] diceArray = new int[]{
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            ImageDice1 = findViewById(R.id.imageView1);
            ImageDice2 = findViewById(R.id.imageView2);
            dice1Click(ImageDice1, ImageDice2);
            dice2Click(ImageDice1, ImageDice2);
            return insets;
        });
    }

    private void dice2Click(ImageView Dice1, ImageView Dice2) {
        dice1Click(Dice2, Dice1);
    }

    private void dice1Click(ImageView Dice1, ImageView Dice2) {
        Dice1.setOnClickListener(v -> {
            int transition_Y1 = rand.nextInt(1500);
            int transition_Y2 = rand.nextInt(1500);
            int transition_X1 = rand.nextInt(140);
            int transition_X2 = rand.nextInt(140);
            int duration = 1200;
            Dice1.setImageResource(diceArray[diceArrayRand()]);
            Dice1.animate().rotation(360 * rotationDegree()).setDuration(duration).start();
            Dice1.animate().translationY(transition_Y1).setDuration(duration).start();
            Dice1.animate().translationX(transition_X1).setDuration(duration).start();
            Dice1.setEnabled(false);    //Disable the button
            Dice2.setImageResource(diceArray[diceArrayRand()]);
            Dice2.animate().rotation(315 * rotationDegree()).setDuration(duration).start();
            Dice2.animate().translationY(transition_Y2).setDuration(duration).start();
            Dice2.animate().translationX(transition_X2).setDuration(duration).start();
            Dice2.setEnabled(false);    //Disable the button
            new Handler().postDelayed(() -> {
                Dice1.setEnabled(true);
                Dice2.setEnabled(true);
            }, 1000);   //Enable the button after 1 second
        });
    }

    private int rotationDegree() {
        return rand.nextInt(10) + 1;
    }

    private int diceArrayRand() {
        return rand.nextInt(diceArray.length);
    }
}