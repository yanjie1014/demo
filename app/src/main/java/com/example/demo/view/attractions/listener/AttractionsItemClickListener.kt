package com.example.demo.view.attractions.listener

import com.example.demo.network.response.AttractionsItem

interface AttractionsItemClickListener {
    fun onClick(attractionsItem: AttractionsItem)
}