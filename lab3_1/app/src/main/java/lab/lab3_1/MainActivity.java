package lab.lab3_1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edittext_tel;
    private Button button_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext_tel = (EditText) findViewById(R.id.edittext_tel);

        button_call = (Button) findViewById(R.id.button_call);
        button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String callnumber = edittext_tel.getText().toString();
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(callnumber)));
            }
        });
    }
}
