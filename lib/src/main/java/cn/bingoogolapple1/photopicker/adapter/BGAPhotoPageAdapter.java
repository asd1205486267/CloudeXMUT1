/**
 * Copyright 2016 bingoogolapple
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.bingoogolapple1.photopicker.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;
import cn.bingoogolapple1.photopicker.R;
import cn.bingoogolapple1.photopicker.imageloader.BGAImage;
import cn.bingoogolapple1.photopicker.util.BGABrowserPhotoViewAttacher;
import cn.bingoogolapple1.photopicker.util.BGAPhotoPickerUtil;
import cn.bingoogolapple1.photopicker.widget.BGAImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 描述:大图预览适配器
 */
public class BGAPhotoPageAdapter extends PagerAdapter {
    private ArrayList<String> mPreviewPhotos;
    private PhotoViewAttacher.OnViewTapListener mOnViewTapListener;

    public BGAPhotoPageAdapter(PhotoViewAttacher.OnViewTapListener onViewTapListener, ArrayList<String> previewPhotos) {
        mOnViewTapListener = onViewTapListener;
        mPreviewPhotos = previewPhotos;
    }

    @Override
    public int getCount() {
        return mPreviewPhotos == null ? 0 : mPreviewPhotos.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        final BGAImageView imageView = new BGAImageView(container.getContext());
        container.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        final BGABrowserPhotoViewAttacher photoViewAttacher = new BGABrowserPhotoViewAttacher(imageView);
        photoViewAttacher.setOnViewTapListener(mOnViewTapListener);
        imageView.setDelegate(new BGAImageView.Delegate() {
            @Override
            public void onDrawableChanged(Drawable drawable) {
                if (drawable != null && drawable.getIntrinsicHeight() > drawable.getIntrinsicWidth() && drawable.getIntrinsicHeight() > BGAPhotoPickerUtil.getScreenHeight()) {
                    photoViewAttacher.setIsSetTopCrop(true);
                    photoViewAttacher.setUpdateBaseMatrix();
                } else {
                    photoViewAttacher.update();
                }
            }
        });

        BGAImage.display(imageView, R.mipmap.bga_pp_ic_holder_dark, mPreviewPhotos.get(position), BGAPhotoPickerUtil.getScreenWidth(), BGAPhotoPickerUtil.getScreenHeight());

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public String getItem(int position) {
        return mPreviewPhotos == null ? "" : mPreviewPhotos.get(position);
    }
}