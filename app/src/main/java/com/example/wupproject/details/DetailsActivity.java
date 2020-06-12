package com.example.wupproject.details;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wupproject.R;
import com.example.wupproject.model.Card;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    @BindView(R.id.detail_progressbar)
    DetailsProgressBar detailsProgressBar;

    @BindView(R.id.details_current)
    TextView detailsCurrentView;

    @BindView(R.id.details_available)
    TextView detailsAvailableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        showDetails();
        detailsProgressBar = findViewById(R.id.detail_progressbar);
        detailsProgressBar.setMax(100);
    }

    @Override
    public void showDetails() {
        Card card = getIntent().getParcelableExtra("card");

        if (card != null) {
            detailsCurrentView.setText(String.valueOf(card.getCurrentBalance()));
            detailsAvailableView.setText(String.valueOf(card.getAvailableBalance()));
            setDetailsProgressBar(card, detailsProgressBar);

            Card.AccountDetails details = card.getAccountDetails();
            System.out.println(details.getAccountNumber());
        }
    }

    private void setDetailsProgressBar(Card card, DetailsProgressBar detailsProgressBar) {
        detailsProgressBar.setMax(100);
        double currentBalance = card.getCurrentBalance();
        double availableBalance = card.getAvailableBalance();
        double ratio = 10.0 / 9;

        double progressCurrent = (currentBalance / (currentBalance + availableBalance)) * 100 / ratio;
        double secondaryProgress = progressCurrent + 10;
        detailsProgressBar.setProgress((int)progressCurrent);
        detailsProgressBar.setSecondaryProgress((int)secondaryProgress);
    }


    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {

    }
}