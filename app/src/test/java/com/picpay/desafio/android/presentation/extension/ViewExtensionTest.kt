package com.picpay.desafio.android.presentation.extension

import android.view.View
import org.junit.Test
import org.mockito.Mockito.*

class ViewExtensionTest {

    private val viewMock = mock(View::class.java)

    @Test
    fun `given show call expected view with visibility visible`() {

        //arrange

        //act
        viewMock.show()

        //assert
        verify(viewMock).visibility = eq(View.VISIBLE)
    }

    @Test
    fun `given hide call expected view with visibility gone`() {

        //arrange

        //act
        viewMock.hide()

        //assert
        verify(viewMock).visibility = eq(View.GONE)
    }

    @Test
    fun `given visibility call with true value expected view with visibility visible`() {

        //arrange

        //act
        viewMock.visible(true)

        //assert
        verify(viewMock).visibility = eq(View.VISIBLE)
    }

    @Test
    fun `given visibility call with false value expected view with visibility gone`() {

        //arrange

        //act
        viewMock.visible(false)

        //assert
        verify(viewMock).visibility = eq(View.GONE)
    }
}