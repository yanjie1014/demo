package com.example.demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demo.repository.AttractionsRepository
import com.example.demo.repository.BaseRepository
import com.example.demo.view.attractions.AttractionsViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when{

            modelClass.isAssignableFrom(AttractionsViewModel::class.java) -> AttractionsViewModel(
                repository as AttractionsRepository
            ) as T

            else -> throw IllegalArgumentException("ViewModel Not Found")
        }

    }

}