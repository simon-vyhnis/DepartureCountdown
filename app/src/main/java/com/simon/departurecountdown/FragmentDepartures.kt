package com.simon.departurecountdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentDepartures : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_departures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val viewModel = ViewModelProvider(requireActivity()).get(DeparturesViewModel::class.java)
        viewModel.getDepartures()?.observe(viewLifecycleOwner) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = DeparturesViewAdapter(it.departures)
        }
    }
}