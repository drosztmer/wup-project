package com.example.wupproject.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wupproject.R;
import com.example.wupproject.cardfragment.CardFragmentAdapter;
import com.example.wupproject.model.Card;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter mPresenter;

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.card_pager)
    ViewPager viewPager;

    List<Card> cards = new ArrayList<>();
    private CardFragmentAdapter cardFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);
        mPresenter.loadData();

    }

    @Override
    public void showData(List<Card> cards) {
        this.cards = cards;
        cardFragmentAdapter = new CardFragmentAdapter(getSupportFragmentManager(), cards);
        viewPager.setAdapter(cardFragmentAdapter);
    }

    @Override
    public void showDetails() {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        viewPager.setCurrentItem(item, smoothScroll);
    }
}