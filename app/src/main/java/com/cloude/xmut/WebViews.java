package com.cloude.xmut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import android.webkit.CookieManager;

import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toolbar;


public class WebViews extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webbrowser);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("url");  //获取输入的总体名字
        String url= bundle.getString("ur");  //获取输入的网址
        init(url);

        Toolbar toolbar =(Toolbar) findViewById(R.id.web_toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void init(String url) {

        final LinearLayout loading= (LinearLayout) findViewById(R.id.process_id);

        webView = (WebView) findViewById(R.id.web);

        webView.loadUrl(url); //WebView加载web资源

        final WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true); //启用支持javascript

        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口

        settings.setLoadsImagesAutomatically(true); //支持自动加载图片

        settings.setBuiltInZoomControls(true);  //  设置出现缩放工具

        settings.setDisplayZoomControls(false);//隐藏webview缩放按钮  false 隐藏  true 显示

        settings.setSupportZoom(true); // 支持缩放

        settings.setAllowFileAccess(true); // 允许访问文件

        settings.setUseWideViewPort(true); //启用支持视窗meta标记（可实现双击缩放）

        settings.setLoadWithOverviewMode(true);  //设置默认加载的可视范围是大视野范围

        settings.supportMultipleWindows();//多窗口

        // settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //优先使用缓存

        // settings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存

        //webView.clearHistory();//清除当前webview访问的历史记录
        //只会webview访问历史记录里的所有记录除了当前访问记录

        settings.setCacheMode(WebSettings.LOAD_DEFAULT);  //开启缓存 加速页面读取

        settings.setDomStorageEnabled(true);  // 开启DOM storage

        /*settings.setDatabaseEnabled(true);  // 开启 数据库存储机制
        final String dbPath = getApplicationContext().getDir("db", Context.MODE_PRIVATE).getPath();
        settings.setDatabasePath(dbPath);*/

        settings.setAppCacheEnabled(true);  // 3. 开启Application Cache存储机制
        final String cachePath = getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(cachePath);  // 1. 设置缓存路径
        settings.setAppCacheMaxSize(5*1024*1024);  // 2. 设置缓存大小

        // 5.1以上默认禁止了https和http混用，以下方式是开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                if (!url.startsWith("http")|!url.startsWith("https")){
                    try{
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }catch (Exception e){
                        e.printStackTrace();
                        return false;
                    }
                    return true;
                }
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress>=100){
//                    loading.setVisible(false,false);//加载完网页进度条消失
                    webView.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.GONE);   //提示文字
                }
                else{
                   /* ProgressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    ProgressBar.setProgress(newProgress);//设置进度值*/
                   loading.setVisibility(View.VISIBLE);
                   webView.setVisibility(View.GONE);
                }

            }


            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getActionBar().setTitle(title);
                //title 就是网页的title
            }
        });

        /**
         * 当下载文件时打开系统自带的浏览器进行下载，当然也可以对捕获到的 url 进行处理在应用内下载。
         **/
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

       webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // handler.cancel();// Android默认的处理方式
                handler.proceed();// 接受所有网站的证书
                // handleMessage(Message msg);// 进行其他处理
            }

        });

       /* webView.setWebChromeClient(new WebChromeClient() {   //判断页面加载过程
        @Override

        public void onProgressChanged(WebView view, int newProgress) {
            // TODO Auto-generated method stub
            if (newProgress>=100) {
                // 网页加载完成

                webView.loadUrl(url);   //WebView加载web资源
            } else {
                // 加载中
                webView.loadUrl("https://www.youdao.com/"); //加载本地assets目录下的的html，也可以是网络上的文件

            }

        }
    });*/

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            } else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //恢复webview的状态（不靠谱）
        webView.resumeTimers();
        //激活webView的状态，能正常加载网页
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //当页面被失去焦点被切换到后台不可见状态，需要执行onPause
        //通过onPause动作通知内核暂停所有的动作，比如DOM的解析、plugin的执行、JavaScript执行。
        webView.onPause();
        //当应用程序(存在webview)被切换到后台时，这个方法不仅仅针对当前的webview而是全局的全应用程序的webview
        //它会暂停所有webview的layout，parsing，javascripttimer。降低CPU功耗。（不靠谱）
        webView.pauseTimers();
    }

    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        //部分机型只会弹出一次提示框，调用此方法即可解决此问题。
        result.cancel();
        //返回true表示不弹出系统的提示框，返回false表示弹出
        return false;
    }
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {

        //confirm表示确认，cancel表示取消。
        result.confirm();
        //返回true表示不弹出系统的提示框，返回false表示弹出
        return false;
    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        //confirm表示确认（并返回值），cancel表示取消。
        result.confirm(message);
        //返回true表示不弹出系统的提示框，返回false表示弹出
        return false;
    }

    /**
     * 将cookie设置到 WebView
     * @param url 要加载的 url
     * @param cookie 要同步的 cookie
     */
    public static void syncCookie(String url,String cookie) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(url, cookie);//如果没有特殊需求，这里只需要将session id以"key=value"形式作为cookie即可
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (URLUtil.isNetworkUrl(url)){
            view.loadUrl(url);
            return true;
        } else {
            try {
                startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
