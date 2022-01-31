package com.simon.departurecountdown

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class DeparturesViewModel : ViewModel() {
    var currentStop : String? = null

    fun getDepartures() : LiveData<DeparturesResponse>? {
        currentStop?.let { return HttpDao.getDepartures(it) }
        return null
    }
}