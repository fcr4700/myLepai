package qf.com.myprojectlepai;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 16-9-6.
 */
public class WebViewActivity extends AppCompatActivity{
    //Intent intent;
    WebView webView;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();
        initData();
        initData2();
        initData3();
    }

    private void initData3() {
        String url=getIntent().getStringExtra("qicai_url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view ,String url){
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void initData2() {
        String web_url=getIntent().getStringExtra("bbs_url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(web_url);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view ,String url){
                view.loadUrl(url);
                return true;
            }
        });
    }


    private void initData() {
        String web_url=getIntent().getStringExtra("web_url");
        Log.d("web","==web==tiaozhuang=="+web_url);
        webView.getSettings().setJavaScriptEnabled(true);
//加载需要显示的网页

        webView.loadUrl(web_url);
//设置Web视图
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view ,String url){
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void initView() {
        webView= (WebView) findViewById(R.id.webviewId);
    }
}
