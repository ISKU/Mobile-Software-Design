package lab.lab08_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ImageDisplayView extends View implements View.OnTouchListener {

    public static final String TAG = "ImageDisplayView";

    private Context mContext;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Bitmap sourceBitmap;
    private Paint mPaint;
    private Matrix mMatrix;

    private int lastX;
    private int lastY;

    private float sourceWidth = 0.0F;
    private float sourceHeight = 0.0F;
    private float bitmapCenterX;
    private float bitmapCenterY;
    private float scaleRatio;
    private float totalScaleRatio;
    private float displayWidth = 0.0F;
    private float displayHeight = 0.0F;

    private int displayCenterX = 0;
    private int displayCenterY = 0;

    private float startX;
    private float startY;

    public static final float MAX_SCALE_RATIO = 5.0F;
    public static final float MIN_SCALE_RATIO = 0.1F;

    private float oldDistance = 0.0F;
    private int oldPointerCount = 0;
    private boolean isScrolling = false;
    private float distanceThreshold = 3.0F;

    public ImageDisplayView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ImageDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mMatrix = new Matrix();
        lastX = -1;
        lastY = -1;

        setOnTouchListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (w > 0 && h > 0) {
            newImage(w, h);
            reDraw();
        }
    }

    public void newImage(int width, int height) {
        Bitmap img = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);

        mBitmap = img;
        mCanvas = canvas;
        displayWidth = (float) width;
        displayHeight = (float) height;
        displayCenterX = width / 2;
        displayCenterY = height / 2;
    }

    public void drawBackground(Canvas canvas) {
        if (canvas != null)
            canvas.drawColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap != null)
            canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void setImageData(Bitmap image) {
        recycle();

        sourceBitmap = image;
        sourceWidth = sourceBitmap.getWidth();
        sourceHeight = sourceBitmap.getHeight();

        bitmapCenterX = sourceBitmap.getWidth() / 2;
        bitmapCenterY = sourceBitmap.getHeight() / 2;

        scaleRatio = 1.0F;
        totalScaleRatio = 1.0F;
    }

    public void recycle() {
        if (sourceBitmap != null)
            sourceBitmap.recycle();
    }

    public void reDraw() {
        if (sourceBitmap == null) {
            Log.d(TAG, "sourceBitmap is null in reDraw().");
            return;
        }

        drawBackground(mCanvas);

        float originX = (displayWidth - (float) sourceBitmap.getWidth());
        float originY = (displayHeight - (float) sourceBitmap.getHeight());

        mCanvas.translate(originX, originY);
        mCanvas.drawBitmap(sourceBitmap, mMatrix, mPaint);
        mCanvas.translate(-originX, -originY);

        invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        int pointerCount = motionEvent.getPointerCount();
        Log.d(TAG, "Pointer Count: " + pointerCount);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (pointerCount == 1) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();

                    startX = x;
                    startY = y;
                }
                else if (pointerCount == 2) {
                    oldDistance = 0.0F;
                    isScrolling = true;
                }

                return true;

            case MotionEvent.ACTION_MOVE:
                if (pointerCount == 1) {
                    if (isScrolling)
                        return true;

                    float x = motionEvent.getX();
                    float y = motionEvent.getY();

                    if (startX == 0.0F) {
                        startX = x;
                        startY = y;
                        return true;
                    }

                    float offsetX = startX - x;
                    float offsetY = startY - y;

                    if (oldPointerCount != 2) {
                        Log.d(TAG, "ACTION MOVE: " + offsetX + ", " + offsetY);

                        if (totalScaleRatio > 1.0F)
                            moveImage(-offsetX, -offsetY);

                        startX = x;
                        startY = y;
                    }
                }
                else if (pointerCount == 2) {
                    float x1 = motionEvent.getX(0);
                    float y1 = motionEvent.getY(0);
                    float x2 = motionEvent.getX(1);
                    float y2 = motionEvent.getY(1);
                    float dx = x1 - x2;
                    float dy = y1 - y2;
                    float distance = new Double(Math.hypot(dx, dy)).floatValue();

                    float outScaleRatio = 0.0F;
                    if (oldDistance == 0.0F) {
                        oldDistance = distance;
                        break;
                    }

                    if (distance > oldDistance) {
                        if ((distance - oldDistance) < distanceThreshold)
                            return true;

                        outScaleRatio = scaleRatio + (oldDistance / distance * 0.05F);
                    }
                    else if (distance < oldDistance) {
                        if ((oldDistance - distance) < distanceThreshold)
                            return true;

                        outScaleRatio = scaleRatio - (distance / oldDistance * 0.05F);
                    }

                    if (outScaleRatio < MIN_SCALE_RATIO || outScaleRatio > MAX_SCALE_RATIO)
                        Log.d(TAG, "Invalid scaleRatio: " + outScaleRatio);
                    else {
                        Log.d(TAG, "Distance: " + distance + ", ScaleRatio: " + outScaleRatio);
                        scaleImage(outScaleRatio);
                    }

                    oldDistance = distance;
                }

                oldPointerCount = pointerCount;
                break;

            case MotionEvent.ACTION_UP:
                if (pointerCount == 1) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    float offsetX = startX - x;
                    float offsetY = startY - y;

                    if (oldPointerCount != 2)
                        moveImage(-offsetX, -offsetY);
                } else {
                    isScrolling = false;
                }

                return true;
        }

        return true;
    }

    private void scaleImage(float inScaleRatio) {
        Log.d(TAG, "scaleImage() called: " + inScaleRatio);

        mMatrix.postScale(inScaleRatio, inScaleRatio, bitmapCenterX, bitmapCenterY);
        mMatrix.postRotate(0);
        totalScaleRatio = totalScaleRatio * inScaleRatio;

        reDraw();
    }

    private void moveImage(float offsetX, float offsetY) {
        Log.d(TAG, "moveImage() called: " + offsetX + ", " + offsetY);

        mMatrix.postTranslate(offsetX, offsetY);

        reDraw();
    }
}