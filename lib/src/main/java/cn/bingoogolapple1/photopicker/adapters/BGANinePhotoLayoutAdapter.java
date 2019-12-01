package cn.bingoogolapple1.photopicker.adapters;

import java.util.ArrayList;

import androidx.databinding.BindingAdapter;
import cn.bingoogolapple1.photopicker.widget.BGANinePhotoLayout;

/**
 * 描述:
 */
public class BGANinePhotoLayoutAdapter {

    @BindingAdapter({"bga_npl_delegate"})
    public static void setDelegate(BGANinePhotoLayout ninePhotoLayout, BGANinePhotoLayout.Delegate delegate) {
        ninePhotoLayout.setDelegate(delegate);
    }

    @BindingAdapter({"bga_npl_data"})
    public static void setData(BGANinePhotoLayout ninePhotoLayout, ArrayList<String> data) {
        ninePhotoLayout.setData(data);
    }
}
