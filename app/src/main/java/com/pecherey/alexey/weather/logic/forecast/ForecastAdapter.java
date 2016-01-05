package com.pecherey.alexey.weather.logic.forecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexey.network.constants.Keys;
import com.alexey.network.decoder.DetailForecastDecoder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.pecherey.alexey.handler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 04.01.2016.
 */
public class ForecastAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<DetailForecastDecoder.DetailTime[]> list = new ArrayList<>();
    private List<String> dates = new ArrayList<>();

    private ImageLoader loader = ImageLoader.getInstance();


    public ForecastAdapter(Context context) {
        loader.init(ImageLoaderConfiguration.createDefault(context));
        mInflater = LayoutInflater.from(context);
    }

    public void add(DetailForecastDecoder.DetailTime[] item) { list.add(item); }

    public void addDate(String date) { dates.add(date); }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.forecast_list_element, parent, false);
        }

        ForecastSimpleItem[] items = {
                (ForecastSimpleItem) view.findViewById(R.id.view1),
                (ForecastSimpleItem) view.findViewById(R.id.view2),
                (ForecastSimpleItem) view.findViewById(R.id.view3),
                (ForecastSimpleItem) view.findViewById(R.id.view4),
        };

        DetailForecastDecoder.DetailTime[] info = list.get(position);

        for(int i = 0; i < info.length; ++i) {
            String value = info[i].getValue(Keys.KEY_DETAIL_TIME_ATTR);
            String temp = info[i].getValue(Keys.KEY_WEATHER_TEMP);
            String desc = info[i].getValue(Keys.KEY_WEATHER_DESC);

            items[i].setDesc(desc);
            items[i].setTemp(temp);
            items[i].setValue(value);

            String uri = "http://m.gismeteo.ru" + info[i].getValue(Keys.KEY_WEATHER_ICON_SRC);
            loader.displayImage(uri, items[i].getImage());
        }

        TextView date = (TextView) view.findViewById(R.id.date);
        date.setText(dates.get(position));

        return view;
    }
}
