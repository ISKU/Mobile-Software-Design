package hw.ms_hw09_201201356;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MoveRectangleView extends View {

    private static final int RECTANGLE_WIDTH = 300;
    private static final int RECTANGLE_HEIGHT = 300;
    private static final int RECTANGLE_WIDTH_CENTER = RECTANGLE_WIDTH / 2;
    private static final int RECTANGLE_HEIGHT_CENTER = RECTANGLE_HEIGHT / 2;

    private static int MAX_DISPLAY_WIDTH;
    private static int MAX_DISPLAY_HEIGHT;

    private Paint rectangle;
    private int startX;
    private int startY;

    public MoveRectangleView(Context context) {
        super(context);

        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        MAX_DISPLAY_WIDTH = display.getWidth();
        MAX_DISPLAY_HEIGHT = display.getHeight() - 300;

        this.startX = randomNumber(0, MAX_DISPLAY_WIDTH - RECTANGLE_WIDTH);
        this.startY = randomNumber(0, MAX_DISPLAY_HEIGHT - RECTANGLE_HEIGHT);

        this.rectangle = new Paint();
        this.rectangle.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(startX, startY, startX + RECTANGLE_WIDTH, startY + RECTANGLE_HEIGHT, rectangle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                startX = (int) event.getX() - RECTANGLE_WIDTH_CENTER;
                startY = (int) event.getY() - RECTANGLE_HEIGHT_CENTER;
                invalidate();
                return true;

            default:
                return false;
        }
    }

    private int randomNumber(int lower, int upper) {
        return (int) (Math.random() * (upper - lower + 1)) + lower;
    }
}
