package com.example.implicit_intents;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class EditTextClear extends AppCompatEditText {

    Drawable mClearButtonIcon;

    public EditTextClear(Context context) {
        super(context);
        init();

    }

    public EditTextClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public EditTextClear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mClearButtonIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if((getCompoundDrawablesRelative()[2] != null )) {
                    float clearButtonStart;
                    float clearButtonEnd;
                    boolean isClearButtonClicked = false;

                    if(getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        clearButtonEnd = mClearButtonIcon.getIntrinsicWidth() + getPaddingStart();

                        if(event.getX() < clearButtonEnd) {
                            isClearButtonClicked = true;
                        }

                    } else {
                        clearButtonStart = (getWidth() - getPaddingEnd() -mClearButtonIcon.getIntrinsicWidth());

                        if(event.getX() > clearButtonStart) {
                            isClearButtonClicked = true;

                        }
                    }

                    if(isClearButtonClicked) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            mClearButtonIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);

                            showClearButton();
                        }

                        if(event.getAction() == MotionEvent.ACTION_UP) {
                            mClearButtonIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);
                            getText().clear();
                            hideClearButton();

                            return true;
                        }
                    } else {
                        return false;
                    }
                }

                return false;
            }
        });

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                showClearButton();

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonIcon, null);

    }

    private void hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);

    }
}
