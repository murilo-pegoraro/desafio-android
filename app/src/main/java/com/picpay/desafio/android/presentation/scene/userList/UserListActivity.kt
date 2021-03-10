package com.picpay.desafio.android.presentation.scene.userList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityUserListBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presentation.extension.toToast
import com.picpay.desafio.android.presentation.extension.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListActivity : AppCompatActivity() {

    private val viewModel: UserListViewModel by viewModel()
    lateinit var binding: ActivityUserListBinding
    private lateinit var adapter: UserListAdapter

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)
        binding.lifecycleOwner = this
        initViews()
        initViewModel()
    }

    private fun initViews() {
        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initViewModel() {
        viewModel.viewUserListStatesLiveData.observe(this, Observer {
            when (it) {
                is UserListStates.LoadingState -> showLoading(it.visible)
                is UserListStates.SuccessState -> showData(it.data)
                UserListStates.EmptyState -> showEmptyData()
                UserListStates.ErrorState -> showError()
            }
        })
    }

    private fun showData(users: List<User>) {
        showLoading(false)
        showList(true)
        adapter.setData(users)
    }

    private fun showEmptyData() {
        showLoading(false)
        showList(false)

        getString(R.string.empty_list).toToast(this)
    }

    private fun showError() {
        showLoading(false)
        showList(false)

        getString(R.string.error).toToast(this)
    }

    private fun showLoading(visible: Boolean) {
        binding.userListProgressBar.visible(visible)
    }

    private fun showList(visible: Boolean) {
        binding.recyclerView.visible(visible)
    }
}