package lab.lab10_3;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressbar_progress;
    private TextView textview_status;

    private ProgressHandler handler;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressbar_progress = (ProgressBar) findViewById(R.id.progressbar_progress);
        textview_status = (TextView) findViewById(R.id.textview_status);

        handler = new ProgressHandler();
    }

    @Override
    public void onStart() {
        super.onStart();
        progressbar_progress.setProgress(0);
        isRunning = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20 && isRunning; i++) {
                        Thread.sleep(1000);
                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    Log.e("MainActivity", "Exception in processing message.", e);
                }
            }
        }).start();
    }

    @Override
    public void onStop() {
        super.onStop();
        isRunning = false;
    }

    private class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            progressbar_progress.incrementProgressBy(5);

            if (progressbar_progress.getProgress() == progressbar_progress.getMax())
                textview_status.setText("Done");
            else
                textview_status.setText("Working... " + progressbar_progress.getProgress());
        }
    }
}