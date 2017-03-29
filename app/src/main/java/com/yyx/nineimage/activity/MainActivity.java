package com.yyx.nineimage.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.yyx.nineimage.R;
import com.yyx.nineimage.fragment.NineImgFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    protected int mcurrentTabIndex = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    showFragment(1);
                    return true;
                case R.id.navigation_notifications:
                    showFragment(2);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //未加载数据,则一次性加载
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList == null || fragmentList.size() == 0 ) {
            Fragment[] fragments = new Fragment[]{
                    new NineImgFragment(),
                    new NineImgFragment(),
                    new NineImgFragment(),
            };
            addFragment(R.id.content,fragments,0);
        }
    }

    protected void addFragment(int resId,Fragment[] fragments, int showIndex) {
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        for(int i=0;i<fragments.length;i++){
            if (fragments[i].isAdded()) {
                throw new RuntimeException(fragments[i].getClass().getSimpleName() + " already added.");
            } else {
                trx.add(resId, fragments[i]);
                if(showIndex==i){
                    mcurrentTabIndex=showIndex;
                    trx.show(fragments[i]);
                }else {
                    trx.hide(fragments[i]);
                }
            }
        }
        trx.commit();

    }

    protected void showFragment(int index) {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList == null || fragmentList.size() == 0) {
            return;
        }
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        //为了,页面访问路径不交叉(同时打开2个页面),先关闭所有的
        for (int i = 0; i < fragmentList.size(); i++) {
            if (i != index) {
                trx.hide(fragmentList.get(i));
            }
        }
        //再显示
        if (index >= 0 && index < fragmentList.size()) {
            mcurrentTabIndex = index;
            trx.show(fragmentList.get(index));
        }
        trx.commit();
    }

}
