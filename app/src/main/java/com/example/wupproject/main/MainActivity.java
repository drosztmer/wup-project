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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter mPresenter;

    @BindView(R.id.card_type)
    TextView titleView;

    @BindView(R.id.card_pager)
    ViewPager viewPager;

    @BindView(R.id.current)
    View currentView;

    @BindView(R.id.btnDetails)
    Button btnDetails;

    @BindView(R.id.currentBalance)
    TextView currentBalance;

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
                cardFragmentAdapter.notifyDataSetChanged();
                ViewParent parent = currentView.getParent();
                int parentWidth = ((RelativeLayout) parent).getMeasuredWidth();

                FillViewsWithData(newCard, parentWidth);

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

    private void FillViewsWithData(Card newCard, int parentWidth) {
        setMainTitle(newCard.getCardImage());

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator('\'');
        DecimalFormat df = new DecimalFormat("###,###.00", otherSymbols);
        currentBalance.setText(df.format(newCard.getCurrentBalance()));

        double toRatio = (double) newCard.getCurrentBalance() / (newCard.getAvailableBalance() + newCard.getCurrentBalance());
        int toWidth = (int) (toRatio * parentWidth);
        currentView.getLayoutParams().width = toWidth;
        currentView.requestLayout();
    }

    private void setMainTitle(String cardImage) {
        switch (cardImage) {
            case "1":
                titleView.setText(R.string.title_premium);
                break;
            case "2":
                titleView.setText(R.string.title_rewards);
                break;
            case "3":
                titleView.setText(R.string.title_journey);
                break;
            default:
                titleView.setText(R.string.title_default);
        }
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