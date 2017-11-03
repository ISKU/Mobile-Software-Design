package hw.ms_hw08_201201356;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductItemView extends LinearLayout {

    private ImageView imageview_image;
    private TextView textview_maker;
    private TextView textview_title;
    private TextView textview_price;
    private TextView textview_description;

    public ProductItemView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_product, this, true);

        imageview_image = (ImageView) findViewById(R.id.imageview_image);
        textview_maker = (TextView) findViewById(R.id.textview_maker);
        textview_title = (TextView) findViewById(R.id.textview_title);
        textview_price = (TextView) findViewById(R.id.textview_price);
        textview_description = (TextView) findViewById(R.id.textview_description);
    }

    public void setImage(int resId) {
        imageview_image.setImageResource(resId);
    }

    public void setMaker(String maker) {
        textview_maker.setText(String.format("[%s]", maker));
    }

    public void setTitle(String title) {
        textview_title.setText(title);
    }

    public void setPrice(int price) {
        textview_price.setText(String.format("%,d", price));
    }

    public void setDescription(String description) {
        textview_description.setText(description);
    }
}
