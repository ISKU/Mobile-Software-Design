package lab.lab06_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View view_first;
    private View view_second;
    private TextView textview_log;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview_log = (TextView) findViewById(R.id.textview_log);

        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                appendLog("Called: onDown()");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                appendLog("Called: onShowPress()");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                appendLog("Called: onSingleTapUp()");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                appendLog(String.format("Called: onScroll() - (%f, %f)", v, v1));
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                appendLog("Called: onLongPress()");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                appendLog(String.format("Called: onFling() - (%f, %f)", v, v1));
                return true;
            }
        });

        view_first = findViewById(R.id.view_first);
        view_first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float X = motionEvent.getX();
                float Y = motionEvent.getY();

                if (action == MotionEvent.ACTION_DOWN)
                    appendLog(String.format("%6s: (%f, %f)", "DOWN", X, Y));
                else if (action == MotionEvent.ACTION_MOVE)
                    appendLog(String.format("%6s: (%f, %f)", "MOVE", X, Y));
                else if (action == MotionEvent.ACTION_UP)
                    appendLog(String.format("%6s: (%f, %f)", "UP", X, Y));

                return true;
            }
        });

        view_second = findViewById(R.id.view_second);
        view_second.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(getApplicationContext(), "Pushed BACK button", Toast.LENGTH_LONG).show();
            return true;
        }

        return false;
    }

    private void appendLog(String log) {
        textview_log.append(log + "\n");
    }
}