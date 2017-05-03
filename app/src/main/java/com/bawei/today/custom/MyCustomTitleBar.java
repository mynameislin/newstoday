package com.bawei.today.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by admin on 2017/3/15.
 */

public class MyCustomTitleBar extends RelativeLayout {
    private Button titlebar_btn;
    private TextView titlebar_title;
    public MyCustomTitleBar(Context context) {
        super(context);
    }

    public MyCustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);


    }


}
