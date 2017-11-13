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

public class ColorDataAdapter extends BaseAdapter {

    public Context mContext;
    public static final int[] colors = new int[]{
            0xff000000, 0xff00007f, 0xff0000ff, 0xff007f00, 0xff007f7f, 0xff00ff00, 0xff00ff7f,
            0xff00ffff, 0xff7f007f, 0xff7f00ff, 0xff7f7f00, 0xff7f7f7f, 0xffff0000, 0xffff007f,
            0xffff00ff, 0xffff7f00, 0xffff7f7f, 0xffff7fff, 0xffffff00, 0xffffff7f, 0xffffffff
    };

    public int rowCount;
    public int columnCount;

    public ColorDataAdapter(Context context) {
        super();

        this.mContext = context;
        this.rowCount = 3;
        this.columnCount = 7;
    }

    @Override
    public int getCount() {
        return rowCount * columnCount;
    }

    @Override
    public Object getItem(int i) {
        return colors[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("ColorDataAdapter", "getView(" + i + ") called.");

        int rowIndex = i / rowCount;
        int columnIndex = i % rowCount;
        Log.d("ColorDataAdapter", "Index: " + rowIndex + ", " + columnIndex);

        GridView.LayoutParams params = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT);

        Button altem = new Button(mContext);
        altem.setText(" ");
        altem.setLayoutParams(params);
        altem.setPadding(4, 4, 4, 4);
        altem.setBackgroundColor(colors[i]);
        altem.setHeight(120);
        altem.setTag(colors[i]);

        altem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ColorPaletteDialog.listener != null)
                    ColorPaletteDialog.listener.onColorSelected(((Integer) view.getTag()).intValue());
                ((ColorPaletteDialog) mContext).finish();
            }
        });

        return altem;
    }

    public int getNumColumns() {
        return columnCount;
    }
}
