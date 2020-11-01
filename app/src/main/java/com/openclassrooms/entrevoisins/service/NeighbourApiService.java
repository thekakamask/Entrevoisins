package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();
    //List<Neighbour> getFavNeighbours();

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

    void changeNeighbourFavoris(Neighbour neighbour);

    void deleteFavNeighbour(Neighbour neighbour);

    //List<Neighbour> getFavorisNeighbours();
}
