package com.jun.znews.ui.main;

import com.jun.znews.bean.Weather;

public interface IMainContract {

    interface IMainView{
        void setWeather(Weather.HeWeather6Bean heWeather6Bean);
    }

    interface IMainPresenter{
        void getWeather();
    }

    interface IMainModel {

    }
}
