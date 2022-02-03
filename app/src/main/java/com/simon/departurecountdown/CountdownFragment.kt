package com.simon.departurecountdown

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels

class CountdownFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_countdown, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel1 = ViewModelProvider(requireActivity()).get(DeparturesViewModel::class.java)
        val viewModel2 : CountdownViewModel by navGraphViewModels(R.id.nav_countdown)
        viewModel2.tripId = viewModel1.currentTripId
        viewModel2.stopName = viewModel1.currentStop

        val textTimeLeft = view.findViewById<TextView>(R.id.time_left)
        val textDelay = view.findViewById<TextView>(R.id.delay)
        val textScheduled = view.findViewById<TextView>(R.id.scheduled)
        textScheduled.text = viewModel1.scheduledDeparture

        viewModel2.getDelayLiveData().observe(viewLifecycleOwner){
            textDelay.text = it.toString()
        }
        viewModel2.getTimeLeftLiveData().observe(viewLifecycleOwner){
            print(it)
            textTimeLeft.text = (it/60).toString() + ":" + (it%60)
        }
    }
}