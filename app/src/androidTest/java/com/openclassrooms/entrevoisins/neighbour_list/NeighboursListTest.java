
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import butterknife.OnClick;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBackUnconditionally;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringEndsWith.endsWith;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT;
    private int POSITION_ITEM = 0;

    private ListNeighbourActivity mActivity;
    private NeighbourApiService mService;
    private List<Neighbour> mNeighbourList;
    private List<Neighbour> mFavNeighbourList;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        //ON SETUP LES ELEMENTS QUI VONT ETRE NECESSAIRES (ACTIVITE LISTNEIGHBOURACTIVITY, INJECTION DE L'API, RECUPERATION DE LA LISTE DE VOISINS...)
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mService = DI.getNeighbourApiService();
        mNeighbourList = mService.getNeighbours();
        ITEMS_COUNT = mNeighbourList.size();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void myNeighboursList_OnclickItem_shouldOpenDetailActivity() {

        // ON RECUPERE LA LISTE VISUELLEMENT AVEC L'XML DU FRAGMENT
        onView(withId(R.id.list_neighbours))
                .perform(actionOnItemAtPosition(POSITION_ITEM, click()));
        // ON SIMULE UN CLIQUE SUR UNE DES CELLULES DE LA LISTE

        // ON RECUPURE VISUELLEMENT LE NOM DU VOISIN DE LA CELLULE QUE L'ON VIENT DE CLIQUE AVEC L'ID DE L'ELEMENT TEXT VIEW QUI DOIT AFFICHER LE NOM DU VOISIN
        onView(withId(R.id.nameTextView))
                .check(matches(isDisplayed()));
        // ON VERIFIE QUE LE BON NOM EST AFFICHE
    }

    @Test
    public void myNeighbour_FavoriteOnClickItem_active() {

        // ON RECUPERE LA LISTE VISUELLEMENT AVEC L'XML DU FRAGMENT
        onView(withId(R.id.list_neighbours))
                .perform(actionOnItemAtPosition(POSITION_ITEM, click()));
        // ON SIMULE UN CLIQUE SUR UNE DES CELLULES

        // ON EST DANS LE DETAIL DU VOISIN DE LA CELLULE SUR LAQUELLE ON A CLIQUE, ON SIMULE UN CLICK SUR LE BOUTON FAVORIS AVEC L'ID DE LELEMENT BUTTON DE LACTIVITE DETAIL
        onView(withId(R.id.favoriteButton))
                .perform(click());

        pressBackUnconditionally();

        onView(withId(R.id.list_neighbours))
                .perform(actionOnItemAtPosition(2, click()));
        // ON SIMULE UN CLIQUE SUR UNE DES CELLULES

        // ON EST DANS LE DETAIL DU VOISIN DE LA CELLULE SUR LAQUELLE ON A CLIQUE, ON SIMULE UN CLICK SUR LE BOUTON FAVORIS AVEC L'ID DE LELEMENT BUTTON DE LACTIVITE DETAIL
        onView(withId(R.id.favoriteButton))
                .perform(click());

        pressBackUnconditionally();
        onView(withText("FAVORITES")).perform(click());

        /*swipeLeft();*/

        /*onView(withId(R.id.list_neighbours2))
                .perform(actionOnItemAtPosition(0, click()));*/

        onView(allOf(withId(R.id.list_neighbours2),isDisplayed())).perform(actionOnItemAtPosition(0, click()));


        onView(withId(R.id.nameTextView))
                .check(matches(withText("Caroline")));

    }

}