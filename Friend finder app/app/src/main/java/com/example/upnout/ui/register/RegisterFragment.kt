package com.example.upnout.ui.register

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.upnout.MainActivity
import com.example.upnout.R
import com.example.upnout.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null

    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth
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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonRegister.setOnClickListener {
            validateData()
        }


        return binding.root
    }

    private fun validateData() {
        email = binding.emailAddress.text.toString().trim()
        password = binding.password.text.toString().trim()


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailAddress.error = "Invalid email format"
        }
        else if (TextUtils.isEmpty(password)){
            binding.password.error = "Please enter your password"
        }
        else {
            firebaseSignUp()
        }
    }

    //sign

    private fun firebaseSignUp() {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(activity, "Account created with $email", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }

            .addOnFailureListener{e->
                Toast.makeText(activity, "Account creation failed due to ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}