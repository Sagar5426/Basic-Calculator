package com.example.calculator_s;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution_TextView ,result_textView;
    MaterialButton button_C , button_Dot , button_Open_Bracket , button_Close_Bracket , button_AC;
    MaterialButton button_Divide , button_Multiply , button_Add , button_Minus , button_Equals;
    MaterialButton button_0 ,button_1 ,button_2 ,button_3 ,button_4 ,button_5 ,button_6 ,button_7, button_8 , button_9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Made By Sagar", Toast.LENGTH_SHORT).show();
        result_textView = findViewById(R.id.result_textView);
        solution_TextView = findViewById(R.id.solution_textView);


        assign_Id(button_C,R.id.button_C);
        assign_Id(button_Dot,R.id.button_Dot);
        assign_Id(button_Open_Bracket,R.id.button_Open_Bracket);
        assign_Id(button_Close_Bracket,R.id.button_Close_Bracket);
        assign_Id(button_AC,R.id.button_AC);

        assign_Id(button_Divide,R.id.button_Divide);
        assign_Id(button_Minus,R.id.button_Minus);
        assign_Id(button_Multiply,R.id.button_Multiply);
        assign_Id(button_Add,R.id.button_Add);
        assign_Id(button_Equals,R.id.button_Equals);

        assign_Id(button_0,R.id.button_0);
        assign_Id(button_1,R.id.button_1);
        assign_Id(button_2,R.id.button_2);
        assign_Id(button_3,R.id.button_3);
        assign_Id(button_4,R.id.button_4);
        assign_Id(button_5,R.id.button_5);
        assign_Id(button_6,R.id.button_6);
        assign_Id(button_7,R.id.button_7);
        assign_Id(button_8,R.id.button_8);
        assign_Id(button_9,R.id.button_9);


    }


    // smart work i.e. less code and run fast
    void assign_Id(MaterialButton btn , int id){

    btn = findViewById(id);
    btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_TextView.getText().toString();

        if (buttonText.equals("AC")){
            solution_TextView.setText("");
            result_textView.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solution_TextView.setText(result_textView.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        solution_TextView.setText(dataToCalculate);
        String finalResult =getResult(dataToCalculate);

        if (!finalResult.equals("Err")){
            result_textView.setText(finalResult);
        }
    }



    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String Result = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (Result.endsWith(".0")){
                Result = Result.replace(".0","");
            }
            return  Result;
        }catch (Exception e){
            return "Err";

        }
    }

}