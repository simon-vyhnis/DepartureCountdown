package com.simon.departurecountdown

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DeparturesViewAdapter(private val data: List<Departure>, private val viewModel : DeparturesViewModel) : RecyclerView.Adapter<DeparturesViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_departure, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.itemView.findViewById<TextView>(R.id.name).text = item.route.name
        holder.itemView.findViewById<TextView>(R.id.direction).text = item.trip.headsign
        val time = LocalDateTime.parse(item.departureTime.regularTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+01:00"))
        holder.itemView.findViewById<TextView>(R.id.time).text = time.format(DateTimeFormatter.ofPattern("HH:mm"))

        holder.itemView.setOnClickListener{
            viewModel.currentTripId = item.trip.id
            viewModel.scheduledDeparture = time.format(DateTimeFormatter.ofPattern("HH:mm"))
            holder.itemView.findNavController().navigate(R.id.action_fragmentDepartures_to_countdownFragment)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}