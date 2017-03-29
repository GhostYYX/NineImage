package com.yyx.nineimage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.ninegrid.NineGridView;
import com.yyx.nineimage.R;
import com.yyx.nineimage.utils.NineImgUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vimi8 on 2017/3/29.
 */

public class NineImgFragment extends Fragment {
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_nine_img,container,false);
        NineGridView nine1 = (NineGridView) view.findViewById(R.id.nine_1);
        List<String> imgList1 = new ArrayList<>();
        imgList1.add("http://cnews.chinadaily.com.cn/img/attachement/png/site1/20170323/a41f726b573a1a3d7bf807.png");
        imgList1.add("http://cnews.chinadaily.com.cn/img/attachement/png/site1/20170323/a41f726b573a1a3d7bf80a.png");
        imgList1.add("http://cnews.chinadaily.com.cn/img/attachement/png/site1/20170323/a41f726b573a1a3d7bf80b.png");
        imgList1.add("http://cnews.chinadaily.com.cn/img/attachement/png/site1/20170323/a41f726b573a1a3d7bf80f.png");
        imgList1.add("http://cnews.chinadaily.com.cn/img/attachement/png/site1/20170323/a41f726b573a1a3d7bf80f.png");
        imgList1.add("http://cnews.chinadaily.com.cn/img/attachement/png/site1/20170323/a41f726b573a1a3d7bf810.png");
        NineImgUtils.funNineGridView(getContext(),imgList1,nine1);

        NineGridView nine2 = (NineGridView) view.findViewById(R.id.nine_2);
        List<String> imgList2 = new ArrayList<>();
        imgList2.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1018122506,3421976594&fm=23&gp=0.jpg");
        imgList2.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3698625380,1448089034&fm=23&gp=0.jpg");
        imgList2.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3698625380,1448089034&fm=23&gp=0.jpg");
        imgList2.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3999788847,752654998&fm=23&gp=0.jpg");
        NineImgUtils.funNineGridView(getContext(),imgList2,nine2);
        return view;
    }
}
