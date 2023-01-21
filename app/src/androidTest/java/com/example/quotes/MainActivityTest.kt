package com.example.quotes

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.quotes.views.MainActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test


class MainActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun testNextButton(){
        onView(withId(R.id.nextBtn)).perform(click())
        onView(withId(R.id.nextBtn)).perform(click())
        onView(withId(R.id.nextBtn)).perform(click())
        onView(withId(R.id.nextBtn)).perform(click())
        onView(withId(R.id.quoteText)).check(matches(withText("Fate is in your hands and no one elses")))
    }

    @Test
    fun testShareButton(){
        Intents.init()
        val expected = allOf(hasAction(Intent.ACTION_SEND))
        onView(withId(R.id.shareBtn)).perform(click())
        intended(expected)
        Intents.release()
    }


}