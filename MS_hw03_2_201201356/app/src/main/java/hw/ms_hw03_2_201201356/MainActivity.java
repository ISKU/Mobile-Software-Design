package hw.ms_hw03_2_201201356;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 101;

    private EditText edittext_id;
    private EditText edittext_pw;
    private Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext_id = (EditText) findViewById(R.id.edittext_id);
        edittext_pw = (EditText) findViewById(R.id.edittext_pw);

        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("id", edittext_id.getText().toString());
                intent.putExtra("pw", edittext_pw.getText().toString());

                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
            if (requestCode == REQUEST_CODE_MENU)
                if (data != null)
                    Toast.makeText(getApplicationContext(), data.getStringExtra("menu"), Toast.LENGTH_SHORT).show();
    }
}
