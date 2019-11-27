package com.cloude.xmut.zxing.common;

import com.google.zxing.Result;

public interface DecodeImgCallback {
    void onImageDecodeSuccess(Result result);

    void onImageDecodeFailed();
}
