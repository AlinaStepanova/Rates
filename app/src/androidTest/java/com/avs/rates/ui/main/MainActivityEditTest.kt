package com.avs.rates.ui.main


import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
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
 * Performs test on editing recycler view items
 */
class MainActivityEditTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Thread.sleep(EMISSION_PERIOD)
    }

    @Test
    fun mainActivityEdit2() {
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

        val textView2 = onView(
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
        textView2.check(matches(withText("AUD")))

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

        val appCompatEditText = onView(
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
        appCompatEditText.perform(click())

        val appCompatEditText2 = onView(
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
        appCompatEditText2.perform(replaceText("10"))

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.editText), withText("10"),
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
        appCompatEditText3.perform(closeSoftKeyboard())

        val editText2 = onView(
            allOf(
                withId(R.id.editText), withText("10"),
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
        editText2.check(matches(withText("10")))

        val textView4 = onView(
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
        textView4.check(matches(withText("EUR")))

        val textView5 = onView(
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
        textView5.check(matches(withText("EUR")))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.editText), withText("10"),
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
        appCompatEditText4.perform(replaceText(""))

        val appCompatEditText5 = onView(
            allOf(
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
        appCompatEditText5.perform(closeSoftKeyboard())
    }
}
