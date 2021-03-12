package com.picpay.desafio.android.presentation.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.RxImmediateSchedulerRule
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUserListUseCase
import com.picpay.desafio.android.presentation.scene.userList.UserListStates
import com.picpay.desafio.android.presentation.scene.userList.UserListViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.IOException

class UserListViewModelTest {

    private lateinit var viewModel: UserListViewModel

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @get:Rule
    var rxRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var usecase: GetUserListUseCase

    @Mock
    private lateinit var observer: Observer<UserListStates>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `given get user list call return user list expected success behavior`() {

        //arrange
        whenever(usecase.getUserList()).thenReturn(Single.just(listOf(User(1, "", "", ""))))


        //act
        viewModel = UserListViewModel(usecase)

        //assert
        viewModel.viewUserListStatesLiveData.observeForever(observer)
        verify(observer).onChanged(UserListStates.SuccessState(listOf(User(1, "", "", ""))))
    }

    @Test
    fun `given get user list call return empty user list expected empty behavior`() {

        //arrange
        whenever(usecase.getUserList()).thenReturn(Single.just(listOf()))

        //act
        viewModel = UserListViewModel(usecase)

        //assert
        viewModel.viewUserListStatesLiveData.observeForever(observer)
        verify(observer).onChanged(UserListStates.EmptyState)
    }

    @Test
    fun `given get user list call return error expected error behavior`() {

        //arrange
        whenever(usecase.getUserList()).thenReturn(Single.error(IOException()))

        //act
        viewModel = UserListViewModel(usecase)

        //assert
        viewModel.viewUserListStatesLiveData.observeForever(observer)
        verify(observer).onChanged(UserListStates.ErrorState)
    }
}