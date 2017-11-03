package hw.ms_hw08_201201356;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProductAddActivity extends AppCompatActivity {

    private EditText edittext_maker;
    private EditText edittext_title;
    private EditText edittext_price;
    private EditText edittext_description;
    private Button button_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edittext_maker = (EditText) findViewById(R.id.edittext_maker);
        edittext_title = (EditText) findViewById(R.id.edittext_title);
        edittext_price = (EditText) findViewById(R.id.edittext_price);
        edittext_description = (EditText) findViewById(R.id.edittext_description);

        button_add = (Button) findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int resId = R.drawable.clothes5;
                String maker = edittext_maker.getText().toString();
                String title = edittext_title.getText().toString();
                int price = Integer.parseInt(edittext_price.getText().toString());
                String description = edittext_description.getText().toString();

                ProductItem item = new ProductItem(resId, maker, title, price, description);
                setResult(RESULT_OK, new Intent().putExtra(ProductItem.PRODUCT_ITEM_KEY, item));
                finish();
            }
        });
    }
}
