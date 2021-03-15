package com.picpay.desafio.android.presentation

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.MockUIFileReader
import com.picpay.desafio.android.R
import com.picpay.desafio.android.RecyclerViewMatchers
import com.picpay.desafio.android.base.BaseUITest
import com.picpay.desafio.android.presentation.scene.userList.UserListActivity
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import java.net.HttpURLConnection


class UserListActivityTest : BaseUITest() {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun givenGetUserListExpectedSuccessBehaviorIntoView() {
        val file = MockUIFileReader("GetUserListResponseSuccess.json")
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(file.content)

        mockWebServer.enqueue(response)

        launchActivity<UserListActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle))
                .check(matches(isDisplayed()))

            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 0, withText("name1"))
            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 0, withText("name1"))

            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 1, withText("name2"))
            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 1, withText("name2"))

            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 2, withText("name3"))
            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 2, withText("name3"))

            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 3, withText("name4"))
            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 3, withText("name4"))

            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 4, withText("name5"))
            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 4, withText("name5"))
        }
    }

    @Test
    fun givenGetUserListExpectedErrorBehaviorIntoView() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)

        mockWebServer.enqueue(response)

        launchActivity<UserListActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle))
                .check(matches(isDisplayed()))

            onView(withId(R.id.user_list_progress_bar))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))

            onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))
        }
    }

    @Test
    fun givenGetUserListExpectedEmptyBehaviorIntoView() {
        val file = MockUIFileReader("GetUserListResponseEmpty.json")
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(file.content)

        mockWebServer.enqueue(response)

        launchActivity<UserListActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle))
                .check(matches(isDisplayed()))

            onView(withId(R.id.user_list_progress_bar))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))

            onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))
        }
    }
}