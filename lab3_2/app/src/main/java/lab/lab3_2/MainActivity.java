package lab.lab3_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 101;
    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonViewMenuClicked(View v) {
        SimpleData data = new SimpleData(100, "Hello Android!");

        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.putExtra(KEY_SIMPLE_DATA, data);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_MENU) {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    String name = bundle.getString("name");
                    String message = String.format("Name: %s", name);

                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
