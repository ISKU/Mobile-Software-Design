package lab.lab09_2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearlayout_tools;
    private LinearLayout linearlayout_board;
    private Button button_color;
    private Button button_pen;
    private Button button_eraser;
    private Button button_undo;

    private LinearLayout linearlayout_added;
    private Button button_color_legend;
    private TextView textview_size_legend_text;

    private PaintBoard board;

    private int mColor = 0xff000000;
    private int mSize = 2;
    private int oldColor;
    private int oldSize;
    private boolean eraserSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearlayout_tools = (LinearLayout) findViewById(R.id.linearlayout_tools);
        linearlayout_board = (LinearLayout) findViewById(R.id.linearlayout_board);
        button_color = (Button) findViewById(R.id.button_color);
        button_pen = (Button) findViewById(R.id.button_pen);
        button_eraser = (Button) findViewById(R.id.button_eraser);
        button_undo = (Button) findViewById(R.id.button_undo);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        board = new PaintBoard(this);
        board.setLayoutParams(params);
        board.setPadding(2, 2, 2, 2);
        linearlayout_board.addView(board);

        LinearLayout.LayoutParams addedParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 48);

        linearlayout_added = new LinearLayout(this);
        linearlayout_added.setLayoutParams(addedParams);
        linearlayout_added.setOrientation(LinearLayout.VERTICAL);
        linearlayout_added.setPadding(8, 8, 8, 8);

        LinearLayout outlineLayout = new LinearLayout(this);
        outlineLayout.setLayoutParams(buttonParams);
        outlineLayout.setOrientation(LinearLayout.VERTICAL);
        outlineLayout.setBackgroundColor(Color.LTGRAY);
        outlineLayout.setPadding(1, 1, 1, 1);

        button_color_legend = new Button(this);
        button_color_legend.setLayoutParams(buttonParams);
        button_color_legend.setText(" ");
        button_color_legend.setBackgroundColor(mColor);
        button_color_legend.setHeight(20);
        outlineLayout.addView(button_color_legend);
        linearlayout_added.addView(outlineLayout);

        textview_size_legend_text = new TextView(this);
        textview_size_legend_text.setLayoutParams(buttonParams);
        textview_size_legend_text.setText("Size : " + mSize);
        textview_size_legend_text.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        textview_size_legend_text.setTextSize(12);
        textview_size_legend_text.setTextColor(Color.BLACK);
        linearlayout_added.addView(textview_size_legend_text);
        linearlayout_tools.addView(linearlayout_added);

        button_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPaletteDialog.listener = new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int color) {
                        mColor = color;
                        board.updatePaintProperty(mColor, mSize);
                        displayPaintProperty();
                    }
                };

                Intent intent = new Intent(getApplicationContext(), ColorPaletteDialog.class);
                startActivity(intent);
            }
        });

        button_pen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PenPaletteDialog.listener = new OnPenSelectedListener() {
                    @Override
                    public void onPenSelected(int size) {
                        mSize = size;
                        board.updatePaintProperty(mColor, mSize);
                        displayPaintProperty();
                    }
                };

                Intent intent = new Intent(getApplicationContext(), PenPaletteDialog.class);
                startActivity(intent);
            }
        });

        button_eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eraserSelected = !eraserSelected;

                if (eraserSelected) {
                    button_color.setEnabled(false);
                    button_pen.setEnabled(false);
                    button_undo.setEnabled(false);

                    button_color.invalidate();
                    button_pen.invalidate();
                    button_undo.invalidate();

                    oldColor = mColor;
                    oldSize = mSize;

                    mColor = Color.WHITE;
                    mSize = 15;

                    board.updatePaintProperty(mColor, mSize);
                    displayPaintProperty();
                } else {
                    button_color.setEnabled(true);
                    button_pen.setEnabled(true);
                    button_undo.setEnabled(true);

                    button_color.invalidate();
                    button_pen.invalidate();
                    button_undo.invalidate();

                    mColor = oldColor;
                    mSize = oldSize;

                    board.updatePaintProperty(mColor, mSize);
                    displayPaintProperty();
                }
            }
        });

        button_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.undo();
            }
        });
    }

    public int getChosenColor() {
        return mColor;
    }

    public int getPenThickness() {
        return mSize;
    }

    private void displayPaintProperty() {
        button_color_legend.setBackgroundColor(mColor);
        textview_size_legend_text.setText("Size: " + mSize);
        linearlayout_added.invalidate();
    }
}
