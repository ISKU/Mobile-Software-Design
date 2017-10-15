package lab.lab05_1;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment_first;
    private Fragment fragment_second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_first = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        fragment_second = new SecondFragment();
    }

    public void onFragmentChanged(int index) {
        if (index == 0)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_second).commit();
        else if (index == 1)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_first).commit();
    }
}
