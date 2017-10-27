package hw.ms_hw07_201201356;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private EditText edittext_name;
    private EditText edittext_birth;
    private EditText edittext_number;
    private Button button_add;
    private TextView textview_count;
    private ListView listview_customer;

    private CustomerListAdapter customerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext_name = (EditText) findViewById(R.id.edittext_name);
        edittext_birth = (EditText) findViewById(R.id.edittext_birth);
        edittext_number = (EditText) findViewById(R.id.edittext_number);
        textview_count = (TextView) findViewById(R.id.textview_count);

        button_add = (Button) findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int iconResourceId = R.drawable.customer;
                String name = edittext_name.getText().toString();
                String birth = edittext_birth.getText().toString();
                String number = edittext_number.getText().toString();

                customerListAdapter.addItem(new CustomerItem(iconResourceId, name, birth, number));
                customerListAdapter.notifyDataSetChanged();
            }
        });

        listview_customer = (ListView) findViewById(R.id.listview_customer);
        customerListAdapter = new CustomerListAdapter();
        listview_customer.setAdapter(customerListAdapter);

        listview_customer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int itemIndex, long l) {
                new AlertDialog.Builder(MainActivity.this) {{
                    setIcon(android.R.drawable.ic_dialog_alert);
                    setTitle("안내");
                    setMessage("삭제하시겠습니까?");

                    setNegativeButton("No", null);
                    setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            customerListAdapter.removeItem(itemIndex);
                            customerListAdapter.notifyDataSetChanged();
                        }
                    });

                    show();
                }};
            }
        });
    }

    private class CustomerListAdapter extends BaseAdapter {

        LinkedList<CustomerItem> items = new LinkedList<CustomerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final CustomerItem item = items.get(i);
            CustomerItemView customerItemView = new CustomerItemView(MainActivity.this) {{
                setIconResourceId(item.getIconResourceId());
                setName(item.getName());
                setBirth(item.getBirth());
                setNumber(item.getNumber());
            }};

            return customerItemView;
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            edittext_name.setText("");
            edittext_birth.setText("");
            edittext_number.setText("");
            textview_count.setText(getCount() + "명");
        }

        public void addItem(CustomerItem item) {
            items.add(item);
        }

        public void removeItem(int i) {
            items.remove(i);
        }
    }
}
