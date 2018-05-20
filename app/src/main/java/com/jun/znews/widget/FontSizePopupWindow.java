package com.jun.znews.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jun.znews.R;

public class FontSizePopupWindow extends PopupWindow {

    private TextView font_size_big, font_size_middle, font_size_small;
    private View contentView;

    public FontSizePopupWindow(Context context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.font_size_select, null);
        font_size_big = contentView.findViewById(R.id.font_size_big);
        font_size_middle = contentView.findViewById(R.id.font_size_middle);
        font_size_small = contentView.findViewById(R.id.font_size_small);
        font_size_big.setOnClickListener(itemsOnClick);
        font_size_middle.setOnClickListener(itemsOnClick);
        font_size_small.setOnClickListener(itemsOnClick);


        this.setContentView(contentView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setTouchable(true);
        this.setAnimationStyle(R.style.fontSizeAnimation);
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        this.setBackgroundDrawable(dw);

        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }


}
