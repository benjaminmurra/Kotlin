package com.example.upnout.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.upnout.databinding.FragmentEventBinding
import com.example.upnout.objects.Event

class EventFragment : Fragment() {

    private lateinit var eventViewModel: EventViewModel
    private var _binding: FragmentEventBinding? = null
    private lateinit var eventReceived : Event

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        eventViewModel =
            ViewModelProvider(this)[EventViewModel::class.java]

        _binding = FragmentEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        eventReceived = arguments?.get("event") as Event

        binding.randomEventFragmentTitle.setText("Would you like to participate to this activity?")
        binding.randomEventTitle.setText("Title: "+eventReceived.title)
        binding.randomEventDescription.setText("Event Description: "+eventReceived.event_description)
        binding.randomEventOrganizer.setText("Organizer: "+eventReceived.organizer)
        binding.randomEventLocation.setText("Location: "+eventReceived.location)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}