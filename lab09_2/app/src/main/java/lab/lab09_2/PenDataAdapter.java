package lab.lab09_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class PenDataAdapter extends BaseAdapter {

    public Context mContext;
    public static final int[] pens = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 17, 20};

    public int rowCount;
    public int columnCount;

    public PenDataAdapter(Context context) {
        super();

        this.mContext = context;
        this.rowCount = 3;
        this.columnCount = 5;
    }

    @Override
    public int getCount() {
        return rowCount * columnCount;
    }

    @Override
    public Object getItem(int i) {
        return pens[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("PendDataAdapter", "getView(" + i + ") called.");

        int rowIndex = i / rowCount;
        int columnIndex = i % rowCount;
        Log.d("PenDataAdapter", "Index: " + rowIndex + ", " + columnIndex);

        GridView.LayoutParams params = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT);

        int areaWidth = 10;
        int areaHeight = 20;

        Bitmap penBitmap = Bitmap.createBitmap(areaWidth, areaHeight, Bitmap.Config.ARGB_8888);
        Canvas penCanvas = new Canvas();
        penCanvas.setBitmap(penBitmap);

        Paint mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        penCanvas.drawRect(0, 0, areaWidth, areaHeight, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth((float) pens[i]);
        penCanvas.drawLine(0, areaHeight / 2, areaWidth - 1, areaHeight / 2, mPaint);
        BitmapDrawable penDrawable = new BitmapDrawable(mContext.getResources(), penBitmap);

        Button altem = new Button(mContext);
        altem.setText(" ");
        altem.setLayoutParams(params);
        altem.setPadding(4, 4, 4, 4);
        altem.setBackgroundDrawable(penDrawable);
        altem.setHeight(120);
        altem.setTag(pens[i]);

        altem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PenPaletteDialog.listener != null)
                    PenPaletteDialog.listener.onPenSelected(((Integer) view.getTag()).intValue());
                ((PenPaletteDialog) mContext).finish();
            }
        });

        return altem;
    }

    public int getNumColumns() {
        return columnCount;
    }
}
