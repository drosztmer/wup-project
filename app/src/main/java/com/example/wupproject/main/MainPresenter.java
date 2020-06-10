package com.example.wupproject.main;

import com.example.wupproject.apiservice.BaseApiService;
import com.example.wupproject.apiservice.UtilsApi;
import com.example.wupproject.model.Card;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter{

    MainContract.View mView;
    BaseApiService mApiService;


    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        mApiService = UtilsApi.getAPIService();
        this.mView.setPresenter(this);
    }

    @Override
    public void loadData() {
        mApiService.getCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Card>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Card> cards) {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void loadDetails() {

    }

    @Override
    public void onDetach() {
        this.mView = null;
    }
}
