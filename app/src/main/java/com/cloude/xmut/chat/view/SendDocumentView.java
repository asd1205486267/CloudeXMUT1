package com.cloude.xmut.chat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cloude.xmut.R;
import com.cloude.xmut.chat.adapter.DocumentAdapter;


public class SendDocumentView extends LinearLayout {

    private ListView mListVew;


    public SendDocumentView(Context context) {
        super(context);
    }

    public SendDocumentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initModule() {
        mListVew = (ListView) findViewById(R.id.file_list_view);
    }

    public void setAdapter(DocumentAdapter adapter) {
        mListVew.setAdapter(adapter);
    }
}
