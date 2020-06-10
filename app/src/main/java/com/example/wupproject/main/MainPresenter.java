package com.example.wupproject.main;

public class MainPresenter implements MainContract.Presenter{

    MainContract.View mView;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void loadDetails() {

    }

    @Override
    public void onDetach() {
        this.mView = null;
    }
}
