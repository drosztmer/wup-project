package com.example.wupproject.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wupproject.R;
import com.example.wupproject.cardfragment.CardFragmentAdapter;
import com.example.wupproject.details.DetailsActivity;
import com.example.wupproject.model.Card;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter mPresenter;

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.card_pager)
    ViewPager viewPager;

    @BindView(R.id.current)
    View currentView;

    @BindView(R.id.btnDetails)
    Button btnDetails;

    private CardFragmentAdapter cardFragmentAdapter;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private Card currentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);
        cardFragmentAdapter = new CardFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(cardFragmentAdapter);
        onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Card newCard = cardFragmentAdapter.getItemAt(position);
                textView.setText(newCard.getCardHolderName());
                ViewParent parent = currentView.getParent();
                int parentWidth = ((RelativeLayout) parent).getMeasuredWidth();
                if (currentCard != null) {
                    animateView(newCard, parentWidth);
                }
                currentCard = newCard;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        viewPager.addOnPageChangeListener(onPageChangeListener);
        mPresenter.loadData();

        btnDetails.setOnClickListener(v -> details());
    }

    private void animateView(Card newCard, int parentWidth) {
        int fromWidth;
        double fromRatio = (double) currentCard.getCurrentBalance() / (currentCard.getAvailableBalance() + currentCard.getCurrentBalance());
        fromWidth = (int) (fromRatio * parentWidth);
        double toRatio = (double) newCard.getCurrentBalance() / (newCard.getAvailableBalance() + newCard.getCurrentBalance());
        int toWidth = (int) (toRatio * parentWidth);

        ValueAnimator anim = ValueAnimator.ofInt(fromWidth, toWidth);

        anim.setDuration(250);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentView.getLayoutParams().width = (int) animation.getAnimatedValue();
                currentView.requestLayout();
            }
        });
        anim.start();
    }

    @Override
    public void showData(List<Card> cards) {
        cardFragmentAdapter.addCards(cards);
        onPageChangeListener.onPageSelected(viewPager.getCurrentItem());
    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void details() {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("card", currentCard);
        startActivity(intent);
    }
}