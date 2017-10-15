package lab.lab05_2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textview_dialog;
    private TextView textview_list;
    private Button button_dialog;
    private Button button_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview_dialog = (TextView) findViewById(R.id.textview_dialog);
        textview_list = (TextView) findViewById(R.id.textview_list);

        button_dialog = (Button) findViewById(R.id.button_dialog);
        button_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        button_list = (Button) findViewById(R.id.button_list);
        button_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showList();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setTitle("안내");
        dialog.setMessage("종료하시겠습니까?");

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textview_dialog.setText("Yes");
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textview_dialog.setText("No");
            }
        });

        dialog.setNeutralButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textview_dialog.setText("Cancle");
            }
        });

        dialog.show();
    }

    private void showList() {
        final String[] fruits = new String[]{"Apple", "Banana", "Grape", "Orange"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("과일 선택");

        dialog.setItems(fruits, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textview_list.setText(fruits[i]);
            }
        });

        dialog.setPositiveButton("OK", null);

        dialog.show();
    }
}
