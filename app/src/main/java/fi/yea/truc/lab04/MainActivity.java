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
        this.shouldClearScreen = false;
        Button button = (Button) v;
        String digit = button.getText().toString();
        TextView myDisplay = (TextView)findViewById(R.id.display);
        if (this.userIsInTheMiddleOfTypingANumber){
            myDisplay.setText(myDisplay.getText() + digit);
        }else{
            myDisplay.setText(digit);
            this.userIsInTheMiddleOfTypingANumber = true;
        }

    }


    public void onClickOperand(View v){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        if(!shouldClearScreen){
            this.userIsInTheMiddleOfTypingANumber = false;
            Button button = (Button) v;
            String myOperand = button.getText().toString();
            this.operand = myOperand;
            double temp = Double.parseDouble(myDisplay.getText().toString());
            this.operandStack.add(temp);
            // Add to operandStack
            if (this.operandStack.size()>=0){
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

            }
        }else{
            myDisplay.setText("0");
            this.shouldClearScreen= false;
        }


    }
    public void performOperationPlus(View v){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        int maxOperand = this.operandStack.size();
        if (maxOperand >1) {
            double result = operandStack.get(maxOperand - 2) + operandStack.get(maxOperand - 1);
            operandStack.set(maxOperand - 1, result);
            NumberFormat formatter = new DecimalFormat("##.###");
            myDisplay.setText(formatter.format(operandStack.get(maxOperand - 1)).toString());
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
            this.userIsInTheMiddleOfTypingANumber = false;
        }

    }
    public void performOperationDivide(View v){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        int maxOperand = this.operandStack.size();
        if (maxOperand >= 2){
            if(operandStack.get(maxOperand-1)!=0){
                double result = operandStack.get(maxOperand-2) / operandStack.get(maxOperand-1);
                operandStack.set(maxOperand-1, result);
                NumberFormat formatter = new DecimalFormat("##.###");
                myDisplay.setText(formatter.format(operandStack.get(maxOperand-1)).toString());
            }else{
                myDisplay.setText("ERROR");
                this.shouldClearScreen = true;
            }

            this.userIsInTheMiddleOfTypingANumber = false;
        }

    }

    public void onClickResult(View v){
        Button operation = (Button) v;
        String digit = operation.getText().toString();
        if (this.userIsInTheMiddleOfTypingANumber){
            enter(v);
        }
    }
    public void removeLastDigit(View v){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        myDisplay.setText("0");
        this.operandStack.clear();
        this.userIsInTheMiddleOfTypingANumber = false;
        this.operand="";
        this.shouldClearScreen= false;

    }
    public void enter(View v){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        int maxOperand = this.operandStack.size();
        if(!shouldClearScreen){

            double temp = Double.parseDouble(myDisplay.getText().toString());
            this.operandStack.add(temp);
            // Add to operandStack
            if (this.operandStack.size()>=0){
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

            }
            this.operand = "";
            operandStack.clear();
        }else{
            myDisplay.setText("0");
            this.shouldClearScreen= false;
        }

            this.userIsInTheMiddleOfTypingANumber = false;
    }

    public double getDisplayValue(){
        TextView myDisplay = (TextView)findViewById(R.id.display);
        Double myDisplayValue = Double.parseDouble(myDisplay.getText().toString());
        return myDisplayValue;
    }

}
