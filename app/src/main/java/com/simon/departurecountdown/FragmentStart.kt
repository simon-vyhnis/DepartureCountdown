package com.simon.departurecountdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

class FragmentStart : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val input = view.findViewById<EditText>(R.id.editText)
        view.findViewById<Button>(R.id.button).setOnClickListener {

            if(input.text.toString().isNotEmpty()){
                ViewModelProvider(requireActivity()).get(DeparturesViewModel::class.java).currentStop = input.text.toString()
                it.findNavController().navigate(R.id.action_fragmentStart_to_fragmentDepartures)
            }
        }
    }
}