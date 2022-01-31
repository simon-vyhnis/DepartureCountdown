package com.simon.departurecountdown

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeparturesViewAdapter(private val data: List<Departure>) : RecyclerView.Adapter<DeparturesViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_departure, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.itemView.findViewById<TextView>(R.id.name).text = item.route.name
        holder.itemView.findViewById<TextView>(R.id.direction).text = item.trip.headsign
        holder.itemView.findViewById<TextView>(R.id.time).text = item.departureTime.regularTime
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}