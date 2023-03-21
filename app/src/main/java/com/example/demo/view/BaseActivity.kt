package com.example.demo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demo.network.RemoteDataSource
import com.example.demo.repository.BaseRepository
import com.example.demo.viewmodel.ViewModelFactory

abstract class BaseActivity<VM : ViewModel, B : ViewDataBinding, R : BaseRepository> :
    AppCompatActivity() {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getActivityBinding()

        val factory = ViewModelFactory(getActivityRepository())
        viewModel = ViewModelProvider(this,factory).get(getViewModel())
    }

    abstract fun getActivityBinding() : B

    abstract fun getActivityRepository() : R

    abstract fun getViewModel() : Class<VM>

}