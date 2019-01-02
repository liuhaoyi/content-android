package com.example.liuhy.news.js;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.liuhy.news.MainActivity;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by liuhy on 2018/12/5.
 */
// 继承自Object类
public class Android2JS extends Object{
    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解

    private Activity activity   = null;
    public Android2JS(Activity activity){
        this.activity = activity;
    }
//    @JavascriptInterface
//    public void showShareAction(String text,String imageUrl,String title,String titleUrl) {
//
////        System.out.println("JS调用了Android的hello方法---"+ params);
//        //   "text" : this.props.location.query.title,
//        //   "imageUrl" : "http://img0.bdstatic.com/img/image/shouye/tangwei.jpg",
//        //   "title" : this.props.location.query.title,
//        //   "titleUrl" : "http://192.168.2.116:8000/detail/content?id=" + this.props.location.query.id,
////        Gson gson = new Gson();
//        System.out.println(text);
//        System.out.println(imageUrl);
//        System.out.println(title);
//        System.out.println(titleUrl);
//
//    }
    @JavascriptInterface
    public void showShareAction(String str) {

//        System.out.println("JS调用了Android的hello方法---"+ params);
        //   "text" : this.props.location.query.title,
        //   "imageUrl" : "http://img0.bdstatic.com/img/image/shouye/tangwei.jpg",
        //   "title" : this.props.location.query.title,
        //   "titleUrl" : "http://192.168.2.116:8000/detail/content?id=" + this.props.location.query.id,
//        Gson gson = new Gson();
        System.out.println(str);

        try {


            JSONObject jsonObj = new JSONObject(str);
            String title = jsonObj.getString("title");
            String imgUrl = jsonObj.getString("imgUrl");
            String titleUrl = jsonObj.getString("titleUrl");
            UMImage thumb =null;
            if(null!=imgUrl &&"null".equals(imgUrl)){
                thumb = new UMImage(this.activity, imgUrl);
            }
            UMWeb web = new UMWeb(titleUrl);
            web.setTitle(title);//标题
//            web.setThumb(thumb);  //缩略图
            web.setDescription(title);//描述
//
//
////            new ShareAction(this.activity)
////                    .withMedia(web)
////                    .share();
//
            new ShareAction(this.activity).withMedia(web).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN_FAVORITE).setCallback(shareListener).open();
//
//            //仅显示标题
//            new ShareAction(this.activity).withText(str).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
//                    .setCallback(shareListener).open();
//
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(Android2JS.this.activity,"成功                                        了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(Android2JS.this.activity,"失                                            败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(Android2JS.this.activity,"取消                                          了",Toast.LENGTH_LONG).show();

        }
    };

}

