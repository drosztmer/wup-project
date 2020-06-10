package com.example.wupproject.main;

import com.example.wupproject.BasePresenter;
import com.example.wupproject.BaseView;

public interface MainContract {

    interface Presenter extends BasePresenter {

        void loadData();

        void loadDetails();

    }

    interface View extends BaseView<Presenter> {

        void showData();

        void showDetails();

    }
}
