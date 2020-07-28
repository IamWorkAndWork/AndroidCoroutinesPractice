package com.devtides.coroutinesroom.presentation.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.devtides.coroutinesroom.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBtn.setOnClickListener { onLogin(it) }
        gotoSignupBtn.setOnClickListener { onGotoSignup(it) }

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginComplete.observe(viewLifecycleOwner, Observer { isComplete ->

            Toast.makeText(activity, "Login Complete", Toast.LENGTH_SHORT).show()
            val acttion =
                LoginFragmentDirections.actionGoToMain()
            Navigation.findNavController(loginUsername).navigate(acttion)

        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->

            Toast.makeText(activity, "Error : $error", Toast.LENGTH_SHORT).show()

        })
    }

    private fun onLogin(v: View) {
        val username = loginUsername.text.toString()
        val password = loginPassword.text.toString()
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            Toast.makeText(activity, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.login(username, password)
        }

    }

    private fun onGotoSignup(v: View) {
        val action =
            LoginFragmentDirections.actionGoToSignup()
        Navigation.findNavController(v).navigate(action)
    }
}
