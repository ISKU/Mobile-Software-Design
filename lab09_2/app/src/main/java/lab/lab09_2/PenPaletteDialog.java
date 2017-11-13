package lab.lab09_2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class PenPaletteDialog extends Activity {

    private GridView gridview_color;
    private Button button_close;
    private PenDataAdapter penDataAdapter;

    public static OnPenSelectedListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        this.setTitle("Select line thickness");
        gridview_color = (GridView) findViewById(R.id.gridview_color);
        button_close = (Button) findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        gridview_color.setColumnWidth(14);
        gridview_color.setBackgroundColor(Color.GRAY);
        gridview_color.setVerticalSpacing(4);
        gridview_color.setHorizontalSpacing(4);

        penDataAdapter = new PenDataAdapter(this);
        gridview_color.setAdapter(penDataAdapter);
        gridview_color.setNumColumns((penDataAdapter.getNumColumns()));
    }
}
