package com.elifeindia.crm.adapters;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by volive on 6/1/2018.
 */

public interface AdapterCallbackTextView {
    void onClickCallback(TextView view1, TextView view2, TextView view3, TextView view4, LinearLayout ll, int position, String id);
}
