package hw.ms_hw03_1_201201356;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static int MAX_NUMBER = 10;
    public static HashMap<String, String> MAP_OPERATOR = new HashMap<String, String>() {{
        put("add", "+");
        put("subtract", "-");
        put("multiply", "*");
        put("divide", "/");
    }};

    private EditText edittext_window;
    private EditText edittext_result;
    private Button[] button_number;
    private Button[] button_operator;
    private Button button_result;
    private Button button_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext_window = (EditText) findViewById(R.id.edittext_window);
        edittext_result = (EditText) findViewById(R.id.edittext_result);

        button_number = new Button[MAX_NUMBER];
        for (int i = 0; i < button_number.length; i++) {
            button_number[i] = (Button) findViewById(getResources().getIdentifier("button_" + i, "id", getPackageName()));
            button_number[i].setOnClickListener(new OperButtonClickListener(String.valueOf(i)));
        }

        int i = 0;
        button_operator = new Button[MAP_OPERATOR.size()];
        for (String operator : MAP_OPERATOR.keySet()) {
            button_operator[i] = (Button) findViewById(getResources().getIdentifier("button_" + operator, "id", getPackageName()));
            button_operator[i++].setOnClickListener(new OperButtonClickListener(MAP_OPERATOR.get(operator)));
        }

        button_result = (Button) findViewById(R.id.button_result);
        button_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittext_result.setText(calculation(edittext_window.getText().toString()));
                edittext_window.setText("");
            }
        });

        button_clear = (Button) findViewById(R.id.button_clear);
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittext_window.setText("");
                edittext_result.setText("");
            }
        });
    }

    private String calculation(String line) {
        try {
            String[] operand = line.split("[+\\-\\*/]");
            String operator = line.replaceAll("[0-9]", "");
            int result = 0;

            if (operator.equals("+"))
                result = Integer.parseInt(operand[0]) + Integer.parseInt(operand[1]);
            else if (operator.equals("-"))
                result = Integer.parseInt(operand[0]) - Integer.parseInt(operand[1]);
            else if (operator.equals("*"))
                result = Integer.parseInt(operand[0]) * Integer.parseInt(operand[1]);
            else if (operator.equals("/"))
                result = Integer.parseInt(operand[0]) / Integer.parseInt(operand[1]);

            return String.valueOf(result);
        } catch (Exception error) {
            return "Invalid input";
        }
    }

    private class OperButtonClickListener implements View.OnClickListener {
        public String value;

        public OperButtonClickListener(String value) {
            this.value = value;
        }

        @Override
        public void onClick(View v) {
            edittext_window.setText(edittext_window.getText().toString() + value);
        }
    }
}