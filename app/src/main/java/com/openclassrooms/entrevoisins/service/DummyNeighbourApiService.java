package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 * ON A DEFINIT LA METHODE PERMETTANT DE RECUPERER LA LISTE FAVORIS ICI
 * CETTE METHODE PERMET DE VERIFIER LES VOISINS ETANT FAVORIS ET DE LES AJOUTER A LA LISTE DE FAVORIS
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }
    //public List<Neighbour> getFavNeighbours() { return favNeighbours; }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {

        List<Neighbour> favoriteNeighbours = new ArrayList<>();

        for (Neighbour neighbour : neighbours) {
            if (neighbour.getFavoris()) {
                favoriteNeighbours.add(neighbour);
            }
        }
        return favoriteNeighbours;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }
    //public void deleteFavNeighbour(Neighbour neighbour) {favNeighbours.remove(neighbour); }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public Neighbour getNeighbourByPosition(int position) {
        return neighbours.get(position);
    }

   /* @Override
    public void addNeighbourFavoris(Neighbour neighbour) {
        //neighbours.get(neighbours.indexOf(neighbour)).setFavoris(!neighbour.getFavoris());

        favNeighbours.add(neighbour);
    }*/


    //@Override
   // public long getNeighbourById(Neighbour neighbour) {
     //   return id;
   // }


    //@Override
    //public void removeNeighbourFavoris(Neighbour neighbour) {

   // }

   // @Override
   // public Boolean isFavoris(Neighbour neighbour) {

   // }

    //@Override
   // public List<Neighbour> getFavorisNeighbours() {

   // }

}
