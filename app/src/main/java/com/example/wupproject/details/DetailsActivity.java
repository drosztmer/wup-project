package com.example.wupproject.details;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wupproject.R;
import com.example.wupproject.model.Card;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    @BindView(R.id.detail_progressbar)
    DetailsProgressBar detailsProgressBar;

    @BindView(R.id.details_current)
    TextView detailsCurrentView;

    @BindView(R.id.details_available)
    TextView detailsAvailableView;

    @BindView(R.id.currency_reservations)
    TextView currencyReservations;

    @BindView(R.id.reservations)
    TextView reservations;

    @BindView(R.id.currency_carried_over)
    TextView currencyCarriedOver;

    @BindView(R.id.carried_over)
    TextView carriedOver;

    @BindView(R.id.currency_total_spendings)
    TextView currencyTotalSpendings;

    @BindView(R.id.total_spendings)
    TextView totalSpendings;

    @BindView(R.id.your_last_repayment)
    TextView yourLastRepayment;

    @BindView(R.id.currency_account_limit)
    TextView currencyAccountLimit;

    @BindView(R.id.account_limit)
    TextView accountLimit;

    @BindView(R.id.account_number)
    TextView accountNumber;

    @BindView(R.id.card_number)
    TextView cardNumber;

    @BindView(R.id.cardholder_name)
    TextView cardHolderName;

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

            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
            otherSymbols.setDecimalSeparator('.');
            otherSymbols.setGroupingSeparator('\'');
            DecimalFormat df = new DecimalFormat("###,##0.00", otherSymbols);

            detailsCurrentView.setText(df.format(card.getCurrentBalance()));
            detailsAvailableView.setText(df.format(card.getAvailableBalance()));

            setDetailsProgressBar(card, detailsProgressBar);

            currencyReservations.setText(card.getCurrency());
            reservations.setText(df.format(card.getReservations()));

            currencyCarriedOver.setText(card.getCurrency());
            carriedOver.setText(df.format(card.getBalanceCarriedOverFromLastStatement()));

            currencyTotalSpendings.setText(card.getCurrency());
            totalSpendings.setText(df.format(card.getSpendingsSinceLastStatement()));

            yourLastRepayment.setText(formatRepaymentDate(card.getYourLastRepayment()));

            currencyAccountLimit.setText(card.getCurrency());

            accountLimit.setText(df.format(card.getAccountDetails().getAccountLimit()));

            accountNumber.setText(card.getAccountDetails().getAccountNumber());
            cardNumber.setText(formatCardNumber(card.getCardNumber()));

            cardHolderName.setText(card.getCardHolderName());
        }
    }

    private String formatCardNumber(String cardNumber) {
        String formatted = "";
        for (int i = 0; i < cardNumber.length() - 4; i++) {
            if (Character.isDigit(cardNumber.charAt(i))) {
                formatted += "*";
            } else {
                formatted += cardNumber.charAt(i);
            }
        }
        formatted += cardNumber.substring(cardNumber.length() - 4);
        return formatted;
    }

    private String formatRepaymentDate(String yourLastRepayment) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate = new Date();
        try {
            inputDate = dateFormat.parse(yourLastRepayment);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        return df.format("MM.dd.yyyy", inputDate).toString();
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