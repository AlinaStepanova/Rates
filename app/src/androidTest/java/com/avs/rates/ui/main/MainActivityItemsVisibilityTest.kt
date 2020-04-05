package com.avs.rates.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.avs.rates.CONSTRAINT_LAYOUT
import com.avs.rates.EMISSION_PERIOD
import com.avs.rates.R
import com.avs.rates.childAtPosition
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
/**
 * Performs UI tests. For Espresso UI testing, it's best practice to turn animations off
 * (Window animation scale, Transition animation scale and Animator duration scale)
 */
class MainActivityItemsVisibilityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Thread.sleep(EMISSION_PERIOD)
    }

    @Test
    fun isRecyclerViewVisibleTest() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRatesTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRatesTitle)).check(matches(withText(R.string.app_name)))
    }

    @Test
    fun recyclerViewClickFirstItemTest() {
        val recyclerView = onView(
            Matchers.allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withClassName(Matchers.`is`(CONSTRAINT_LAYOUT)),
                    1
                )
            )
        )
        val appCompatEditText = onView(
            Matchers.allOf(
                withId(R.id.editText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText.check(matches(withText("1")))
        recyclerView.perform(actionOnItemAtPosition<CurrenciesAdapter.RatesViewHolder>(0, click()))
    }
}