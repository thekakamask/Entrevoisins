package com.openclassrooms.entrevoisins.service;

import android.telecom.Call;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailsNeighbourActivity;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    //NOUVELLE INSTANCE POUR L'API (INJECTION)
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {

        //JE RECUPERE LA LISTE PAR L'OBJET SERVICE (AVEC L'API)
        List<Neighbour> neighbours = service.getNeighbours();

        //JE RECUPERE LA LISTE D'UNE AUTRE MANIERE, SANS PASSER PAR L'API,GRACE A LA CLASSE GENERATOR DUMMY
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;

        //JE COMPARE LES DEUX LISTES POUR VERIFIER QUELLE SONT IDENTIQUES
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {

        // JE RECUPERE UN OBJET DE TYPE NEIGHBOUR QUI CORRESPOND AU PREMIER DE LA LISTE NEIGHBOURS
        // EN LE RECUPERANT AVEC L'API
        Neighbour neighbourToDelete = service.getNeighbours().get(0);

        // J UTILISE LA METHODE PERMETTANT DE SUPPRIMER UN VOISIN AVEC L'API,
        // JE SUPPRIME DONC LE VOISIN NOMME NEIGHBOURTODELETE
        service.deleteNeighbour(neighbourToDelete);

        // JE VERIFIE QUE LA LISTE NE CONTIENNE PAS LE VOISIN QUE L'ON VIENT DE SUPPRIMER
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getNeighbourWithSuccess() {

        // JE RECUPERE UN OBJET DE TYUPE NEIGHBOUR QUI CORRESPOND AU DEUXIEME VOISIN DE LA LISTE
        Neighbour neighbourToAdd = service.getNeighbours().get(1);

        //J'AJOUTE LE VOISIN QUE JE VIENS DE RECUPERER A LA LISTE
        service.createNeighbour(neighbourToAdd);

        //JE VERIFIE QUE LA LISTE CONTIENNE BIEN LE VOISIN
        assertTrue(service.getNeighbours().contains(neighbourToAdd));
    }

    // VERIFIER QUE LES FAVORIS S'AJOUTENT BIEN A LA LISTE QUI LEURS CORRESPONDENT
    @Test
    public void addFavNeighbour() {

        //JE RECUPERE UN VOISIN QUI EST LE TROISIEME DE LA LISTE DE VOISIN
        Neighbour neighbourFavToAdd = service.getNeighbours().get(2);

        //JE SUPPRIME CE VOISIN DE LA LISTE AVEC L'API
        service.deleteNeighbour(neighbourFavToAdd);

        // JE REND CE VOISIN FAVORI
        neighbourFavToAdd.setFavoris(true);

        // JE RAJOUTE CE VOISIN FAVORIS A LA LISTE DE VOISINS
        service.createNeighbour(neighbourFavToAdd);

        // JE RECUPERE LA LISTE DE FAVORIS DE VOISINS
        service.getFavoriteNeighbours();

        // JE VERIFIE QUE LA LISTE DE FAVORIS CONTIENT NOTRE VOISINS
        assertTrue(service.getFavoriteNeighbours().contains(neighbourFavToAdd));



        /*if (neighbourFavToAdd.getFavoris()) {
            service.deleteNeighbour(neighbourFavToAdd);
            neighbour.setFavoris(false);
            service.createNeighbour(neighbourFavToAdd);
        } else  {*/

        //tjr pas sur QUE LE neighbourfavtodel sajoute bien a la liste favoris vu quil nest pas en parametre
        //details.clickFavButton();
    }

    // VERIFIER QUE LES FAVORIS SE SUPPRIMENT BIEN DE LEURS LISTE CORRESPONDANTE
    @Test
    public void deleteFavNeighbour() {

        // JE RECUPERE UN VOISIN QUI EQUIVAUT AU 3 EME DE LA LISTE DE VOISINS AVEC L'API
        Neighbour neighbourFavToDel = service.getNeighbours().get(2);

        //JE SUPPRIME CE VOISIN DE LA LISTE
        service.deleteNeighbour(neighbourFavToDel);

        // JE REND CE VOISIN FAVORI
        neighbourFavToDel.setFavoris(true);

        //J'AJOUTE CE VOISIN A LA LISTE
        service.createNeighbour(neighbourFavToDel);

        // JE RECUPERE LA LISTE DE FAVORIS
        service.getFavoriteNeighbours();

        //JE SUPPRIME CE VOISIN
        service.deleteNeighbour(neighbourFavToDel);

        //JE REND CE VOISIN NN FAVORI
        neighbourFavToDel.setFavoris(false);

        //JE RECUPERE LA LISTE FAVORI
        service.getFavoriteNeighbours();

        //JE VERIFIE QUE LA LISTE FAVORI NE CONTIENNE PAS LE VOISIN
        assertFalse(service.getFavoriteNeighbours().contains(neighbourFavToDel));

        //tjr pas sur QUE LE neighbourfavtodel sajoute bien a la liste favoris vu quil nest pas en parametre
        //details.clickFavButton();

    }

    //VERIFIER QUE LA LISTE DE BASE DES FAVORIS EXISTE ET QUE ELLE BIEN VIDE

    @Test
    public void getFavoriteNeighboursEmpty() {

        // JE RECUPERE LA LISTE DE FAVORIS
        List<Neighbour> favoriteNeighbours1 = service.getFavoriteNeighbours();

        // JE GENERE UNE NOUVELLE LISTE
        List<Neighbour> favoriteNeighbours2 = new ArrayList<>();

        // JE VERIFIE QUE LES DEUX LISTES SONT EQUIVALENT A SAVOIR QUELLES NE CONTIENNENT RIEN
        assertThat(favoriteNeighbours1, IsIterableContainingInAnyOrder.containsInAnyOrder(favoriteNeighbours2.toArray()));


    }
}