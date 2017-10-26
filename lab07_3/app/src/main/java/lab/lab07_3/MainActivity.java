package lab.lab07_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button button_add;
    private EditText edittext_search;
    private GridView gridview_singer;

    private SingerAdapter singerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_add = (Button) findViewById(R.id.button_add);
        edittext_search = (EditText) findViewById(R.id.edittext_search);

        gridview_singer = (GridView) findViewById(R.id.gridview_singer);
        singerAdapter = new SingerAdapter() {{
            addItem(new SingerItem(R.drawable.girlsgeneration, "소녀시대", "010-1234-5678", "2007"));
            addItem(new SingerItem(R.drawable.apink, "에이핑크", "010-5678-1234", "2008"));
            addItem(new SingerItem(R.drawable.girlfriend, "여자친구", "010-5233-6312", "2009"));
            addItem(new SingerItem(R.drawable.redvelvet, "레드벨벳", "010-6322-7452", "2010"));
            addItem(new SingerItem(R.drawable.aoa, "AOA", "010-1225-5321", "2011"));
            addItem(new SingerItem(R.drawable.twice, "트와이스", "010-6663-5432", "2012"));
        }};
        gridview_singer.setAdapter(singerAdapter);

        gridview_singer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SingerItem item = (SingerItem) singerAdapter.getItem(i);
                Toast.makeText(getApplicationContext(), "선택: " + item.getTeam(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class SingerAdapter extends BaseAdapter {

        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

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
            final SingerItem item = items.get(i);
            SingerItemView singerItemView = new SingerItemView(MainActivity.this) {{
                setIcon(item.getIcon());
                setTeam(item.getTeam());
                setNumber(item.getNumber());
                setYear(item.getYear());
            }};

            int numColumns = gridview_singer.getNumColumns();
            int rowIndex = i / numColumns;
            int columnIndex = i % numColumns;

            return singerItemView;
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }
    }
}