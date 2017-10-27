package hw.ms_hw07_201201356;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomerItemView extends LinearLayout {

    private ImageView imageview_icon;
    private TextView textview_name;
    private TextView textview_birth;
    private TextView textview_number;

    public CustomerItemView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_customer, this, true);

        imageview_icon = (ImageView) findViewById(R.id.imageview_icon);
        textview_name = (TextView) findViewById(R.id.textview_name);
        textview_birth = (TextView) findViewById(R.id.textview_birth);
        textview_number = (TextView) findViewById(R.id.textview_number);
    }

    public void setIconResourceId(int iconResourceId) {
        imageview_icon.setImageResource(iconResourceId);
    }

    public void setName(String name) {
        textview_name.setText(name);
    }

    public void setBirth(String birth) {
        textview_birth.setText(birth);
    }

    public void setNumber(String number) {
        textview_number.setText(number);
    }
}