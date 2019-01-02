package com.example.liuhy.news;
//
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liuhy.news.js.Android2JS;
import com.umeng.hybrid.UMHBSocialSDK;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

//public class MainActivity extends Activity {

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        WebView webView = new WebView(this);
//        WebViewClient wvClient = new WebViewClient();
//        webView.setWebViewClient(wvClient);
//        webView.setWebChromeClient(new WebChromeClient() {
//            public boolean onJsAlert(WebView view, String url, String message,
//                                     JsResult result) {
//                return super.onJsAlert(view, url, message, result);
//            }
//        });
//
//        // you must call the following line after the webviewclient is set into the webview
//        ShareSDKUtils.prepare(webView, wvClient);
//        setContentView(webView);
////        webView.loadUrl("file:///android_asset/Sample.html");
//        webView.loadUrl("http://192.168.1.104:8001/login");
//    }
//}


public class MainActivity extends AppCompatActivity {

    WebView webView = null;
    ImageView imageview = null;
    private long exitTime = 0;
    private Handler mHandler;
    int alpha = 200;
    int b = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        webView = (WebView) this.findViewById(R.id.webView);
        imageview = this.findViewById(R.id.imageView2);

        this.init();
        initWebView();
        webView.loadUrl("http://192.168.170.9:8000/login");
//        webView.loadUrl("http://192.168.2.116:8000/login");
//        webView.loadUrl("http://39.96.17.251:18000/login");
//        webView.loadUrl("file:///android_asset/Sample.html");
    }


    private void initWebView() {

        WebViewClient webviewClient =  new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                //6.0以下执行
//                Log.i(TAG, "onReceivedError: ------->errorCode" + errorCode + ":" + description);
                //网络未连接
                showErrorPage();
            }

            //处理网页加载失败时
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //6.0以上执行
//                Log.i(TAG, "onReceivedError: ");
                showErrorPage();//显示错误页面
            }
        };


        //加载需要显示的网页
//        webView.loadUrl("file:///android_asset/Sample.html");
        WebSettings mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);    //允许加载javascript
        webView.addJavascriptInterface(new Android2JS(this), "share");

        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setAppCacheMaxSize(1024*1024*8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        mWebSettings.setAppCachePath(appCachePath);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setAppCacheEnabled(true);

        webView.setWebViewClient(webviewClient);
        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
////                Log.i(TAG, "onProgressChanged:----------->" + newProgress);
////                if (newProgress == 100) {
////                    loadingLayout.setVisibility(View.GONE);
////                }
//            }
//
//
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
////                Log.i(TAG, "onReceivedTitle:title ------>" + title);
////                if (title.contains("404")){
////                    showErrorPage();
////                }
//            }
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });


//        webParentView = (RelativeLayout) webView.getParent(); //获取父容器


//        WebSettings mWebSettings = webView.getSettings();
//        mWebSettings.setJavaScriptEnabled(true);    //允许加载javascript
////        webView.addJavascriptInterface(new Android2JS(), "test");
//
//        mWebSettings.setDomStorageEnabled(true);
//        mWebSettings.setAppCacheMaxSize(1024*1024*8);
//        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
//        mWebSettings.setAppCachePath(appCachePath);
//        mWebSettings.setAllowFileAccess(true);
//        mWebSettings.setAppCacheEnabled(true);
//
//        webView.setWebViewClient(new MyWebviewClient());
//        webView.setWebChromeClient(new MyChromeClient());
    }


    private void showErrorPage() {
//        webParentView.removeAllViews(); //移除加载网页错误时，默认的提示信息
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        webParentView.addView(mErrorView, 0, layoutParams); //添加自定义的错误提示的View
    }

    /***
     * 显示加载失败时自定义的网页
     */
    private void initErrorPage() {
//        if (mErrorView == null) {
//            mErrorView = View.inflate(this, R.layout.layout_load_error, null);
//        }
    }

//    private void showShare() {
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
//        oks.setTitle("标题");
//        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite("ShareSDK");
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");
//
//// 启动分享GUI
//        oks.show(this);
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void init(){
        mHandler	 = new Handler();
        imageview  = (ImageView) this.findViewById(R.id.imageView2);
        new Thread(new Runnable() {
            public void run() {
                initApp();
                while (b < 2) {
                    try {
                        if (b == 0) {
                            Thread.sleep(2500);
                            b = 1;
                        } else {
                            Thread.sleep(20);
                        }
                        updateApp();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if(msg.what==0){
                    //逐步显示图片；
                    imageview.setAlpha(alpha);
                    imageview.invalidate();
                }else{
                    //what==1  .加载结束，隐藏图片；
                    imageview.setVisibility(View.GONE);
                }
            }
        };
    }
    public void updateApp() {
        Message message = Message.obtain();
        alpha += 5;
        if (alpha >= 255) {
            b = 2;
//            Intent in = new Intent(this, MainTabActivity.class);
//            startActivity(in);
//            initApp();

            message.what = 1;
        } else {
            message.what = 0;
        }
        mHandler.sendMessage(message);
    }
    public void initApp(){

    }
}
