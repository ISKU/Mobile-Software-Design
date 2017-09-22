package lab.lab3_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    public static final String KEY_SIMPLE_DATA = "data";

    private TextView textview_data;
    private Button button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textview_data = (TextView) findViewById(R.id.textview_data);
        button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike");

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        processIntent(getIntent());
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            SimpleData data = (SimpleData) bundle.getParcelable(KEY_SIMPLE_DATA);

            textview_data.setText("Received Data\n" +
                    "Number: " + data.getNumber() + "\n" +
                    "Message: " + data.getMessage()
            );
        }
    }
}
