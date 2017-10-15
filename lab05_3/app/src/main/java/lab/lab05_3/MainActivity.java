package lab.lab05_3;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button_progressbar;

    private SeekBar seekbar_seekbar;
    private TextView textview_seekbar;

    private RatingBar ratingbar_ratingbar;
    private TextView textview_ratingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_progressbar = (Button) findViewById(R.id.button_progressbar);
        button_progressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this) {{
                    setMessage("다운로드 중입니다.");
                    setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    setProgress(0);
                    setMax(0);
                    setCancelable(true);
                    show();
                }};

                new Thread() {
                    @Override
                    public void run() {
                        for (int time = 0; time < 100; time += 5) {
                            try {
                                sleep(200);
                                progressDialog.setProgress(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        progressDialog.dismiss();
                    }
                }.start();
            }
        });

        textview_seekbar = (TextView) findViewById(R.id.textview_seekbar);
        seekbar_seekbar = (SeekBar) findViewById(R.id.seekbar_seekbar);
        seekbar_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setBrightness(i);
                textview_seekbar.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        textview_ratingbar = (TextView) findViewById(R.id.textview_ratingbar);
        ratingbar_ratingbar = (RatingBar) findViewById(R.id.ratingbar_ratingbar);
        ratingbar_ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                textview_ratingbar.setText("결과 " + ratingbar_ratingbar.getRating());
            }
        });
    }

    private void setBrightness(int value) {
        value = (value < 10) ? 10 :
                (value > 100) ? 100 : value;

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float) value / 100;
        getWindow().setAttributes(params);
    }
}