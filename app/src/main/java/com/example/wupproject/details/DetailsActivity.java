package com.example.wupproject.details;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wupproject.R;
import com.example.wupproject.model.Card;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    @BindView(R.id.currentBalance)
    TextView currentBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        showDetails();
    }

    @Override
    public void showDetails() {
        Card card = getIntent().getParcelableExtra("card");

        if (card != null) {
            currentBalance.setText(card.getStatus());
            Card.AccountDetails details = card.getAccountDetails();
            System.out.println(details.getAccountNumber());
        }
    }


    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {

    }
}