package fi.yea.truc.lab04;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.lang.reflect.Array;


public class MainActivity extends Activity implements View.OnClickListener {

    Boolean userIsInTheMiddleOfTypingANumber = false;
    Boolean shouldClearScreen = false;
    Boolean f = false;
    List<Double> operandStack = new ArrayList<Double>();
    Double temp = 0.0;
    String operand = "";
   // TextView myDisplay = (TextView)findViewById(R.id.display);
  /*  Button b0 = (Button)findViewById(R.id.button0);
    Button b1 = (Button)findViewById(R.id.button1);
    Button b2 = (Button)findViewById(R.id.button2);
    Button b3 = (Button)findViewById(R.id.button3);
    Button b4 = (Button)findViewById(R.id.button4);
    Button b5 = (Button)findViewById(R.id.button5);
    Button b6 = (Button)findViewById(R.id.button6);
    Button b7 = (Button)findViewById(R.id.button7);
    Button b8 = (Button)findViewById(R.id.button8);
    Button b9 = (Button)findViewById(R.id.button9);
    Button bDel = (Button)findViewById(R.id.buttonC);
    Button bAdd = (Button)findViewById(R.id.buttonPlus);
    Button bSub = (Button)findViewById(R.id.buttonMinus);
    Button bMul = (Button)findViewById(R.id.buttonMultiply);
    Button bDiv = (Button)findViewById(R.id.buttonDivide);*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        Button button = (Button) v;
        String digit = button.getText().toString();
        TextView myDisplay = (TextView)findViewById(R.id.display);
        if (userIsInTheMiddleOfTypingANumber){
            myDisplay.setText(myDisplay.getText() + digit);
        }else{
            myDisplay.setText(digit);
            userIsInTheMiddleOfTypingANumber = true;
        }
    }
    public void onClickOperand(View v){
        Button button = (Button) v;
        String myOperand = button.getText().toString();
        this.operand = myOperand;
        //this.operand = myOperand;
        TextView myDisplay = (TextView)findViewById(R.id.display);
        this.temp = Double.parseDouble(myDisplay.getText().toString());
        this.operandStack.add(this.temp);

        if (userIsInTheMiddleOfTypingANumber){
           // enter(v);
        }
        if(this.operand.contains("+")){
            performOperationPlus(v);
            userIsInTheMiddleOfTypingANumber = false;
        }
        switch (this.operand){
            case "+":
                performOperationPlus(v);
                userIsInTheMiddleOfTypingANumber = false;
                break;
            case "-":
                performOperationMinus(v);
                userIsInTheMiddleOfTypingANumber = false;
                break;
            case "×":
                performOperationMultiply(v);
                userIsInTheMiddleOfTypingANumber = false;
                break;
            case "÷":
                performOperationDivide(v);
                userIsInTheMiddleOfTypingANumber = false;
                break;
            default:
                break;

        }
    }
    public void performOperationPlus(View v){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        int maxOperand = this.operandStack.size();
        if (maxOperand >= 2){
            double result = operandStack.get(maxOperand-1) + operandStack.get(maxOperand-2);
            operandStack.set(maxOperand-1, result);
            NumberFormat formatter = new DecimalFormat("##.###");
            myDisplay.setText(formatter.format(operandStack.get(maxOperand-1)).toString());
            userIsInTheMiddleOfTypingANumber = false;
        }

    }

    public void performOperationMinus(View v){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        int maxOperand = this.operandStack.size();
        if (maxOperand >= 2){
            double result = operandStack.get(maxOperand-2) - operandStack.get(maxOperand-1);
            operandStack.set(maxOperand-1, result);
            NumberFormat formatter = new DecimalFormat("##.###");
            myDisplay.setText(formatter.format(operandStack.get(maxOperand-1)).toString());
            userIsInTheMiddleOfTypingANumber = false;
        }

    }
    public void performOperationMultiply(View v){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        int maxOperand = this.operandStack.size();
        if (maxOperand >= 2){
            double result = operandStack.get(maxOperand-1) * operandStack.get(maxOperand-2);
            operandStack.set(maxOperand-1, result);
            NumberFormat formatter = new DecimalFormat("##.###");
            myDisplay.setText(formatter.format(operandStack.get(maxOperand-1)).toString());
            userIsInTheMiddleOfTypingANumber = false;
        }

    }
    public void performOperationDivide(View v){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        int maxOperand = this.operandStack.size();
        if (maxOperand >= 2){
            double result = operandStack.get(maxOperand-2) / operandStack.get(maxOperand-1);
            operandStack.set(maxOperand-1, result);
            NumberFormat formatter = new DecimalFormat("##.###");
            myDisplay.setText(formatter.format(operandStack.get(maxOperand-1)).toString());
            userIsInTheMiddleOfTypingANumber = false;
        }

    }

    public void onClickResult(View v){
        Button operation = (Button) v;
        String digit = operation.getText().toString();
        if (userIsInTheMiddleOfTypingANumber){
            enter(v);
        }
    }
    public void removeLastDigit(View v){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        myDisplay.setText("0");
        this.operandStack.clear();
        userIsInTheMiddleOfTypingANumber = false;

    }
    public void enter(View v){

        TextView myDisplay = (TextView)findViewById(R.id.display);
        this.temp = Double.parseDouble(myDisplay.getText().toString());
        this.operandStack.add(this.temp);
        // Add to operandStack

        if (userIsInTheMiddleOfTypingANumber){
            switch (this.operand){
                case "+":
                    performOperationPlus(v);
                    break;
                case "-":
                    performOperationMinus(v);
                    break;
                case "×":
                    performOperationMultiply(v);
                    break;
                case "÷":
                    performOperationDivide(v);
                    break;
                default:
                    break;

            }

        }else{
            userIsInTheMiddleOfTypingANumber = false;
        }
        this.operand = "";
        int maxOperand = this.operandStack.size();
        double temp2 = operandStack.get(maxOperand-1);
        this.operandStack.clear();
        this.operandStack.add(temp2);
        this.shouldClearScreen = true;
    }

    public double getDisplayValue(){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        Double myDisplayValue = Double.parseDouble(myDisplay.getText().toString());
        return myDisplayValue;
    }

}
