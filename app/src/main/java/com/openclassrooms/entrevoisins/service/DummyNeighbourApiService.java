package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favNeighbours = DummyNeighbourGenerator.generateFavNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }
    //public List<Neighbour> getFavNeighbours() { return favNeighbours; }

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

    @Override
    public void changeNeighbourFavoris(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setFavoris(!neighbour.getFavoris());
    }

    @Override
    public void deleteFavNeighbour(Neighbour neighbour) {

    }


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
