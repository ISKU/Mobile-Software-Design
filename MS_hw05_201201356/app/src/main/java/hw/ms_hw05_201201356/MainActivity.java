package hw.ms_hw05_201201356;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText edittext_name;
    private EditText edittext_phoneNumber;
    private Button button_date;
    private Button button_time;
    private Button button_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date currentTime = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        edittext_name = (EditText) findViewById(R.id.edittext_name);
        edittext_phoneNumber = (EditText) findViewById(R.id.edittext_phoneNumber);

        button_date = (Button) findViewById(R.id.button_date);
        button_date.setText(new SimpleDateFormat("yyyy년 MM월 dd일").format(currentTime));
        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        button_date.setText(String.format("%d년 %d월 %d일", y, m + 1, d));
                    }
                }, year, month, day).show();
            }
        });

        button_time = (Button) findViewById(R.id.button_time);
        button_time.setText(new SimpleDateFormat("HH시 mm분").format(currentTime));
        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        button_time.setText(String.format("%d시 %d분", h, m));
                    }
                }, hour, minute, false).show();
            }
        });

        button_save = (Button) findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message =
                        "이름: " + edittext_name.getText().toString() + "\n" +
                        "전화번호: " + edittext_phoneNumber.getText().toString() + "\n" +
                        "날짜: " + button_date.getText() + "\n" +
                        "시간: " + button_time.getText();

                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
