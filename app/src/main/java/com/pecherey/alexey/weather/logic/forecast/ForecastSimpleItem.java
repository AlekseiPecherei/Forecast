package com.pecherey.alexey.weather.logic.forecast;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pecherey.alexey.handler.R;

/**
 * Created by Алексей on 04.01.2016.
 */
public class ForecastSimpleItem extends LinearLayout {
    private TextView mValue;
    private ImageView mImage;
    private TextView mTemp;
    private TextView mDesc;

    public ForecastSimpleItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.item, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        mValue = (TextView) findViewById(R.id.value);
        mImage = (ImageView) findViewById(R.id.img);
        mTemp = (TextView) findViewById(R.id.temp);
        mDesc = (TextView) findViewById(R.id.desc);
    }

    public void setValue(String text) {
        mValue.setText(text);
    }

    public void setTemp(String text) {
        mTemp.setText(text);
    }

    public ImageView getImage() {
        return mImage;
    }

    public void setImage(Bitmap bm) {
        mImage.setImageBitmap(bm);
    }

    public void setDesc(String text) {
        mDesc.setText(text);
    }
}
