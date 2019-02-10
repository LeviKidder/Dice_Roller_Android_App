/* Created by Levi Kidder
   Dice Roller App
   February 2019
 */


package com.levikidder.diceroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //Initializing variables
    int numDice =0, diceVal =0;

    EditText numDiceInput, diceValInput;

    TextView rollTotal;

    Button roll, shadowSwitch;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setting all textviews and edittexts
        rollTotal = findViewById(R.id.rollTotal);
        numDiceInput = findViewById(R.id.numDiceInput);
        diceValInput = findViewById(R.id.diceValIninput);
        rollTotal.setText("0");
        //setting buttons
        roll = findViewById(R.id.roll);
        shadowSwitch = findViewById(R.id.shadowSwitch);
        //new onClickListener for roll button - executes dice rolls
        roll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    /*checking if input is empty, setting it to zero if it is to prevent
                    runtime errors otherwise setting it to user input*/
                    if (diceValInput.getText().toString().isEmpty()) {
                        diceVal = 0;
                    } else {
                        diceVal = Integer.valueOf(diceValInput.getText().toString());
                    }
                    //Setting dice value limit of 100
                    if (diceVal > 100) {
                        diceVal = 100;
                        diceValInput.setText("100");
                    }
                    /*checking if input is empty, setting it to zero if it is to prevent
                    runtime errors otherwise setting it to user input*/
                    if (numDiceInput.getText().toString().isEmpty()) {
                        numDice = 0;
                    } else {
                        numDice = Integer.valueOf(numDiceInput.getText().toString());
                    }
                    //setting number of dice limit to 100
                    if (numDice > 100) {
                        numDice = 100;
                        numDiceInput.setText("100");
                    }

                    //Using diceRoll Method to set totalVal and print to rollTotal
                    String totalVal = Integer.toString(diceRoll(numDice, diceVal));
                    rollTotal.setText(totalVal);
                    rollTotal.setFocusable(false);
                }

        });
        //New onClickListener for shadowSwitch- Changes to Shadowrun Dice Roller
        shadowSwitch.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                //Changing to shadowRoller activity on click
                Intent changeAct = new Intent(MainActivity.this, shadowRoller.class);
                MainActivity.this.startActivity(changeAct);
            }
        });

            }
            //diceRoll method using Math.random to generate a random dice roll in user range
            public  int diceRoll(int numDice, int diceVal) {
                int totalRoll = 0, i;

                for (i = 0; i < numDice; i++) {

                    int rollVal = (int) (Math.random() * diceVal + 1);
                    totalRoll += rollVal;


                }


                return totalRoll;

            }

        }
