package com.example.upnout.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upnout.R
import com.example.upnout.objects.Event
import com.example.upnout.objects.EventAdapter
import com.example.upnout.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.eventRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)

        homeViewModel.insertEvent(Event("1","Event 1", "Test Summary for Event 1111", "Kim", "Dartmouth"))
        homeViewModel.insertEvent(Event("2","Event 2", "Test Summary for Event 1111", "Kim", "Dartmouth"))
        homeViewModel.insertEvent(Event("3","Event 3", "Test Summary for Event 1111", "Kim", "Dartmouth"))


        val adapter = EventAdapter { event ->
            Log.d("Recycler", event.toString()+" is here")
            val args = bundleOf(
                "event" to event
            )
            findNavController().navigate(R.id.action_navigation_home_to_eventFragment, args)
        }

        homeViewModel.eventsLiveData.observe(viewLifecycleOwner, { eventsLiveDataList -> adapter.setEvents(eventsLiveDataList) })

        recyclerView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}