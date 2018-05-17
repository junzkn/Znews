package com.jun.znews.ui.main;

import com.jun.znews.bean.WeatherBean;

public interface IMainContract {

    interface IMainView{
        void setWeather(WeatherBean.HeWeather6Bean heWeather6Bean);
    }

    interface IMainPresenter{
        void getWeather();
    }

    interface IMainModel {

    }
}
