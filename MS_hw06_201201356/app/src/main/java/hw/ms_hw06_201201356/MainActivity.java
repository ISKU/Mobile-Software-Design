package hw.ms_hw06_201201356;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edittext_url;
    private Button button_naver;
    private Button button_move;
    private WebView webview_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext_url = (EditText) findViewById(R.id.edittext_url);

        webview_web = (WebView) findViewById(R.id.webview_web);
        webview_web.setWebViewClient(new WebViewClient() {

            private String errorMessage = null;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

                errorMessage = "Error occurred\n" + "ErrorCode: " + errorCode + "\n" + description;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (errorMessage != null) {
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    errorMessage = null;
                }
                else if (!Patterns.WEB_URL.matcher(url).matches())
                    Toast.makeText(getApplicationContext(), "Error occurred\nBAD URL", Toast.LENGTH_SHORT).show();
            }
        });

        button_naver = (Button) findViewById(R.id.button_naver);
        button_naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview_web.loadUrl("http://www.naver.com");
            }
        });

        button_move = (Button) findViewById(R.id.button_move);
        button_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview_web.loadUrl(edittext_url.getText().toString());
            }
        });
    }
}