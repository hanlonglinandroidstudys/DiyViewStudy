package com.example.diyviewstudy.view.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.example.diyviewstudy.view.glide.inner.HolderFragment;
import com.example.diyviewstudy.view.glide.inner.ILifeCircleObserver;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

/**
 * create by DragonForest at 2020/4/11
 */
public class Glide implements ILifeCircleObserver {
    String TAG = "Glide";

    Context context;
    String url;
    int resid = -1;

    HashMap<FragmentManager, HolderFragment> holderFragmentArrayMap = new HashMap<>();

    public static Glide getInstance() {
        return new Glide();
    }

    public Glide with(Context context) {
        this.context = context;
        bindLifeCircle(context);
        return this;
    }

    public Glide load(String url) {
        this.url = url;
        return this;
    }

    public Glide load(int id) {
        this.resid = id;
        return this;
    }

    public void into(ImageView imageView) {
        if (resid != -1) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resid);
            imageView.setImageBitmap(bitmap);
        }
    }

    /**
     * 绑定生命周期
     *
     * @param context
     */
    private void bindLifeCircle(Context context) {
        if (context == null)
            throw new RuntimeException("context不能为空！");
        if (context instanceof AppCompatActivity) {
            FragmentManager supportFragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            if (supportFragmentManager.findFragmentByTag("HOLDER") == null
                    && holderFragmentArrayMap.get(supportFragmentManager) == null) {

                HolderFragment holderFragment = new HolderFragment();
                holderFragmentArrayMap.put(supportFragmentManager, holderFragment);

                Log.e(TAG, "bindLifeCircle: 添加holder");
                supportFragmentManager.beginTransaction().add(holderFragment, "HOLDER").commitAllowingStateLoss();

                //发送消息，当接收消息时，说明fragment已经添加完成
                Message msg = Message.obtain();
                msg.what = 0;
                msg.obj = supportFragmentManager;
                finishCommitHandler.sendMessage(msg);
            }

            // 发送消息，添加监听
            Message msg = Message.obtain();
            msg.what = 1;
            msg.obj = supportFragmentManager;
            finishCommitHandler.sendMessage(msg);
        }
    }

    Handler finishCommitHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
                    // 添加已经完成
                    FragmentManager fm = (FragmentManager) msg.obj;
                    holderFragmentArrayMap.remove(fm);
                    Log.e(TAG, "handleMessage: 添加holder完成");
                    break;
                }
                case 1: {
                    // 添加监听
                    FragmentManager fm = (FragmentManager) msg.obj;
                    HolderFragment holderFragment = (HolderFragment) fm.findFragmentByTag("HOLDER");
                    holderFragment.addObserver(Glide.this);
                    Log.e(TAG, "handleMessage: 添加监听完成");
                    break;
                }
            }

        }
    };

    @Override
    public void onStart() {
        Log.e(TAG, "图片开始显示" + resid);
    }

    @Override
    public void onStop() {
        Log.e(TAG, "图片准备释放" + resid);
        context = null;
    }
}