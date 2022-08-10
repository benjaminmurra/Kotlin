package com.example.upnout.ui.createevents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.upnout.databinding.FragmentCreateEventBinding
import com.example.upnout.models.EventRoom
import com.example.upnout.objects.Event
import com.example.upnout.objects.User

class CreateEventsFragment : Fragment() {

    private lateinit var createEventsViewModel: CreateEventsViewModel
    private var _binding: FragmentCreateEventBinding? = null
    private var user = User("123", "Kim-Frederique Viens", "k@k.com", "9021111111", "Dartmouth",1)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createEventsViewModel =
            ViewModelProvider(this)[CreateEventsViewModel::class.java]

        _binding = FragmentCreateEventBinding.inflate(inflater, container, false)

        binding.buttonCreateEvent.setOnClickListener(){
            val event_created = Event("1234", binding.edittextCreateEventTitle.getText().toString(), binding.editTextTextMultiLine.getText().toString(), user.full_name ,binding.edittextCreateEventLocation.getText().toString())
            val toast = Toast.makeText(getActivity(), "Event Added!", Toast.LENGTH_LONG)
            toast.show()
            createEventsViewModel.insertEvent(event_created)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}