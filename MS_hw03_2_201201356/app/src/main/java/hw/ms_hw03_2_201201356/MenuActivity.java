package hw.ms_hw03_2_201201356;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button button_clients;
    private Button button_sales;
    private Button button_products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button_clients = (Button) findViewById(R.id.button_clients);
        button_clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("menu", "메뉴: 고객 관리");

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        button_sales = (Button) findViewById(R.id.button_sales);
        button_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("menu", "메뉴: 매출 관리");

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        button_products = (Button) findViewById(R.id.button_products);
        button_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("menu", "메뉴: 상품 관리");

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        intentProcessing(getIntent());
    }

    private void intentProcessing(Intent intent) {
        String id = intent.getStringExtra("id");
        String pw = intent.getStringExtra("pw");
        String message = String.format("Username: %s\nPassword: %s", id, pw);

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
