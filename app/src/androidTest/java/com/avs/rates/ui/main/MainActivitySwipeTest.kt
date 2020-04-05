package com.avs.rates.ui.main


import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.avs.rates.CONSTRAINT_LAYOUT
import com.avs.rates.EMISSION_PERIOD
import com.avs.rates.R
import com.avs.rates.childAtPosition
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
/**
 * Performs test on swiping recycler view items
 */
class MainActivitySwipeTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Thread.sleep(EMISSION_PERIOD)
    }

    @Test
    fun mainActivitySwipeExtendedTest() {
        val textView = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("EUR"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("EUR")))

        val editText = onView(
            allOf(
                withId(R.id.editText), withText("1"),
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
        editText.check(matches(withText("1")))

        val recyclerView = onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withClassName(`is`(CONSTRAINT_LAYOUT)),
                    1
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val textView2 = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("EUR"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("EUR")))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withClassName(`is`(CONSTRAINT_LAYOUT)),
                    1
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        val recyclerView3 = onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withClassName(`is`(CONSTRAINT_LAYOUT)),
                    1
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

        val recyclerView4 = onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withClassName(`is`(CONSTRAINT_LAYOUT)),
                    1
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        val recyclerView5 = onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withClassName(`is`(CONSTRAINT_LAYOUT)),
                    1
                )
            )
        )
        recyclerView5.perform(actionOnItemAtPosition<ViewHolder>(4, click()))

        val recyclerView6 = onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withClassName(`is`(CONSTRAINT_LAYOUT)),
                    1
                )
            )
        )
        recyclerView6.perform(actionOnItemAtPosition<ViewHolder>(5, click()))

        val textView3 = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("CHF"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("CHF")))

        val textView5 = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("CNY"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        6
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("CNY")))

        val recyclerView7 = onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withClassName(`is`(CONSTRAINT_LAYOUT)),
                    1
                )
            )
        )
        recyclerView7.perform(actionOnItemAtPosition<ViewHolder>(31, click()))

        val textView6 = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("ZAR"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("ZAR")))
    }
}
