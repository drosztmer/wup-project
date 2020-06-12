package com.example.wupproject.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wupproject.R;
import com.example.wupproject.cardfragment.CardFragmentAdapter;
import com.example.wupproject.details.DetailsActivity;
import com.example.wupproject.model.Card;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @BindView(R.id.availableBalance)
    TextView availableBalance;

    @BindView(R.id.currency)
    TextView currency;

    @BindView(R.id.currentBalance)
    TextView currentBalance;

    @BindView(R.id.currency_min_payment)
    TextView currencyMinPayment;

    @BindView(R.id.min_payment)
    TextView minPayment;

    @BindView(R.id.due_date)
    TextView dueDate;

    @BindView(R.id.tabDots)
    TabLayout tabLayout;

    @BindView(R.id.exclamationmark)
    View exclamationMark;

    @BindView(R.id.left_arrow)
    ImageView leftArrow;

    @BindView(R.id.right_arrow)
    ImageView rightArrow;

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
        tabLayout.setupWithViewPager(viewPager, true);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    viewPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    viewPager.setCurrentItem(tab);
                }
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                tab++;
                viewPager.setCurrentItem(tab);
            }
        });

        onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                availableBalance.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                exclamationMark.setVisibility(View.GONE);

                Card newCard = cardFragmentAdapter.getItemAt(position);
                cardFragmentAdapter.notifyDataSetChanged();
                ViewParent parent = currentView.getParent();
                int parentWidth = ((RelativeLayout) parent).getMeasuredWidth();

                fillViewsWithData(newCard, parentWidth);

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

    private void fillViewsWithData(Card newCard, int parentWidth) {
        setMainTitle(newCard.getCardImage());

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator('\'');
        DecimalFormat df = new DecimalFormat("###,##0.00", otherSymbols);
        if(newCard.getAvailableBalance() == 0) {
            availableBalance.setTextColor(getResources().getColor(R.color.zero_balance));
            exclamationMark.setVisibility(View.VISIBLE);

        }
        availableBalance.setText(df.format(newCard.getAvailableBalance()));

        double toRatio = (double) newCard.getAvailableBalance() / (newCard.getAvailableBalance() + newCard.getCurrentBalance());
        int toWidth = (int) (toRatio * parentWidth);
        currentView.getLayoutParams().width = toWidth;
        currentView.requestLayout();

        currency.setText(newCard.getCurrency());

        currentBalance.setText(df.format(newCard.getCurrentBalance()));

        currencyMinPayment.setText(newCard.getCurrency());

        minPayment.setText(df.format(newCard.getMinPayment()));

        dueDate.setText(formatDueDate(newCard.getDueDate()));


    }

    private String formatDueDate(String dueDateStr) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate = new Date();
        try {
            inputDate = dateFormat.parse(dueDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        return df.format("MM.dd.yyyy", inputDate).toString();
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
        double fromRatio = (double) currentCard.getAvailableBalance() / (currentCard.getAvailableBalance() + currentCard.getCurrentBalance());
        fromWidth = (int) (fromRatio * parentWidth);
        double toRatio = (double) newCard.getAvailableBalance() / (newCard.getAvailableBalance() + newCard.getCurrentBalance());
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