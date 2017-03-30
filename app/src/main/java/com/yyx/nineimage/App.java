package com.yyx.nineimage;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.ninegrid.NineGridView;

/**
 * Created by vimi8 on 2017/3/29.
 */

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initNineGridView();
        context = this.getApplicationContext();

    }

    public static Context getContext() {
        return context;
    }


    public void initNineGridView(){
        NineGridView.setImageLoader(new PicassoImageLoader());
    }

    /** Picasso 加载 */
    class PicassoImageLoader implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Glide.with(context)                             //配置上下文
                    .load(url)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .error(R.mipmap.ic_launcher)           //设置错误图片
                    .placeholder(R.mipmap.ic_launcher)     //设置占位图片
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }
}
