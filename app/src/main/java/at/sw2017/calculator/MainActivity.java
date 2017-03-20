package at.sw2017.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    ArrayList numberButtons;
    TextView numberView;

    public int firstNumber;
    public enum State {
        ADD , SUB , MUL , DIV , INIT , NUM
    }
    public State state;

    public void setUpNumberButtonListener () {
        for (int i=0;i<=9;i++) {
            String buttonName = "button" + i;

            int id = getResources().getIdentifier(buttonName,"id",R.class.getPackage().getName());
            Button button= (Button) findViewById(id);
            button.setOnClickListener(this);

            //numberButtons.add(button); TODO warum zu der Liste???
        }

        numberView= (TextView) findViewById(R.id.textView);
        Button button=(Button)findViewById(R.id.buttonCalculate);
        button.setOnClickListener(this);
        button=(Button)findViewById(R.id.buttonDevide);
        button.setOnClickListener(this);
        button=(Button)findViewById(R.id.buttonMinus);
        button.setOnClickListener(this);
        button=(Button)findViewById(R.id.buttonPlus);
        button.setOnClickListener(this);
        button=(Button)findViewById(R.id.buttonTimes);
        button.setOnClickListener(this);
        button=(Button)findViewById(R.id.buttonC);
        button.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpNumberButtonListener();
        state = State.INIT;
        //Button buttonZero = (Button) findViewById(R.id.buttonZero);
    }


    private void clearTextView () {
        numberView.setText("0");
        state=State.INIT;
    }


    private void clearNumberView () {
        String tempString = numberView.getText().toString();
        if (!tempString.equals("")){
            firstNumber = Integer.valueOf(tempString);
        }
        numberView.setText("");
    }

    private void calculateResult () {
        int secondNumber = 0;
        String tempString = numberView.getText().toString();
        if (! tempString . equals ( "" )){
            secondNumber = Integer.valueOf(tempString);
        }
        int result;
        switch (state){
            case ADD:
                result = Calculations.doAddition(firstNumber , secondNumber);
                break;
            case SUB:
                result = Calculations.doSubtraction(firstNumber , secondNumber);
                break;
            case MUL:
                result = Calculations.doMultiplication(firstNumber , secondNumber);
                break;
            case DIV:
                result = Calculations.doDivision(firstNumber , secondNumber);
                break;
            default:
                result = secondNumber;
        }
        numberView.setText(Integer.toString(result));
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = ( Button ) v;
        switch ( clickedButton . getId ()) {
            case R.id.buttonPlus:
                clearNumberView();
                state = State.ADD;
                break;
            case R.id.buttonMinus:
                clearNumberView ();
                state = State.SUB;
                break;
            case R.id.buttonTimes:
                clearNumberView ();
                state = State.MUL;
                break;
            case R.id.buttonDevide:
                clearNumberView ();
                state = State.DIV;
                break;
            case R.id.buttonCalculate:
                calculateResult ();
                state = State.INIT;
                break;
            case R.id.buttonC:
                clearTextView ();
                break;
            default:
                String recentNumber = numberView . getText (). toString ();
                if ( state == State.INIT) {
                    recentNumber = "";
                    state = State.NUM;
                }
                recentNumber += clickedButton . getText (). toString ();
                numberView . setText ( recentNumber );
        }
    }
}
