package com.example.wupproject.cardfragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wupproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardFragment extends Fragment {

    @BindView(R.id.card_image)
    ImageView cardImageView;

    private int cardImageNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        ButterKnife.bind(this, view);
        cardImageNumber = getArguments().getInt("cardImageNumber");
        switchCardImage(cardImageNumber);
        return view;
    }

    private void switchCardImage(int cardImageNumber) {
        switch (cardImageNumber) {
            case 1:
                cardImageView.setImageResource(R.drawable.cccard);
                break;
            case 2:
                cardImageView.setImageResource(R.drawable.cccard2);
                break;
            case 3:
                cardImageView.setImageResource(R.drawable.cccard3);
                break;
            default:
                System.out.println("No image found for that cardImage");
        }
    }
}