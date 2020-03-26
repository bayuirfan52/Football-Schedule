package com.bayurf.latihan.footballschedule

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.recyclerview.widget.RecyclerView
import com.bayurf.latihan.footballschedule.mvp.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityInstrumentedTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testWithSchema() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_last_match))
            .check(matches(isDisplayed()))

        onView(withId(R.id.bottom_navigation))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_last_match))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        Thread.sleep(3000)
        onView(withId(R.id.add_fav_detail_menu))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withText("Event telah ditambahkan ke Favorit"))
            .check(matches(isDisplayed()))

        Thread.sleep(1000)

        pressBack()

        Thread.sleep(3000)
        onView(withId(R.id.teams))
            .perform(click())

        Thread.sleep(5000)

        onView(withId(R.id.rv_teams))
            .check(matches(isDisplayed()))

        Thread.sleep(1000)

        onView(withId(R.id.rv_teams))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        Thread.sleep(3000)

        pressBack()

        Thread.sleep(5000)
    }
}