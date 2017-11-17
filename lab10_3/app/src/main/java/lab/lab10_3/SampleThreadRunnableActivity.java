package lab.lab10_3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SampleThreadRunnableActivity extends Activity {

    private ProgressBar progressbar_progress;
    private TextView textview_status;

    private Handler handler;
    private ProgressRunnable runnable;
    private boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressbar_progress = (ProgressBar) findViewById(R.id.progressbar_progress);
        textview_status = (TextView) findViewById(R.id.textview_status);

        handler = new Handler();
        runnable = new ProgressRunnable();
    }

    @Override
    protected void onStart() {
        super.onStart();

        progressbar_progress.setProgress(0);
        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20 && isRunning; ++i) {
                        Thread.sleep(1000);
                        handler.post(runnable);
                    }
                } catch (Exception e) {
                    Log.e("SampleThreadActivity", "Exception in processing message.", e);
                }
            }
        }).start();
    }

    @Override
    public void onStop() {
        super.onStop();
        isRunning = false;
    }

    public class ProgressRunnable implements Runnable {
        @Override
        public void run() {
            progressbar_progress.incrementProgressBy(5);

            if (progressbar_progress.getProgress() == progressbar_progress.getMax())
                textview_status.setText("Runnable Done");
            else
                textview_status.setText("Runnable Working ..." + progressbar_progress.getProgress());
        }
    }
}
