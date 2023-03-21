package com.example.demo.view.attractions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.R
import com.example.demo.databinding.ActivityAttractionsBinding
import com.example.demo.network.AttractionsApi
import com.example.demo.network.Resource
import com.example.demo.repository.AttractionsRepository
import com.example.demo.view.BaseActivity

class AttractionsActivity : BaseActivity<AttractionsViewModel, ActivityAttractionsBinding, AttractionsRepository>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAttractions("zh-tw", 1)

        viewModel.attractionsResponse.observe(this, Observer { resource ->
            when(resource) {
                is Resource.Success -> {
                    binding.attractionsRecyclerView.also {
                        it.layoutManager = LinearLayoutManager(this)
                        it.setHasFixedSize(true)
                        it.adapter = AttractionsAdapter(resource.value.attractions)
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(this,"Get Attractions Failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun getActivityBinding(): ActivityAttractionsBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_attractions)
    }

    override fun getActivityRepository(): AttractionsRepository {
        return AttractionsRepository(remoteDataSource.buildApi(AttractionsApi::class.java))
    }

    override fun getViewModel() = AttractionsViewModel::class.java
}