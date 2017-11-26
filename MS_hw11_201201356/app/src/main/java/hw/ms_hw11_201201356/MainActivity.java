package hw.ms_hw11_201201356;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText edittext_url;
    private Button button_request;
    private TextView textview_msg;
    private WebView webview_web;

    private Handler httpRequestHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpRequestHandler = new Handler();

        edittext_url = (EditText) findViewById(R.id.edittext_url);
        textview_msg = (TextView) findViewById(R.id.textview_msg);

        webview_web = (WebView) findViewById(R.id.webview_web);
        webview_web.setWebViewClient(new WebViewClient());

        button_request = (Button) findViewById(R.id.button_request);
        button_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String url = edittext_url.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String output = request(url);

                        httpRequestHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                textview_msg.setText(output);
                            }
                        });
                    }
                }).start();

                webview_web.loadUrl(url);
            }
        });
    }

    private String request(String urlStr) {
        StringBuilder output = new StringBuilder();

        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection != null) {
                connection.setConnectTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;

                while ((line = reader.readLine()) != null)
                    output.append(line + "\n");

                reader.close();
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }
}
