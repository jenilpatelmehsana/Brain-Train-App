package com.jenil.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.sql.Time;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Global variable declare here
    final int totalTimeInSeconds = 30;
    Button goButton,playAgain,exitButton;
    TextView timeView,scoreView,questionView,stateView,resultText,accuText,finalResultTextView,finalAccuTextView;
    Button answerButtons[] = new Button[4];
    boolean stateOfChoosenAnswer;
    boolean operationVariable;


    //Timing and score variable goes here

    int secondsLeft = 30;
    int numberOfAllQuestion = 0;
    int numberOfRightQuestion = 0;

    //temp. variables goes here
    int countNumberOfRightAnswer;

    public void chooseAnswer(View view)
    {

        String logString = (String)view.getTag();
        Log.i("Tag",logString);
//        System.out.println( logString);

 //       System.out.println(view.getTag().toString());

        int choosenNumberByUser = Integer.parseInt((String)view.getTag());

 //       System.out.println(choosenNumberByUser + " is choosenNumberByUser");


        if(choosenNumberByUser == countNumberOfRightAnswer)
        {
            stateOfChoosenAnswer = true;
        }
        else
        {
            stateOfChoosenAnswer = false;
        }

        //making staeView

        if(stateOfChoosenAnswer == true)
        {

            stateView.setText("Correct !");
            numberOfRightQuestion++;
            numberOfAllQuestion++;
            scoreView.setText(Integer.toString(numberOfRightQuestion) + " / " + Integer.toString(numberOfAllQuestion));

        } else {
            numberOfAllQuestion++;
            scoreView.setText(Integer.toString(numberOfRightQuestion) + " / " + Integer.toString(numberOfAllQuestion));
            stateView.setText("Wrong ");

        }


        generateNewQuestion();

    }

    private void generateNewQuestion() {

        String questionString;

        Random random = new Random();

        int x1 = random.nextInt(21);
        int x2 = random.nextInt(21);
        operationVariable = random.nextBoolean();
        int rightAnswer;
        if(operationVariable == true)
        {
            rightAnswer = x1 + x2;
        }else
        {
            rightAnswer = x1 - x2;
        }

        countNumberOfRightAnswer = random.nextInt(4);

        //System.out.println(countNumberOfRightAnswer);
        Log.i("countNumberOfRightAnswer",Integer.toString(countNumberOfRightAnswer));


        if(operationVariable == true)
        {
            questionString = Integer.toString(x1)+" + "+Integer.toString(x2);
        }else
        {
            questionString = Integer.toString(x1)+" - "+Integer.toString(x2);
        }

        questionView.setText(questionString);

        for(int i = 0;i < 4; i++)
        {
            int tempButton = random.nextInt(40);
            if(i == countNumberOfRightAnswer) {
                answerButtons[i].setText(Integer.toString(rightAnswer));
            }
            else
            {
                if(tempButton == rightAnswer)
                {
                    while(tempButton == rightAnswer)
                    {
                        tempButton = random.nextInt(4);
                    }
                }
                answerButtons[i].setText(Integer.toString(tempButton));
            }

        }

    }

    public void onClickGo(View view){

        //making goButton Invisible and starting the game

        goButton.setVisibility(View.INVISIBLE);

        //making visible attributes

        timeView.setVisibility(View.VISIBLE);
        scoreView.setVisibility(View.VISIBLE);
        questionView.setVisibility(View.VISIBLE);
    //    gridLayout.setVisibility(View.VISIBLE);

        for(int i = 0;i<4;i++)
        {
            answerButtons[i].setVisibility(View.VISIBLE);
        }

        String questionString;

        Random random = new Random();

        int x1 = random.nextInt(21);
        int x2 = random.nextInt(21);
        operationVariable = random.nextBoolean();
        int rightAnswer;
        if(operationVariable == true)
        {
                rightAnswer = x1 + x2;
        }else
        {
                rightAnswer = x1 - x2;
        }

        countNumberOfRightAnswer = random.nextInt(4);

        //System.out.println(countNumberOfRightAnswer);
        Log.i("countNumberOfRightAnswer",Integer.toString(countNumberOfRightAnswer));


        if(operationVariable == true)
        {
            questionString = Integer.toString(x1)+" + "+Integer.toString(x2);
        }else
        {
            questionString = Integer.toString(x1)+" - "+Integer.toString(x2);
        }

        questionView.setText(questionString);

        for(int i = 0;i < 4; i++)
        {
            int tempButton = random.nextInt(40);
            if(i == countNumberOfRightAnswer) {
                answerButtons[i].setText(Integer.toString(rightAnswer));
            }
            else
            {
                if(tempButton == rightAnswer)
                {
                    while(tempButton == rightAnswer)
                    {
                        tempButton = random.nextInt(4);
                    }
                }
                answerButtons[i].setText(Integer.toString(tempButton));
            }

        }

       new CountDownTimer(totalTimeInSeconds*1000,1000){

           public void onTick(long milliSecondsUntilFinished){

               secondsLeft--;
               timeView.setText(Integer.toString(secondsLeft)+" SEC");

            }

            public void onFinish(){

               finishingView();

            }

        }.start();

    }

    private void finishingView() {

        timeView.setVisibility(View.INVISIBLE);
        scoreView.setVisibility(View.INVISIBLE);
        questionView.setVisibility(View.INVISIBLE);
        stateView.setVisibility(View.INVISIBLE);


        for(int i = 0;i<4;i++)
        {
            answerButtons[i].setVisibility(View.INVISIBLE);
        }

        playAgain.setVisibility(View.VISIBLE);
        exitButton.setVisibility(View.VISIBLE);
        resultText.setVisibility(View.VISIBLE);
        accuText.setVisibility(View.VISIBLE);
        finalResultTextView.setVisibility(View.VISIBLE);
        finalAccuTextView.setVisibility(View.VISIBLE);

        finalResultTextView.setText(Integer.toString(numberOfRightQuestion)+" / "+Integer.toString(numberOfAllQuestion));

        float accu = (float)numberOfRightQuestion/numberOfAllQuestion;

        String accuString = String.format("%.2f",accu);

        finalAccuTextView.setText(accuString);

    }

    public void playAgain(View view)
    {

        //making all thing INVISIBLE

        playAgain.setVisibility(View.INVISIBLE);
        resultText.setVisibility(View.INVISIBLE);
        accuText.setVisibility(View.INVISIBLE);
        finalResultTextView.setVisibility(View.INVISIBLE);
        finalAccuTextView.setVisibility(View.INVISIBLE);
        exitButton.setVisibility(View.INVISIBLE);



        //retriving goButton
        goButton.setVisibility(View.VISIBLE);

        //setting all attributes to normal
        secondsLeft = 30;
        numberOfAllQuestion = 0;
        numberOfRightQuestion = 0;


        scoreView.setText(Integer.toString(numberOfRightQuestion)+" / "+Integer.toString(numberOfAllQuestion));

    }

    public void onClickExit(View view)
    {
        finish();
        System.exit(0);

        Log.i("Enging","Game ended successfully");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Define here all global variable

        goButton = (Button)findViewById(R.id.goButton);
        playAgain = (Button)findViewById(R.id.playAgainButton);
        exitButton = (Button)findViewById(R.id.exitButton);
        timeView = (TextView)findViewById(R.id.timeView);
        scoreView = (TextView)findViewById(R.id.scoreView);
        questionView = (TextView)findViewById(R.id.questionView);
        answerButtons[0] = (Button)findViewById(R.id.ansButton1);
        answerButtons[1] = (Button)findViewById(R.id.ansButton2);
        answerButtons[2] = (Button)findViewById(R.id.ansButton3);
        answerButtons[3] = (Button)findViewById(R.id.ansButton4);
        stateView = (TextView)findViewById(R.id.stateView);
        resultText = (TextView)findViewById(R.id.resultText);
        accuText = (TextView)findViewById(R.id.accuText);
        finalResultTextView = (TextView)findViewById(R.id.finalResultTextVeiw);
        finalAccuTextView = (TextView)findViewById(R.id.finalAccuTextView);

     //   gridLayout = (GridLayout)findViewById(R.id.gridLayout);

//        timeView.setText("30 SEC");
//        scoreView.setText("0/0");

        timeView.setText(Integer.toString(secondsLeft)+" SEC");
        scoreView.setText(Integer.toString(numberOfRightQuestion)+" / "+Integer.toString(numberOfAllQuestion));



    }
}
