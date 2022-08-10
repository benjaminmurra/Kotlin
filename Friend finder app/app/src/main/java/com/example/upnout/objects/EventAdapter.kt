package com.example.upnout.objects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.upnout.R

class EventAdapter(private val onItemClicked: (Event) -> Unit) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    private var eventList = emptyList<Event>()

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.event_list_card, parent, false)
        return ViewHolder(v) {
            onItemClicked(eventList.get(it))
        }
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(eventList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return eventList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(event: Event) {
            val textViewName = itemView.findViewById(R.id.textViewEventTitle) as TextView
            val textViewAddress  = itemView.findViewById(R.id.textViewEventSummary) as TextView
            textViewName.text = event.title
            textViewAddress.text = event.event_description
        }

        init {
            itemView.setOnClickListener {
                onItemClicked(bindingAdapterPosition)
            }
        }
    }

    fun setEvents(events: List<Event>) {
        this.eventList = events
        notifyDataSetChanged()
    }
}