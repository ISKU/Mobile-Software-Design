package lab.lab06_3;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textview_contents;
    private Button button_changes;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = this.getSupportActionBar();
        actionBar.setSubtitle("옵션바 살펴보기");
        actionBar.show();

        textview_contents = (TextView) findViewById(R.id.textview_contents);

        button_changes = (Button) findViewById(R.id.button_changes);
        button_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.setLogo(R.drawable.home);
                actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                textview_contents.setText("새로고침 메뉴를 선택했습니다.");
                return true;
            case R.id.menu_search:
                textview_contents.setText("검색 메뉴를 선택했습니다.");
                return true;
            case R.id.menu_settings:
                textview_contents.setText("설정 메뉴를 선택했습니다.");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
