package lab.lab10_1;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public class CoverFlow extends Gallery {

    public static final int maxRotationAngle = 55;
    public static final int maxZoom = -60;

    private Camera camera;
    private int centerPoint;

    public CoverFlow(Context context) {
        super(context);
        init();
    }

    public CoverFlow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CoverFlow(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerPoint = getCenterOfCoverFlow();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void init() {
        this.camera = new Camera();
        this.setStaticTransformationsEnabled(true);
    }

    private int getCenterOfCoverFlow() {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    }

    private static int getCenterOfView(View view) {
        return view.getLeft() + view.getWidth() / 2;
    }

    protected boolean getChildStaticTransformation(View child, Transformation transformation) {
        final int childCenter = getCenterOfView(child);
        final int childWidth = child.getWidth();
        int rotationAngle = 0;
        transformation.clear();
        transformation.setTransformationType(Transformation.TYPE_MATRIX);

        if (childCenter == centerPoint)
            transformImageBitmap((ImageView) child, transformation, 0);
        else {
            rotationAngle = (int) (((float) (centerPoint - childCenter) / childWidth) * maxRotationAngle);

            if (Math.abs(rotationAngle) > maxRotationAngle)
                rotationAngle = (rotationAngle < 0) ? -maxRotationAngle : maxRotationAngle;
            transformImageBitmap((ImageView) child, transformation, rotationAngle);
        }

        return true;
    }

    private void transformImageBitmap(ImageView child, Transformation transformation, int rotationAngle) {
        camera.save();

        final Matrix imageMatrix = transformation.getMatrix();
        final int imageHeight = child.getLayoutParams().height;
        final int imageWidth = child.getLayoutParams().width;
        final int rotation = Math.abs(rotationAngle);

        camera.translate(0.0f, 0.0f, 100.0f);

        if (rotation < maxRotationAngle) {
            float zoomAmount = (float) (maxZoom + (rotation * 1.5));
            camera.translate(0.0f, 0.0f, zoomAmount);
        }

        camera.rotateY(rotationAngle);
        camera.getMatrix(imageMatrix);

        imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
        imageMatrix.postTranslate(imageWidth / 2, imageHeight / 2);

        camera.restore();
    }
}