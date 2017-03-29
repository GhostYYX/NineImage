package com.yyx.nineimage.utils;

import android.content.Context;

import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vimi8 on 2017/3/29.
 */

public class NineImgUtils {
    /*
    * 九宫格图片显示方法---起始
    * */
    public static void funNineGridView(Context context, List<String> listImage, NineGridView productNineGrid){
        List<ImageInfo> imageInfos = new ArrayList<>();
        for (String image : listImage) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setBigImageUrl(image);
            imageInfo.setThumbnailUrl(image);
            imageInfos.add(imageInfo);
        }
        productNineGrid.setAdapter(new NineGridViewClickAdapter(context, imageInfos));
    }
}
