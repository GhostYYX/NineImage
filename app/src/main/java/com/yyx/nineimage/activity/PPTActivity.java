package com.yyx.nineimage.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;

import com.yyx.nineimage.R;
import com.yyx.nineimage.photopicker.ActivityPhotoPickers;

/**
 * Created by vimi8 on 2017/3/30.
 */

public class PPTActivity extends ActivityPhotoPickers {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppt);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        super.initImagePicker(gridView,9,true);
    }
}
