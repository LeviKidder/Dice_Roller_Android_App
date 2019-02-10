package com.levikidder.diceroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class shadowRoller extends AppCompatActivity {
    //Initializing variables
    int numDice, diceVal = 6;

    EditText shadowInput;

    TextView viewBox, hitsTotal ,glitchAns;



    Button roll, mainSwitch;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_roller);
        //Setting all textviews and edittexts
        viewBox = findViewById(R.id.viewBox);
        hitsTotal = findViewById(R.id.hitsTotal);
        glitchAns = findViewById(R.id.glitchAns);
        shadowInput = findViewById(R.id.shadowInput);

        viewBox.setMovementMethod(new ScrollingMovementMethod());

        //setting buttons
        roll = findViewById(R.id.roll);
        mainSwitch = findViewById(R.id.mainSwitch);
        //new OnClickListener for roll button
        roll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                /*checking if input is empty, setting it to zero if it is to prevent
                    runtime errors otherwise setting it to user input*/
                if(shadowInput.getText().toString().isEmpty()){
                    numDice = 0;
                }
                else{
                    numDice = Integer.valueOf(shadowInput.getText().toString());
                }
                /*calling diceRoll and setting it to realValue to
                 pass into arrayData. Calling only one instance of dice
                 roll ensures that my values are consistent*/
                int[] realValue = diceRoll(numDice,diceVal);


                //Parsing data from arrayData for hitsTotal and glitchAns
                String totalVal = Integer.toString(arrayData(realValue)[0]);
                hitsTotal.setText(totalVal);
                hitsTotal.setFocusable(false);
                if((arrayData(realValue)[2] == 1)) {
                    glitchAns.setText("Critical Glitch!");
                }
                else if((arrayData(realValue)[1])==1){
                    glitchAns.setText("Glitch!");
                }
                else{
                    glitchAns.setText("No Glitch!");
                }
                /*creating a string allRolls with new lines from the array realValue returned
                from  method diceRoll*/
                StringBuilder allRolls = new StringBuilder();
                for (int roll : realValue) {
                    allRolls.append(String.valueOf(roll) + "\n");
                }
                //setting viewBox to have new string allRolls
                viewBox.setText(allRolls);
            }

        });
        //setting button to return to main screen
        mainSwitch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //new intent to change to main screen on click
                Intent changeAct = new Intent(shadowRoller.this, MainActivity.class);
                shadowRoller.this.startActivity(changeAct);
            }
        });


    }
    // creating diceRoll method that returns an array of numDice length of random d6 rolls
    public static int[] diceRoll(int numDice, int diceVal) {
        int i;
        int returnArray[] = new int[numDice];

        for(i=0;i<numDice;i++) {

            int rollVal = (int )(Math.random() * diceVal + 1);
            returnArray[i]=rollVal;

            }


        return returnArray;
    }
    /*Creating method arrayData that returns an array (shortArray) with value of total hits, and true
    false flags for glitch or critical glitch
     */
    public static int[] arrayData(int[] returnArray) {
        int totalHits = 0, numOnes =0, glitch =0, criticalGlitch =0;
        int shortArray[] = new int[3];

        for(int roll :returnArray){
            if (roll>=5){
            totalHits+=1;
            }
            if(roll ==1){
                numOnes+=1;
            }

        }
        if(numOnes>returnArray.length/2){
            if(totalHits ==0){
                criticalGlitch = 1;
            }
            else{
                glitch = 1;
            }
        }

        shortArray[0] = totalHits;
        shortArray[1] = glitch;
        shortArray[2] = criticalGlitch;

    return shortArray;
    }

}



