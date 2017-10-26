package lab.lab07_3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {

    private ImageView imageview_icon;
    private TextView textview_team;
    private TextView textview_number;
    private TextView textview_year;

    public SingerItemView(Context context) {
        super(context);
        init(context);
    }

    public SingerItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_singer, this, true);

        imageview_icon = (ImageView) findViewById(R.id.imageview_icon);
        textview_team = (TextView) findViewById(R.id.textview_team);
        textview_number = (TextView) findViewById(R.id.textview_number);
        textview_year = (TextView) findViewById(R.id.textview_year);
    }

    public void setIcon(int resId) {
        imageview_icon.setImageResource(resId);
    }

    public void setTeam(String team) {
        textview_team.setText(team);
    }

    public void setNumber(String number) {
        textview_number.setText(number);
    }

    public void setYear(String year) {
        textview_year.setText(year);
    }
}