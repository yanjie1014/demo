package com.example.demo.view.attractions

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.R
import com.example.demo.databinding.ActivityAttractionsBinding
import com.example.demo.network.AttractionsApi
import com.example.demo.network.Resource
import com.example.demo.network.response.AttractionsItem
import com.example.demo.repository.AttractionsRepository
import com.example.demo.view.BaseActivity
import com.example.demo.view.attractions.listener.AttractionsItemClickListener
import com.example.demo.view.detail.DetailActivity
import com.google.gson.Gson

class AttractionsActivity : BaseActivity<AttractionsViewModel, ActivityAttractionsBinding, AttractionsRepository>(), AttractionsItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAttractions("zh-tw", 1)

        viewModel.attractionsResponse.observe(this, Observer { resource ->
            when(resource) {
                is Resource.Success -> {
                    binding.attractionsRecyclerView.also {
                        it.layoutManager = LinearLayoutManager(this)
                        it.setHasFixedSize(true)
                        it.adapter = AttractionsAdapter(resource.value.attractions, this)
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
    override fun onClick(attractionsItem: AttractionsItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("Attractions", Gson().toJson(attractionsItem))
        startActivity(intent)
    }

}