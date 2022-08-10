package com.example.upnout.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.upnout.R
import com.example.upnout.databinding.FragmentLoginBinding
import com.example.upnout.ui.profile.ProfileFragment


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth
    private var email = ""
    private var password = ""


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = FirebaseAuth.getInstance()
        checkUser()

        binding.buttonLogin.setOnClickListener {
            validateData()
        }

        loginViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return root
    }

    private fun validateData() {
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEt.error = "Invalid email format"
        }
        else if (TextUtils.isEmpty(password)){
            binding.passwordEt.error = "Please enter your password"
        }
        else {
            firebaseLogin()
        }
    }
    private fun firebaseLogin(){

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val firebaseUser = auth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(activity, "Logged in as $email", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_profileFragment)

            }
            .addOnFailureListener{e->
                Toast.makeText(activity, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser() {
        val fireBaseUser = auth.currentUser

        if (fireBaseUser != null){
            findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}