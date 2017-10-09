package hw.ms_hw04_201201356;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
                if (edittext_id.getText().toString().equals("") || edittext_pw.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "아이디 또는 패스워드가 입력되지 않았습니다.", Toast.LENGTH_LONG).show();
                else
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });
    }
}
