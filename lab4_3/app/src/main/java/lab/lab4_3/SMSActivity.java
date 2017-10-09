package lab.lab4_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SMSActivity extends AppCompatActivity {

    private EditText edittext_sender;
    private EditText edittext_contents;
    private EditText edittext_receivedDate;
    private Button button_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        edittext_sender = (EditText) findViewById(R.id.edittext_sender);
        edittext_contents = (EditText) findViewById(R.id.edittext_contents);
        edittext_receivedDate = (EditText) findViewById(R.id.edittext_receivedDate);

        button_confirm = (Button) findViewById(R.id.button_confirm);
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        processIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            edittext_sender.setText(intent.getStringExtra("sender"));
            edittext_contents.setText(intent.getStringExtra("contents"));
            edittext_receivedDate.setText(intent.getStringExtra("receivedDate"));
        }
    }
}