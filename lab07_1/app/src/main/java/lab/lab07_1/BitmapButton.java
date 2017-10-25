package lab.lab07_1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class BitmapButton extends AppCompatButton {

    public static final int STATUS_BUTTON_NORMAL = 0;
    public static final int STATUS_BUTTON_CLICKED = 1;

    public int iconStatus = STATUS_BUTTON_NORMAL;
    private int iconNormal = R.drawable.bitmap_button_normal;
    private int iconClicked = R.drawable.bitmap_button_clicked;

    public BitmapButton(Context context) {
        super(context);
        init();
    }

    public BitmapButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        int defaultTextcolor = Color.WHITE;
        float defaultTextSize = getResources().getDimension(R.dimen.text_size);
        Typeface defalutTypeface = Typeface.DEFAULT_BOLD;

        setBackgroundResource(iconNormal);
        setTextColor(defaultTextcolor);
        setTextSize(defaultTextSize);
        setTypeface(defalutTypeface);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(iconClicked);
                iconStatus = STATUS_BUTTON_CLICKED;
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(iconNormal);
                iconStatus = STATUS_BUTTON_NORMAL;
                break;
        }

        invalidate();
        return true;
    }
}
