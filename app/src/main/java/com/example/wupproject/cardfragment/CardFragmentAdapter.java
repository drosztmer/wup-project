package com.example.wupproject.cardfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wupproject.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardFragmentAdapter extends FragmentPagerAdapter {

    private List<Card> cards = new ArrayList<>();

    public CardFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addCards(List<Card> cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        CardFragment cardFragment = new CardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cardImageNumber", Integer.parseInt(cards.get(position).getCardImage()));
        cardFragment.setArguments(bundle);
        return cardFragment;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    public Card getItemAt(int position) {
        return cards.get(position);
    }
}
