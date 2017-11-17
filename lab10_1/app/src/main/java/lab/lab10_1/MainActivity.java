package lab.lab10_1;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final int spacing = -45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoverFlow coverFlow = (CoverFlow) findViewById(R.id.coverflow);
        coverFlow.setAdapter(new ImageAdapter(this));
        coverFlow.setSpacing(spacing);
        coverFlow.setSelection(2, true);
    }

    private class ImageAdapter extends BaseAdapter {

        private Context mContext;
        private Integer[] mImageIds = {R.drawable.item01, R.drawable.item02, R.drawable.item03, R.drawable.item04, R.drawable.item05};
        private ImageView[] mImages;

        public ImageAdapter(Context context) {
            mContext = context;
            mImages = new ImageView[mImageIds.length];
        }

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView image = new ImageView(mContext);
            image.setImageResource(mImageIds[i]);
            image.setLayoutParams(new CoverFlow.LayoutParams(500, 280));
            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
            drawable.setAntiAlias(true);

            return image;
        }
    }
}