
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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
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
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void myNeighboursList_OnclickItem_shouldOpenDetailActivity() {

        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM, click()));

        onView(withId(R.id.nameTextView))
                .check(matches(isDisplayed()));
    }

    @Test
    public void myNeighbour_FavoriteOnClickItem_active() {

        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM, click()));

        onView(withId(R.id.favoriteButton))
                .perform(click());

        pressBack();
        swipeLeft();
        click();

        onView(withId(R.id.list_neighbours))
                .check(withItemCount(1));


    }

    /*@Test
    public void myNeighbour_DeleteFavoriteOnClickItem_active() {

        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM, click()));

        onView(withId(R.id.favoriteButton))
                .perform(click());
        onView(withId(R.id.favoriteButton))
                .perform(click());

        pressBack();

        onView(withText("Favorites"))
                .perform(click());

        onView(withId(R.id.list_neighbours))
                .check(withItemCount(0));

    }*/

    // DERNIER TEST : VERIFIER QUE LE FAVORI SE SUPPRIME BIEN
    //COMMENTAIRE SUR CHAQUE CHANGEMENT QUE JAI FAIT DANS LES TEST ET DANS LE CODE SOURCE
    // READ ME : FAIRE COMME EXEMPLE

}