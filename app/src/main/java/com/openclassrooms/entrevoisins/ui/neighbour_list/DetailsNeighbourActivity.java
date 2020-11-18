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

/*ICI NOUS AJOUTONS DONC LA FONCTIONNALITE DETAIL
* L'ACTIVITE DETAILNEIGHBOUR EST COMPOSE DE SA CLASSE ET DE SON FICHIER XML JOINT (XML = COMPOSANT GRAPHIQUE
* ON CREER DONC VISUELLEMENT AVEC L'XML L'ACTIVITE
* AVEC LA METHODE BINDVIEW ON LIE DONC LES ELEMENTS DU FICHIER XML AVEC NOTRE CLASSE JAVA DETAILSNEIGHBOUR
* CHAQUE ELEMENT EST LIE GRACE A SON ID AINSI QUE LES IMAGES FIXES*/

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



    // LE ON CREATE NOUS PERMET DE FIXER NOTRE FICHIER XML
    //ON LIE LE FICHIER XML EN LUI MEME , ON ACTIVE LE BIND DES ELEMENTS DE L'XML
    // ON PROCEDE A L'INJECTION DE l'API
    // LA METHODE SUIVANTE PERMET DE RECUPERER L'INTENT QUI VIENT DE L'ACTIVITE PRINCIPAL
    // ON RECUPERE NOTRE VOISIN PAR SA POSITION
    // LA METHODE SUIVANTE NOUS PERMET D'IMPLEMENTER LES DIFFERENTES CARACTERISTIQUES DU VOISINS
    // LA SUIVANTE PERMET DE CONFIGURER L'IMAGE DU BOUTON FAVORI EN FONCTION DE SI LE VOISIN EST FAVORI OU NON
    // PUIS LA DERNIERE PERMET DE CLIQUER SUR LE BOUTON POUR CHANGER LE STATUT DE FAVORIS DU VOISIN
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

    }

    private void getNeighbour() {
        Intent intent = getIntent();
        int position = intent.getIntExtra("neighbourPosition", 0);
        mNeighbour = mApiService.getNeighbourByPosition(position);
    }


    private void configureInfosCard() {
        nameTextView.setText(mNeighbour.getName());
        headerNameTextView.setText(mNeighbour.getName());
        addressTextView.setText(mNeighbour.getAddress());
        phoneNumberTextView.setText(mNeighbour.getPhoneNumber());
        internetSiteTextView.setText(mNeighbour.getAvatarUrl());
        aboutMeTextView.setText(mNeighbour.getAboutMe());
        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(avatarUrlImageView);
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
    }


}