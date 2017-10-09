package lab.lab4_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edittext_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext_name = (EditText) findViewById(R.id.edittext_name);
        processIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(getApplicationContext(), "command: " + command + ", name: " + name, Toast.LENGTH_LONG).show();
        }
    }

    private void clickServiceButton(View v) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("command", "show");
        intent.putExtra("name", edittext_name.getText().toString());

        startService(intent);
    }
}