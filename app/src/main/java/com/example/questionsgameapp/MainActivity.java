
 package com.example.questionsgameapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity  {
    /**
    * TextView variable tv.
     */
    private TextView tv;
    /**
     * EditText variable ed.
     */
    private EditText ed;
    /**
     * Button variable btn1.
     */
    private Button btn1;
    /**
     * Button variable btn2.
     */
    private Button btn2;
    /**
     *  ArrayList<String> variable questions.
     */
   private ArrayList<String> questions;
    /**
     * String variable ques.
     */
   private String ques;
    /**
     * int variable z.
     */
   private int z;
    /**
     * String variable sizeString.
     */
    private String sizeString;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv =   findViewById(R.id.textView2);
        ed =   findViewById(R.id.editText);
        btn1 =  findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        questions = new ArrayList<>();




         btn1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(final View v) {

                 ques = ed.getText().toString().trim();

                 if (ques.isEmpty()) {
                  Toast.makeText(MainActivity.this, "الرجاء ادخال سؤال !", Toast.LENGTH_SHORT).show();
                 } else {
                     questions.add(ques);
                     z = questions.size();
                     sizeString = valueOf(z);
                     tv.setText("عدد الاسئلة : " + sizeString);
                     ed.getText().clear();

                 }



             }
         });


            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    if (z <= 2) {
                  Toast.makeText(MainActivity.this, "الرجاء ادخال اكثر من سؤالين", Toast.LENGTH_SHORT).show();
                    } else {

                        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                        intent.putExtra("text", questions);
                        startActivity(intent);

                    }

                }
            });



        }

}