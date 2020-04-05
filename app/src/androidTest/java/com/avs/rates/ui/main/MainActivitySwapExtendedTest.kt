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
class MainActivitySwapExtendedTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Thread.sleep(EMISSION_PERIOD)
    }

    @Test
    fun mainActivitySwapItemsTest() {
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

        val textView2 = onView(
            allOf(
                withId(R.id.tvCurrencyFullName), withText("Euro"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Euro")))

        val textView3 = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("AUD"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("AUD")))

        val textView4 = onView(
            allOf(
                withId(R.id.tvCurrencyFullName), withText("Australian Dollar"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        1
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Australian Dollar")))

        val textView5 = onView(
            allOf(
                withId(R.id.tvCurrencyFullName), withText("Australian Dollar"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        1
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Australian Dollar")))

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

        val textView6 = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("AUD"),
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
        textView6.check(matches(withText("AUD")))

        val textView7 = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("EUR"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView7.check(matches(withText("EUR")))

        val textView8 = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("EUR"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView8.check(matches(withText("EUR")))

        val recyclerView3 = onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withClassName(`is`(CONSTRAINT_LAYOUT)),
                    1
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(30, click()))

        val textView9 = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("USD"),
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
        textView9.check(matches(withText("USD")))

        val textView10 = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("AUD"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView10.check(matches(withText("AUD")))

        val textView11 = onView(
            allOf(
                withId(R.id.tvCurrencyShortName), withText("EUR"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        2
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView11.check(matches(withText("EUR")))
    }
}
