package com.example.upnout.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.upnout.R
import com.example.upnout.databinding.FragmentRegisterBinding
import com.example.upnout.databinding.ProfileFragmentBinding
import com.example.upnout.ui.login.LoginFragment
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private  var binding: ProfileFragmentBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding!!.logoutBtn.setOnClickListener{
            firebaseAuth.signOut()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        return binding!!.root
    }

    private fun checkUser() {
        val fireBaseUser = firebaseAuth.currentUser
        if (fireBaseUser != null) {
            val email = fireBaseUser.email
            binding?.emailTv?.text = email
        }
        else {
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}