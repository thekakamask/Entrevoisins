package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsNeighbourActivity extends AppCompatActivity {

    // BIND DES ELEMENTS DE L'XML
    @BindView(R.id.scrollView2)
    public ScrollView mScrollView;
    @BindView(R.id.imageCardView)
    public CardView imageCardView;
    @BindView(R.id.avatarUrlImageView)
    public ImageView avatarUrlImageView;
    @BindView(R.id.nameTextView)
    public TextView nameTextView;
    @BindView(R.id.infosCardTitle)
    public CardView infosCardTitle;
    @BindView(R.id.headerNameTextView)
    public TextView headerNameTextView;
    @BindView(R.id.addressTextView)
    public TextView addressTextView;
    @BindView(R.id.phoneNumberTextView)
    public TextView phoneNumberTextView;
    @BindView(R.id.internetSiteTextView)
    public TextView internetSiteTextView;
    @BindView(R.id.infosCardText)
    public CardView infosCardText;
    @BindView(R.id.headerAboutMeTextView)
    public TextView headerAboutMeTextView;
    @BindView(R.id.aboutMeTextView)
    public TextView aboutMeTextView;
    @BindView(R.id.favoriteButton)
    public android.support.design.widget.FloatingActionButton mFavoriteButton;

    @BindDrawable(R.drawable.baseline_location_on_24)
    public Drawable mBaselineLocation;
    @BindDrawable(R.drawable.baseline_call_24)
    public Drawable mBaselineCall;
    @BindDrawable(R.drawable.baseline_language_24)
    public Drawable mBaselineLang;
    @BindDrawable(R.drawable.baseline_star_rate_24)
    public Drawable mBaselineStar;
    @BindDrawable(R.drawable.baseline_star_border_black_24dp)
    public Drawable mBaselineStarBorder;


    private NeighbourApiService mApiService;
    private String mNeighbourImage;
    private Neighbour mNeighbour;

    // AJOUTER LE BOUTON DE
    //FAVORI SUR L'ACTIVITE ID


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getNeighbourApiService();
        getNeighbour();
        configureInfosCard();
        configureFavButton();
        
        //clickFavButton();
        /*mFavoriteButton.setOnClickListener(v -> {
            mApiService.changeNeighbourFavoris(mNeighbour);
            mNeighbour.setFavoris(!mNeighbour.getFavoris());
            configureFavButton();
        });*/

        // A FAIRE RECUPERATION DE L'ID DU VOISIN SELECTIONNE A PARTIR DE L'INTENT
        // A FAIRE RECUPERER LES AUTRES INFORMATIONS DU VOISIN A PARTIR DE SON ID
        // AFFICHER LES INFOS DU VOISINS SUR LES COMPOSANTS GRAPHIQUES DE L'ACTIVITE
    }

    private void getNeighbour() {
        Intent intent = getIntent();
        int position = intent.getIntExtra("neighbourPosition", 0);
        mNeighbour = mApiService.getNeighbourByPosition(position);
    }

    //private void configureToolbar() {
    //  Glide.with(this).load(mNeighbour.getAvatarUrl()).into(mNeighbourAvatar);
    // mToolbarTextView.setText(mNeighbour.getName());
    //mToolbarButton.setOnClickListener(v -> finish());

    //}

    private void configureInfosCard() {
        nameTextView.setText(mNeighbour.getName());
        headerNameTextView.setText(mNeighbour.getName());
        addressTextView.setText(mNeighbour.getAddress());
        phoneNumberTextView.setText(mNeighbour.getPhoneNumber());
        internetSiteTextView.setText(mNeighbour.getAvatarUrl());
        aboutMeTextView.setText(mNeighbour.getAboutMe());
        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(avatarUrlImageView);
        //Glide.with(this).load(mNeighbour.getAvatarUrl()).into();
    }

    private void configureFavButton() {
        if (mNeighbour.getFavoris()) {
            mFavoriteButton.setImageResource(R.drawable.baseline_star_rate_24);
        } else {
            mFavoriteButton.setImageResource(R.drawable.baseline_star_border_black_24dp);
        }
    }

    @OnClick (R.id.favoriteButton)
    public void clickFavButton() {

        if (mNeighbour.getFavoris()) {
            mApiService.deleteNeighbour(mNeighbour);
            mNeighbour.setFavoris(false);
            mApiService.createNeighbour(mNeighbour);
        } else  {
            mApiService.deleteNeighbour(mNeighbour);
            mNeighbour.setFavoris(true);
            mApiService.createNeighbour(mNeighbour);
        }
        configureFavButton();
        //Toast.makeText(getApplicationContext(), "clique sur el bouton favoris", Toast.LENGTH_SHORT).show();
    }


}