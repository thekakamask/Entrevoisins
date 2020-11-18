package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.FavoriteNeighbourFragment;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     * ON RAJOUTE LA METHODE PERMETTANT DE RECUPERER LA LISTE FAVORIS A L'INTERFACE QUE LON DEFINIT DANS LA CLASSE DUMMYNEIGHBOURAPISERVICE
     */
    List<Neighbour> getNeighbours();
    //List<Neighbour> getFavNeighbours();

    List<Neighbour> getFavoriteNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);
    //void deleteFavNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    // AJOUTER UNE NOUVELLE METHODE QUI RETOURNE UN NEIGHBOUR EN FONCTION DE SON ID DANS LE MODEL: GetNeighbourById

    Neighbour getNeighbourByPosition (int position);

    //void removeNeighbourFavoris(Neighbour neighbour);

    //Void addNeighbourFavoris(Neighbour neighbour);

    //void deleteFavNeighbour(Neighbour neighbour);

    //List<Neighbour> getFavorisNeighbours();
}
