package com.cloude.xmut.chat.utils.keyboard.interfaces;

import android.view.View;
import android.view.ViewGroup;

import com.cloude.xmut.chat.utils.keyboard.data.PageEntity;

public interface PageViewInstantiateListener<T extends PageEntity> {

    View instantiateItem(ViewGroup container, int position, T pageEntity);
}
