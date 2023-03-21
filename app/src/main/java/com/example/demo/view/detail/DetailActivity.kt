package com.example.demo.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.demo.R
import com.example.demo.databinding.ActivityDetailBinding
import com.example.demo.network.response.AttractionsItem
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    lateinit var attractionsItem: AttractionsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = intent.getStringExtra("Attractions")
        attractionsItem = Gson().fromJson(json, AttractionsItem::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(IntroductionFragment(attractionsItem), "Introduction")
        fragmentAdapter.addFragment(WebSiteFragment(attractionsItem.url), "Web Site")

        binding.viewPager.adapter = fragmentAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}