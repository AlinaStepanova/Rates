package com.avs.rates.ui.main


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.avs.rates.R
import com.avs.rates.childAtPosition
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
/**
 * Checks items visibility in a case when there is NO INTERNET CONNECTION
 * All tests will pass only when the internet connection was disabled before the test starts to run
 */
class MainActivityNoNetworkTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityNoNetworkTest() {
        val imageView = onView(
            allOf(
                withId(R.id.ivCloud), withContentDescription("Network error image"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.ivMessage), withText("Please, check network connection"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Please, check network connection")))

        val textView2 = onView(
            allOf(
                withId(R.id.tvRatesTitle), withText("Rates"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Rates")))

        val textView3 = onView(
            allOf(
                withId(R.id.tvRatesTitle), withText("Rates"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Rates")))
    }
}
