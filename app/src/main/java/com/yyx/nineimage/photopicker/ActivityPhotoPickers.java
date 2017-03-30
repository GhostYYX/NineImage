package com.yyx.nineimage.photopicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yyx.nineimage.R;
import com.yyx.nineimage.photopicker.utils.ImageLoader;
import com.yyx.nineimage.photopicker.utils.OtherUtils;

import java.util.ArrayList;
import java.util.List;

public class ActivityPhotoPickers extends AppCompatActivity {
    private static final int PICK_PHOTO = 1;

    private GridView mGrideView;
    private List<String> mResults;
    private GridAdapter mAdapter;
    private int mColumnWidth;
    private int imgSum;
    private boolean isCamera;
    private int isPlural;


    /**
     * 图片多选
     * @param recyclerView
     * @param maxImgCount
     * @param isSum
     */
    public final void initImagePicker(final GridView recyclerView, int maxImgCount,boolean isSum){
        isCamera = isSum;
        initImagePicker(recyclerView,maxImgCount);
    }

    public final void initImagePicker(final GridView recyclerView, int maxImgCount) {
        int screenWidth = OtherUtils.getWidthInPx(getApplicationContext());
        mColumnWidth = (screenWidth - OtherUtils.dip2px(getApplicationContext(), 4))/4;
        //初始化ImagePicker
        mGrideView = recyclerView;
        imgSum = maxImgCount;
        isPlural = imgSum==1?0:1;
        ArrayList<String> result = new ArrayList<>();
        result.add("initialise");
        showResult(result);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_PHOTO){
            if(resultCode == RESULT_OK){
                ArrayList<String> result = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
                result.add("initialise");
                showResult(result);
            }
        }
    }

    private void showResult(ArrayList<String> paths){
        if(mResults == null){
            mResults = new ArrayList<>();
        }
        mResults.clear();
        mResults.addAll(paths);

        if(mAdapter == null){
            mAdapter = new GridAdapter(mResults);
            mGrideView.setAdapter(mAdapter);
        }else {
            mAdapter.setPathList(mResults);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class GridAdapter extends BaseAdapter {
        private List<String> pathList;

        public GridAdapter(List<String> listUrls) {
            this.pathList = listUrls;
        }

        @Override
        public int getCount() {
            return pathList.size();
        }

        @Override
        public String getItem(int position) {
            return pathList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void setPathList(List<String> pathList) {
            this.pathList = pathList;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.item_image, null);
                imageView = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(imageView);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mColumnWidth, mColumnWidth);
                imageView.setLayoutParams(params);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(getItem(position).equals("initialise")){
                            Intent intent = new Intent(ActivityPhotoPickers.this, PhotoPickerActivity.class);
                            intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, isCamera);
                            intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, 1);
                            intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, imgSum);
                            startActivityForResult(intent, PICK_PHOTO);
                        }
                    }
                });
            }else {
                imageView = (ImageView) convertView.getTag();
            }
            if(getItem(position).equals("initialise")){
                if(getCount()<imgSum){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.activity_base_image_picker_add));
                    imageView.setMaxWidth(mColumnWidth);
                    imageView.setMaxHeight(mColumnWidth);
                }
            }else {
                ImageLoader.getInstance().display(getItem(position), imageView, mColumnWidth, mColumnWidth);
            }
            return convertView;
        }
    }

}
