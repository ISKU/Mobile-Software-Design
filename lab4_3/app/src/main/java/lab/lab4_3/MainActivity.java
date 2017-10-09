package lab.lab4_3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionReceiveSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if (permissionReceiveSMS == PackageManager.PERMISSION_GRANTED)
            Toast.makeText(getApplicationContext(), "SMS 수신 권한 있음", Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(getApplicationContext(), "SMS 수신 권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS))
                Toast.makeText(getApplicationContext(), "SMS 권한 설명 필요", Toast.LENGTH_LONG).show();
            else
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(getApplicationContext(), "SMS 권한을 사용자가 승인함", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "SMS 권한 거부됨", Toast.LENGTH_LONG).show();
            default:
                return;
        }
    }
}
