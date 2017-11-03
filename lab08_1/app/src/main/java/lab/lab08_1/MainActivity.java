package lab.lab08_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView textview_datetime;
    private DateTimePicker datetimepicker_picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");

        textview_datetime = (TextView) findViewById(R.id.textview_datetime);
        textview_datetime.setText(dateFormat.format(new Date(System.currentTimeMillis())));

        datetimepicker_picker = (DateTimePicker) findViewById(R.id.datetimepicker_picker);
        datetimepicker_picker.setOnDateTimeChangedListener(new DateTimePicker.OnDateTimeChangedListener() {
            @Override
            public void onDateTimeChanged(DateTimePicker view, int year, int month, int day, int hour, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day, hour, minute);

                textview_datetime.setText(dateFormat.format(calendar.getTime()));
            }
        });
    }
}
