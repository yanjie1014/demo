package com.example.demo.view.attractions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.network.Resource
import com.example.demo.network.response.AttractionsResponse
import com.example.demo.repository.AttractionsRepository
import kotlinx.coroutines.launch

class AttractionsViewModel(private var repository: AttractionsRepository) : ViewModel() {

    private val _attractionsResponse: MutableLiveData<Resource<AttractionsResponse>> =
        MutableLiveData()

    val attractionsResponse: LiveData<Resource<AttractionsResponse>>
        get() = _attractionsResponse

    fun getAttractions(lang: String, page:Int) = viewModelScope.launch {
        _attractionsResponse.value = repository.getAttractions(lang, page)
    }

}