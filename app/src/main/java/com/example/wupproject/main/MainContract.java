package com.example.wupproject.main;

import com.example.wupproject.BasePresenter;
import com.example.wupproject.BaseView;
import com.example.wupproject.model.Card;

import java.util.List;

public interface MainContract {

    interface Presenter extends BasePresenter {

        void loadData();

    }

    interface View extends BaseView<Presenter> {

        void showData(List<Card> cards);

    }
}
