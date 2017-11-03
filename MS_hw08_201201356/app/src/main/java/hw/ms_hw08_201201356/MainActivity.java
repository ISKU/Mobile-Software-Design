package hw.ms_hw08_201201356;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD = 1356;

    private GridView gridview_products;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridview_products = (GridView) findViewById(R.id.gridview_products);
        productAdapter = new ProductAdapter() {{
            addItem(new ProductItem(R.drawable.clothes1, "빈폴", "롱 코트", 300000, "기획상품"));
            addItem(new ProductItem(R.drawable.clothes2, "나이키", "운동화", 70000, "특가상품"));
            addItem(new ProductItem(R.drawable.clothes3, "폴로", "남방", 150000, "기획상품"));
            addItem(new ProductItem(R.drawable.clothes4, "리바이스", "모자", 40000, "기획상품"));
        }};
        gridview_products.setAdapter(productAdapter);

        gridview_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProductItem item = (ProductItem) productAdapter.getItem(i);
                String message = String.format("선택된 제품: %s\n가격: %,d", item.getTitle(), item.getPrice());

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        this.getSupportActionBar().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
            if (requestCode == REQUEST_CODE_ADD)
                if (data != null)
                    productAdapter.addItem((ProductItem) data.getExtras().getParcelable(ProductItem.PRODUCT_ITEM_KEY));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent = new Intent(this, ProductAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ProductAdapter extends BaseAdapter {

        public LinkedList<ProductItem> products;

        public ProductAdapter() {
            this.products = new LinkedList<ProductItem>();
        }

        @Override
        public int getCount() {
            return products.size();
        }

        @Override
        public Object getItem(int i) {
            return products.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            return new ProductItemView(MainActivity.this) {{
                ProductItem item = products.get(i);
                setImage(item.getResId());
                setMaker(item.getMaker());
                setTitle(item.getTitle());
                setPrice(item.getPrice());
                setDescription(item.getDescription());
            }};
        }

        public void addItem(ProductItem item) {
            products.add(item);
            notifyDataSetChanged();
        }
    }
}