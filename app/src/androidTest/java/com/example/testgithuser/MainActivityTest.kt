package com.example.testgithuser

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityTest {

    @Test
    fun searchUser(){
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.et_search))
            .perform(typeText("rogers-dwiputra"))
//            .perform(closeSoftKeyboard())

        onView(withId(R.id.cl_search))
            .check(matches(isDisplayed()))
            .check(matches(hasDescendant(withText("rogers-dwiputra"))))
    }
}