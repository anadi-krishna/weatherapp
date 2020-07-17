package com.anadi.weatherinfo.details;

import android.content.Context;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.anadi.weatherinfo.repository.data.WeatherInfo;

import java.util.Observer;

public interface DetailsContract {

    interface View {

        void loading();

        void onError(@StringRes int resId);

        void onUpdateSuccess();
    }

    interface Presenter extends Observer {
        void update();

        WeatherInfo getInfo(int id);
    }

    interface Model {
        WeatherInfo getInfo(int id);
        boolean update(int id);
        boolean needUpdate(int id);
    }
}