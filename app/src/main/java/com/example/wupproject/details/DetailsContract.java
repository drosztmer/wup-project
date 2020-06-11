package com.example.wupproject.details;

import com.example.wupproject.BasePresenter;
import com.example.wupproject.BaseView;
import com.example.wupproject.main.MainContract;
import com.example.wupproject.model.Card;

import java.util.List;

public interface DetailsContract {

    interface Presenter extends BasePresenter {

        void loadData();

    }

    interface View extends BaseView<DetailsContract.Presenter> {

        void showDetails();

    }
}