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
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getNeighbourWithSuccess() {
        Neighbour neighbourToAdd = service.getNeighbours().get(1);
        service.createNeighbour(neighbourToAdd);
        assertTrue(service.getNeighbours().contains(neighbourToAdd));
    }

    // VERIFIER QUE LES FAVORIS S'AJOUTENT BIEN A LA LISTE QUI LEURS CORRESPONDENT
    @Test
    public void addFavNeighbour() {


        Neighbour neighbourFavToAdd = service.getNeighbours().get(2);
        service.deleteNeighbour(neighbourFavToAdd);
        neighbourFavToAdd.setFavoris(true);
        service.createNeighbour(neighbourFavToAdd);
        service.getFavoriteNeighbours();
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

        Neighbour neighbourFavToDel = service.getNeighbours().get(2);
        service.deleteNeighbour(neighbourFavToDel);
        neighbourFavToDel.setFavoris(true);
        service.createNeighbour(neighbourFavToDel);
        service.getFavoriteNeighbours();
        service.deleteNeighbour(neighbourFavToDel);
        neighbourFavToDel.setFavoris(false);
        service.getFavoriteNeighbours();

        assertFalse(service.getFavoriteNeighbours().contains(neighbourFavToDel));

        //tjr pas sur QUE LE neighbourfavtodel sajoute bien a la liste favoris vu quil nest pas en parametre
        //details.clickFavButton();

    }

    //VERIFIER QUE LA LISTE DE BASE DES FAVORIS EXISTE ET QUE ELLE BIEN VIDE

    @Test
    public void getFavoriteNeighboursEmpty() {

        List<Neighbour> favoriteNeighbours1 = service.getFavoriteNeighbours();
        List<Neighbour> favoriteNeighbours2 = new ArrayList<>();

        assertThat(favoriteNeighbours1, IsIterableContainingInAnyOrder.containsInAnyOrder(favoriteNeighbours2.toArray()));


    }
}