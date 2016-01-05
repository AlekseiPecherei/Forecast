package com.pecherey.alexey.weather.logic.forecast;

import android.content.Context;
import android.widget.BaseAdapter;

import static com.alexey.network.decoder.DetailForecastDecoder.getDetailDay;
import static com.alexey.network.decoder.DetailForecastDecoder.getDetailTime;

import com.alexey.network.BaseForecast;
import com.alexey.network.DetailForecast;
import com.alexey.network.decoder.DetailForecastDecoder;
import com.alexey.network.decoder.DetailForecastDecoder.DetailTimes;
import com.alexey.network.encoder.Encoder;
import com.alexey.network.interfaces.IForecast;

import org.w3c.dom.Document;

/**
 * Created by Алексей on 04.01.2016.
 */
public class ForecastPresenter implements IForecast {
    private static ForecastPresenter ourInstance = new ForecastPresenter();

    private BaseForecast mForecast = new DetailForecast(this);
    private ForecastAdapter mAdapter;
    private Context mContext;

    public static ForecastPresenter getInstance() {
        return ourInstance;
    }

    private ForecastPresenter() {
    }

    public void update(String place, int day) {
        mForecast.update(place, day);
    }

    @Override
    public void onForecastLoadStart() { }

    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public void onForecastLoadFinish(Document xml) {
        Encoder.show(xml);
        DetailForecastDecoder.DetailTime[] array = {
                getDetailTime(xml, DetailTimes.NIGHT),
                getDetailTime(xml, DetailTimes.MORNING),
                getDetailTime(xml, DetailTimes.DAY),
                getDetailTime(xml, DetailTimes.EVENING)
        };
        if(mAdapter == null) {
            mAdapter = new ForecastAdapter(mContext);
        }
        mAdapter.add(array);
        mAdapter.addDate(getDetailDay(xml));
        mAdapter.notifyDataSetChanged();
    }

    public BaseAdapter getAdapter() {
        return mAdapter;
    }
}
