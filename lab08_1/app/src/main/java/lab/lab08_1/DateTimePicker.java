package lab.lab08_1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Calendar;

public class DateTimePicker extends LinearLayout {

    private OnDateTimeChangedListener dateTimeChangedListener;
    private DatePicker datepicker_datepicker;
    private TimePicker timepicker_timepicker;
    private CheckBox checkbox_enable;

    public DateTimePicker(Context context) {
        super(context);
        init(context);
    }

    public DateTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_datetimepicker, this, true);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        datepicker_datepicker = (DatePicker) findViewById(R.id.datepicker_datepicker);
        datepicker_datepicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                if (dateTimeChangedListener != null)
                    dateTimeChangedListener.onDateTimeChanged(DateTimePicker.this, year, month, day, getHour(), getMinute());
            }
        });

        checkbox_enable = (CheckBox) findViewById(R.id.checkbox_enable);
        checkbox_enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                timepicker_timepicker.setEnabled(isChecked);
                timepicker_timepicker.setVisibility(checkbox_enable.isChecked() ? View.VISIBLE : View.INVISIBLE);
            }
        });

        timepicker_timepicker = (TimePicker) findViewById(R.id.timepicker_timepicker);
        timepicker_timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int month) {
                if (dateTimeChangedListener != null)
                    dateTimeChangedListener.onDateTimeChanged(DateTimePicker.this, getYear(), getMonth(), getDay(), hour, month);
            }
        });
        timepicker_timepicker.setCurrentHour(hour);
        timepicker_timepicker.setCurrentMinute(minute);
        timepicker_timepicker.setEnabled(checkbox_enable.isChecked());
        timepicker_timepicker.setVisibility(checkbox_enable.isChecked() ? View.VISIBLE : View.INVISIBLE);
    }

    public interface OnDateTimeChangedListener {
        public abstract void onDateTimeChanged(DateTimePicker view, int year, int month, int day, int hour, int minute);
    }

    public void setOnDateTimeChangedListener(OnDateTimeChangedListener dateTimeChangedListener) {
        this.dateTimeChangedListener = dateTimeChangedListener;
    }

    public int getYear() {
        return datepicker_datepicker.getYear();
    }

    public int getMonth() {
        return datepicker_datepicker.getMonth();
    }

    public int getDay() {
        return datepicker_datepicker.getDayOfMonth();
    }

    public int getHour() {
        return timepicker_timepicker.getHour();
    }

    public int getMinute() {
        return timepicker_timepicker.getMinute();
    }
}
