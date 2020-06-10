package com.example.wupproject.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wupproject.R;
import com.example.wupproject.apiservice.BaseApiService;
import com.example.wupproject.apiservice.UtilsApi;
import com.example.wupproject.model.Card;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter mPresenter;

    @BindView(R.id.textView)
    TextView textView;


    List<Card> cards = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);

    }

    @Override
    public void showData() {

    }

    @Override
    public void showDetails() {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}