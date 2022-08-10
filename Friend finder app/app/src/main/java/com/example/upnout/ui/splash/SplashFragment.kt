package com.example.upnout.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.upnout.databinding.FragmentCreateEventBinding

class SplashFragment : Fragment() {

    private lateinit var splashViewModel: SplashViewModel
    private var _binding: FragmentCreateEventBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        splashViewModel =
            ViewModelProvider(this)[SplashViewModel::class.java]

        _binding = FragmentCreateEventBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}