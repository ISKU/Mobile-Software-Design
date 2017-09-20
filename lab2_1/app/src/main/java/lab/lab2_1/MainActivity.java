package lab.lab2_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView_top;
    private ImageView imageView_bottom;
    private Button button_top;
    private Button button_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView_top = (ImageView) findViewById(R.id.imageView_top);
        imageView_bottom = (ImageView) findViewById(R.id.imageView_bottom);

        button_top = (Button) findViewById(R.id.button_top);
        button_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveImageUp();
            }
        });

        button_bottom = (Button) findViewById(R.id.button_bottom);
        button_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveImageDown();
            }
        });
    }

    private void moveImageUp() {
        imageView_top.setImageResource(R.drawable.image01);
        imageView_bottom.setImageResource(0);

        imageView_top.invalidate();
        imageView_bottom.invalidate();
    }

    private void moveImageDown() {
        imageView_top.setImageResource(0);
        imageView_bottom.setImageResource(R.drawable.image01);

        imageView_top.invalidate();
        imageView_bottom.invalidate();
    }
}