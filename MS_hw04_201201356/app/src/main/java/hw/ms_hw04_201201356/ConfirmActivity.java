package hw.ms_hw04_201201356;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmActivity extends AppCompatActivity {

    private EditText edittext_confirm;
    private Button button_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getIntent = getIntent();
        final String type = getIntent.getStringExtra("type");
        final String confirmMessage = getIntent.getStringExtra("msg");
        final int confirm = getIntent.getIntExtra("confirm", 0);
        Toast.makeText(getApplicationContext(), confirmMessage, Toast.LENGTH_LONG).show();

        edittext_confirm = (EditText) findViewById(R.id.edittext_confirm);
        edittext_confirm.setText(confirmMessage);

        button_menu = (Button) findViewById(R.id.button_menu);
        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("msg", String.format("%s 응답\nResultCode: %d\nMessage: (%s)", type, confirm, (confirm == 0) ? "거절" : "승인"));

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
