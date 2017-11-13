package lab.lab09_2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by kmh on 17. 11. 13.
 */

public class ColorPaletteDialog extends Activity {

    private GridView gridview_color;
    private Button button_close;
    private ColorDataAdapter colorDataAdapter;

    public static OnColorSelectedListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        this.setTitle("Select Color");
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

        colorDataAdapter = new ColorDataAdapter(this);
        gridview_color.setAdapter(colorDataAdapter);
        gridview_color.setNumColumns((colorDataAdapter.getNumColumns()));
    }
}
