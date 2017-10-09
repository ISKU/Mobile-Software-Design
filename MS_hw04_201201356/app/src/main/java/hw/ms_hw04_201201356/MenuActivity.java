package hw.ms_hw04_201201356;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 201201356;
    public static final int RESULT_CODE_DENY = 0;
    public static final int RESULT_CODE_ALLOW = -1;

    private Button button_location;
    private Button button_calendar;
    private Button button_camera;
    private Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button_location = (Button) findViewById(R.id.button_location);
        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "위치 권한이 있습니다.", Toast.LENGTH_LONG).show();
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MenuActivity.this, Manifest.permission.ACCESS_FINE_LOCATION))
                        Toast.makeText(getApplicationContext(), "위치 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                    else
                        ActivityCompat.requestPermissions(MenuActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
            }
        });

        button_calendar = (Button) findViewById(R.id.button_calendar);
        button_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CALENDAR)
                        == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "달력 권한이 있습니다.", Toast.LENGTH_LONG).show();
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MenuActivity.this, Manifest.permission.READ_CALENDAR))
                        Toast.makeText(getApplicationContext(), "달력 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                    else
                        ActivityCompat.requestPermissions(MenuActivity.this, new String[]{Manifest.permission.READ_CALENDAR}, 1);
                }
            }
        });

        button_camera = (Button) findViewById(R.id.button_camera);
        button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "카메라 권한이 있습니다.", Toast.LENGTH_LONG).show();
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MenuActivity.this, Manifest.permission.CAMERA))
                        Toast.makeText(getApplicationContext(), "카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                    else
                        ActivityCompat.requestPermissions(MenuActivity.this, new String[]{Manifest.permission.CAMERA}, 2);
                }
            }
        });

        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);

        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    intent.putExtra("msg", "위치 권한 인증 성공");
                    intent.putExtra("confirm", RESULT_CODE_ALLOW);
                } else {
                    intent.putExtra("msg", "위치 권한 인증 실패");
                    intent.putExtra("confirm", RESULT_CODE_DENY);
                }

                intent.putExtra("type", "위치 권한");
                startActivityForResult(intent, REQUEST_CODE_MENU);
                return;
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    intent.putExtra("msg", "달력 권한 인증 성공");
                    intent.putExtra("confirm", RESULT_CODE_ALLOW);
                } else {
                    intent.putExtra("msg", "달력 권한 인증 실패");
                    intent.putExtra("confirm", RESULT_CODE_DENY);
                }

                intent.putExtra("type", "달력 권한");
                startActivityForResult(intent, REQUEST_CODE_MENU);
                return;
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    intent.putExtra("msg", "카메라 권한 인증 성공");
                    intent.putExtra("confirm", RESULT_CODE_ALLOW);
                } else {
                    intent.putExtra("msg", "카메라 권한 인증 실패");
                    intent.putExtra("confirm", RESULT_CODE_DENY);
                }

                intent.putExtra("type", "카메라 권한");
                startActivityForResult(intent, REQUEST_CODE_MENU);
                return;
            default:
                return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
            if (requestCode == REQUEST_CODE_MENU)
                if (data != null)
                    Toast.makeText(getApplicationContext(), data.getStringExtra("msg"), Toast.LENGTH_LONG).show();
    }
}