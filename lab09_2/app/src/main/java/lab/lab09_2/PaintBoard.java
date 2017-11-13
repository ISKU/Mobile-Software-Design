package lab.lab09_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Stack;

public class PaintBoard extends View {

    public static final int MAX_UNDO = 10;
    public static final float TOUCH_TOLERANCE = 8;
    private static final boolean RERNDERING_ANTIALIAS = true;
    private static final boolean DITHER_FLAG = true;

    private int mInvalidateExtraBorder = 10;
    private int mCertainColor = 0xff000000;
    private float mStrokeWidth = 2.0f;

    private Stack undos = new Stack();
    private final Path mPath = new Path();
    public boolean changed = false;

    private Canvas mCanvas;
    private Bitmap mBitmap;
    public final Paint mPaint;

    private float lastX;
    private float lastY;
    private float mCurveEndX;
    private float mCurveEndY;

    public PaintBoard(Context context) {
        super(context);
        this.mPaint = new Paint();
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(mStrokeWidth);
        this.mPaint.setColor(mCertainColor);
        this.mPaint.setAntiAlias(RERNDERING_ANTIALIAS);
        this.mPaint.setDither(DITHER_FLAG);
    }

    public void clearUndo() {
        while (true) {
            Bitmap prev = (Bitmap) undos.pop();
            if (prev == null)
                return;

            prev.recycle();
        }
    }

    public void saveUndo() {
        if (mBitmap == null)
            return;

        while (undos.size() >= MAX_UNDO) {
            Bitmap cur = (Bitmap) undos.get(undos.size() - 1);
            cur.recycle();
            undos.remove(cur);
        }

        Bitmap img = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        undos.push(img);
    }

    public void undo() {
        Bitmap prev = null;

        try {
            prev = (Bitmap) undos.pop();
        } catch (Exception e) {
            Log.e("GoodPaintBoard", "Exception: " + e.getMessage());
        }

        if (prev != null) {
            drawBackground(mCanvas);
            mCanvas.drawBitmap(prev, 0, 0, mPaint);
            invalidate();

            prev.recycle();
        }
    }

    public void drawBackground(Canvas canvas) {
        if (canvas != null)
            canvas.drawColor(Color.WHITE);
    }

    public void updatePaintProperty(int color, int size) {
        mPaint.setColor(color);
        mPaint.setStrokeWidth(size);
    }

    public void newImage(int width, int height) {
        Bitmap img = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);

        mBitmap = img;
        mCanvas = canvas;

        drawBackground(mCanvas);

        changed = false;
        invalidate();
    }

    public void setImageSize(int width, int height, Bitmap newImage) {
        if (mBitmap != null) {
            if (width < mBitmap.getWidth())
                width = mBitmap.getWidth();
            if (height < mBitmap.getHeight())
                height = mBitmap.getHeight();
        }

        if (width < 1 || height < 1)
            return;

        Bitmap img = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        drawBackground(canvas);

        if (newImage != null)
            canvas.setBitmap(newImage);

        if (mBitmap != null) {
            mBitmap.recycle();
            mCanvas.restore();
        }

        mBitmap = img;
        mCanvas = canvas;

        clearUndo();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w > 0 && h > 0)
            newImage(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap != null)
            canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_UP:
                changed = true;

                Rect rect = touchUp(event, false);
                if (rect != null)
                    invalidate(rect);

                mPath.rewind();
                return true;

            case MotionEvent.ACTION_DOWN:
                saveUndo();

                rect = touchDown(event);
                if (rect != null)
                    invalidate(rect);

                return true;

            case MotionEvent.ACTION_MOVE:
                rect = touchMove(event);
                if (rect != null)
                    invalidate(rect);

                return true;
        }

        return false;
    }

    private Rect touchDown(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        lastX = x;
        lastY = y;

        Rect mInvalidRect = new Rect();
        mPath.moveTo(x, y);

        final int border = mInvalidateExtraBorder;
        mInvalidRect.set((int) x - border, (int) y - border, (int) x + border, (int) y + border);

        mCurveEndX = x;
        mCurveEndY = y;
        mCanvas.drawPath(mPath, mPaint);

        return mInvalidRect;
    }

    private Rect touchMove(MotionEvent event) {
        return processMove(event);
    }

    private Rect touchUp(MotionEvent event, boolean cancel) {
        return processMove(event);
    }

    private Rect processMove(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();
        final float dx = Math.abs(x - lastX);
        final float dy = Math.abs(y - lastY);

        Rect mInvalidRect = new Rect();
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            final int border = mInvalidateExtraBorder;
            mInvalidRect.set((int) mCurveEndX - border, (int) mCurveEndY - border, (int) mCurveEndX + border, (int) mCurveEndY + border);

            float cX = mCurveEndX = (x + lastX) / 2;
            float cY = mCurveEndY = (y + lastY) / 2;

            mPath.quadTo(lastX, lastY, cX, cY);

            mInvalidRect.union((int) lastX - border, (int) lastY - border, (int) lastX + border, (int) lastY + border);
            mInvalidRect.union((int) cX - border, (int) cY - border, (int) cX + border, (int) cY + border);

            lastX = x;
            lastY = y;
            mCanvas.drawPath(mPath, mPaint);
        }

        return mInvalidRect;
    }
}
