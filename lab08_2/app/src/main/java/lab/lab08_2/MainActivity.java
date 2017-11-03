package lab.lab08_2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearlayout_container;
    private ImageDisplayView imageDisplayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearlayout_container = (LinearLayout) findViewById(R.id.linearlayout_container);
        Bitmap sourceBitmap = loadImage();

        if (sourceBitmap != null) {
            imageDisplayView = new ImageDisplayView(this);
            imageDisplayView.setImageData(sourceBitmap);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            linearlayout_container.addView(imageDisplayView, params);
        }
    }

    private Bitmap loadImage() {
        return BitmapFactory.decodeResource(getResources(), R.drawable.beach);
    }
}
