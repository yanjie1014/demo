package com.example.demo.view.attractions

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

class AttractionsActivity :
    BaseActivity<AttractionsViewModel, ActivityAttractionsBinding, AttractionsRepository>(),
    AttractionsItemClickListener {

    private var page: Int = 1
    private var currentPage = 1
    private var totalPage: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAttractions("zh-tw", page)

        viewModel.attractionsResponse.observe(this, Observer { resource ->
            binding.swipeRefreshLayout.isRefreshing = false
            when (resource) {
                is Resource.Success -> {
                    totalPage = (resource.value.total - 1) / 30 + 1
                    currentPage = page
                    binding.attractionsRecyclerView.also {
                        it.layoutManager = LinearLayoutManager(this)
                        it.setHasFixedSize(true)
                        it.adapter = AttractionsAdapter(resource.value.attractions, this)
                    }
                }
                is Resource.Failure -> {
                    page = currentPage
                    Toast.makeText(this, "Get Attractions Failed", Toast.LENGTH_SHORT).show()
                }
            }
            if (page == 1) {
                binding.prevBtn.visibility = View.INVISIBLE
            } else {
                binding.prevBtn.visibility = View.VISIBLE
            }

            if (page == totalPage) {
                binding.nextBtn.visibility = View.INVISIBLE
            } else {
                binding.nextBtn.visibility = View.VISIBLE
            }

            binding.pageState = "$page / $totalPage"
        })

        setupSwipeRefresh()
        setupButton()
    }

    fun setupButton() {
        binding.prevBtn.setOnClickListener {
            page -= 1
            viewModel.getAttractions("zh-tw", page)
        }
        binding.nextBtn.setOnClickListener {
            page += 1
            viewModel.getAttractions("zh-tw", page)
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setProgressViewOffset(true, 50, 100)
        binding.swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_light,
            android.R.color.holo_red_light,
            android.R.color.holo_orange_light
        )
        binding.swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE)
        binding.swipeRefreshLayout.isEnabled = true
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getAttractions("zh-tw", page)
        }
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