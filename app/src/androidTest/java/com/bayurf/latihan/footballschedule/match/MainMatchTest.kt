package com.bayurf.latihan.footballschedule.match

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.mvp.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainMatchTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    //    Practice
    @Test
    fun testRecyclerViewBehavior() {

        Thread.sleep(10000)
        onView(withId(R.id.rv_match))
            .check(matches(isDisplayed()))

        Thread.sleep(2000)
        onView(withId(R.id.rv_match))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        Thread.sleep(1000)
        onView(withId(R.id.rv_match))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    //    Submission
    @Test
    fun testScenePerform() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_match))
            .check(matches(isDisplayed()))

        onView(withId(R.id.bottom_navigation))
            .check(matches(isDisplayed()))

        onView(withId(R.id.next_match))
            .perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.rv_match)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        Thread.sleep(3000)
        onView(withId(R.id.add_fav_detail_menu))
            .check(matches(isDisplayed()))


        onView(withId(R.id.add_fav_detail_menu))
            .perform(click())

        onView(withText("Event telah ditambahkan ke Favorit"))
            .check(matches(isDisplayed()))

        Thread.sleep(1000)

        pressBack()

        onView(withId(R.id.favorites))
            .perform(click())

        onView(withId(R.id.rv_match))
            .check(matches(isDisplayed()))

        Thread.sleep(3000)
    }
}