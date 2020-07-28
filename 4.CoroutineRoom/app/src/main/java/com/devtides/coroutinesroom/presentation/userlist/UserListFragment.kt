package com.devtides.coroutinesroom.presentation.userlist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devtides.coroutinesroom.R
import com.devtides.coroutinesroom.data.model.User
import com.devtides.coroutinesroom.data.repository.UserDatabase
import com.devtides.coroutinesroom.data.repository.UserRepository
import com.devtides.coroutinesroom.domain.usecases.DeleteUserUseCase
import com.devtides.coroutinesroom.domain.usecases.GetUserUseCase
import com.devtides.coroutinesroom.domain.usecases.InsertUserUserCase
import com.devtides.coroutinesroom.domain.usecases.UpdateUserUseCase
import kotlinx.android.synthetic.main.user_list_fragment.*
import java.util.*

class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private lateinit var viewModel: UserListViewModel
    private lateinit var userRepository: UserRepository
    private lateinit var userDatabase: UserDatabase
    private lateinit var factory: ViewModelProvider.Factory

    private val TAG by lazy {
        javaClass.simpleName
    }

    private val adapter by lazy {
        UserListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btRandomAddUser?.setOnClickListener {
            randomAddUser()
        }

        btRandomAddListUser?.setOnClickListener {
            randomAddListUser()
        }

        userDatabase = UserDatabase(requireActivity())
        userRepository = UserRepository(userDatabase)

        val getUserUseCase = GetUserUseCase(userRepository)
        val insertUserUserCase = InsertUserUserCase(userRepository)
        val deleteUserUseCase = DeleteUserUseCase(userRepository)
        val updateUserUseCase = UpdateUserUseCase(userRepository)

        factory = UserListViewModel.Factory(
            requireActivity().application,
            getUserUseCase,
            insertUserUserCase,
            deleteUserUseCase,
            updateUserUseCase
        )

        viewModel = ViewModelProviders.of(this, factory).get(UserListViewModel::class.java)

        initViewModel()
        initWidget()

    }

    private fun randomAddListUser() {
        val userList = mutableListOf<User>()
        userList.add(ramdomUser())
        userList.add(ramdomUser())
        userList.add(ramdomUser())
        viewModel.addUser(userList)
    }

    private fun randomAddUser() {
        val user = viewModel.ramdomUser()
        viewModel.addUser(user)
    }

    private fun ramdomUser(): User {
        val time = Date().time
        val user = User(
            username = "user$time",
            info = "Info$time}",
            passwordHash = "111111".hashCode()
        )
        return user
    }

    private fun initWidget() {

        userListRecView.apply {
            adapter = this@UserListFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        adapter.setListener { user ->
            val action = arrayOf("Update User", "Delete User", "Clear User")
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Choose Action?")
            builder.setNegativeButton(android.R.string.cancel) { dialog, which ->
            }
            builder.setItems(action) { dialog, which ->
                when (which) {
                    0 -> {
                        viewModel.updateUser(user)
                    }
                    1 -> {
                        viewModel.deleteUser(user.id)
                    }
                    2->{
                        viewModel.clearUser()
                    }
                }
            }
            val dialog = builder.create()
            dialog.show()
        }

    }

    private fun initViewModel() {

        viewModel.getAllUserFlow()?.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
//            userListRecView.smoothScrollToPosition(0)
            Log.d(TAG, "getAllUserFlow = ${it.toString()} | size = ${it.size}")
        })

        viewModel.getAllUserCoroutine()?.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "getAllUserCoroutine = ${it.toString()} | size = ${it.size}")
        })

        viewModel.getAllUserLiveData()?.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "getAllUserLiveData = ${it.toString()} | size = ${it.size}")
        })

    }

}