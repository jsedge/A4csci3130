package com.acme.a3csci3130;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;

/**
 * Created by justin on 15/03/18.
 */

@RunWith(AndroidJUnit4.class)
public class CRUDTest {
    private MainActivity mainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);


    /**
     * Restarts the main activity before each test
     */
    @Before
    public void init(){
        mainActivity = mainActivityRule.getActivity();
    }

    /**
     * Creates a business and makes sure it loaded properly
     */
    @Test
    public void create(){
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("Valid Name"));
        onView(withId(R.id.number)).perform(typeText("123456789"));
        onView(withId(R.id.business)).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(typeText("123 Place Street"));
        onView(withId(R.id.province)).perform(typeText("AB"));
        closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.listView)).check(matches(withChild(withText("Valid Name"))));
        onView(withText("Valid Name")).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
    }

    /**
     * Tries to read the created business
     */
    @Test
    public void read(){
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("Valid Name"));
        onView(withId(R.id.number)).perform(typeText("123456789"));
        onView(withId(R.id.business)).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(typeText("123 Place Street"));
        onView(withId(R.id.province)).perform(typeText("AB"));
        closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());

        onView(withText("Valid Name")).perform(click());
        onView(withId(R.id.name)).check(matches(withText("Valid Name")));
        onView(withId(R.id.number)).check(matches(withText("123456789")));
        onView(withId(R.id.business)).check(matches(withText("Fisher")));
        onView(withId(R.id.address)).check(matches(withText("123 Place Street")));
        onView(withId(R.id.province)).check(matches(withText("AB")));
        onView(withId(R.id.deleteButton)).perform(click());
    }

    /**
     * updates an entry and makes sure the changes were saved
     */
    @Test
    public void update(){
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("Valid Name"));
        onView(withId(R.id.number)).perform(typeText("123456789"));
        onView(withId(R.id.business)).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(typeText("123 Place Street"));
        onView(withId(R.id.province)).perform(typeText("AB"));
        closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());

        onView(withText("Valid Name")).perform(click());
        onView(withId(R.id.name)).perform(typeText("2"));
        closeSoftKeyboard();
        onView(withId(R.id.updateButton)).perform(click());

        onView(withId(R.id.listView)).check(matches(withChild(withText("Valid Name2"))));
    }

    /**
     * Creates then deletes an entry and makes sure it was deleted
     */
    @Test
    public void delete(){
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("Valid Name"));
        onView(withId(R.id.number)).perform(typeText("123456789"));
        onView(withId(R.id.business)).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(typeText("123 Place Street"));
        onView(withId(R.id.province)).perform(typeText("AB"));
        closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());

        onView(withText("Valid Name")).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
        try {
            onView(withText("Valid Name")).check(matches(isDisplayed()));
            assertTrue(false);
        }catch (NoMatchingViewException success){
            //espresso cant check if something doesnt exist, so try to check if it does and catch
            //then have that be a success
            assertTrue(true);
        }
    }
}
