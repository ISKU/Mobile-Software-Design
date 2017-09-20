package lab.lab2_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private EditText editText_inputMessage;
    private TextView textView_inputCount;
    private Button button_send;
    private Button button_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_inputCount = (TextView) findViewById(R.id.textView_inputCount);

        editText_inputMessage = (EditText) findViewById(R.id.editText_inputMessage);
        editText_inputMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    byte[] strBytes = charSequence.toString().getBytes("KSC5601");
                    textView_inputCount.setText(strBytes.length + " / 80 Bytes");
                } catch (UnsupportedEncodingException error) {
                    error.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                try {
                    byte[] strBytes = text.toString().getBytes("KSC5601");
                    if (strBytes.length > 80)
                        editable.delete(editable.length() - 2, editable.length() - 1);
                } catch (UnsupportedEncodingException error) {
                    error.printStackTrace();
                }
            }
        });

        button_send = (Button) findViewById(R.id.button_send);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "입력한 내용\n\n";
                message += editText_inputMessage.getText().toString();

                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });

        button_close = (Button) findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
